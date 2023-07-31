package com.example.nationalparks.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.nationalparks.common.Constants
import com.example.nationalparks.common.Screen
import com.example.nationalparks.presentation.components.HeaderWithContextButton
import com.example.nationalparks.presentation.components.LargeDropdownMenu
import com.example.nationalparks.presentation.components.ParkListItem
import com.example.nationalparks.presentation.viewmodels.NationalParksViewModel

@Composable
fun PhoneHome(
    navController: NavController,
    viewModel: NationalParksViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsStateWithLifecycle().value
    Column(modifier = Modifier.fillMaxSize()) {
        HeaderWithContextButton("Explore Parks in ${
            Constants.STATE_MAP.getOrDefault(state.currentStateCode, "...")
        }") {

            var selectedIndex by rememberSaveable {
                mutableStateOf(-1)
            }

            LargeDropdownMenu(
                label = "State",
                items = Constants.STATE_MAP.keys.toList(),
                selectedIndex = selectedIndex,
                onItemSelected = { index, item ->
                    selectedIndex = index
                    viewModel.getParksWithStateCode(item)
                }
            )
        }

        Box(modifier = Modifier.fillMaxSize()) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(state.parks) { park ->
                    ParkListItem(
                        park = park,
                        onItemClick = {
                            navController.navigate(Screen.ParkDetailScreen.route + "/${park.parkCode}")
                        }
                    )
                }
            }
            if (state.error.isNotEmpty()) {
                Text(
                    text = state.error,
                    color = MaterialTheme.colorScheme.error,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .align(Alignment.Center)
                )
            }
            if (state.isLoading) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}