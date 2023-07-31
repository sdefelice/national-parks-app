package com.example.nationalparks.presentation.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nationalparks.common.Resource
import com.example.nationalparks.data.repository.ParkRepository
import com.example.nationalparks.data.model.Park
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NationalParksViewModel @Inject constructor(
    private val repository: ParkRepository,
    private val savedState: SavedStateHandle
) : ViewModel() {

    /*
    Represents the current state of the UI. Utilizing a backing variable
    protects the app data inside the ViewModel from unwanted and unsafe
    changes by external classes while still allowing external callers to
    safely access its value. Using the StateFlow API optimizes the
    maintenance of having an observable, mutable state, allowing the
    UI to listen to state changes and inherently make the screen state
    survive configuration changes.
     */
    private val _state = MutableStateFlow(ParkListState())
    val state: StateFlow<ParkListState> = _state.asStateFlow()

    init {
        getParks()
    }

    private fun getParks(stateCode: String? = null) {
        val parkCode = savedState.get<String>("parkCode")
        repository.invoke(stateCode, parkCode).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ParkListState(
                        parks = result.data ?: emptyList(),
                        currentStateCode = stateCode ?: ""
                    )
                }
                is Resource.Error -> {
                    _state.value = ParkListState(
                        error = result.message ?: "An unexpected error occurred."
                    )
                }
                is Resource.Loading -> {
                    _state.value = ParkListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getParksWithStateCode(stateCode: String) {
        getParks(stateCode)
    }
}

data class ParkListState(
    val isLoading: Boolean = false,
    val parks: List<Park> = emptyList(),
    val error: String = "",
    val currentStateCode: String = ""
)