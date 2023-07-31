package com.example.nationalparks.data.repository

import com.example.nationalparks.common.Resource
import com.example.nationalparks.data.remote.NationalParksAPIClient
import com.example.nationalparks.data.remote.dto.Response
import com.example.nationalparks.data.remote.dto.toParkList
import com.example.nationalparks.data.model.Park
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ParkRepository @Inject constructor(
    private val api: NationalParksAPIClient
) {

    fun invoke(stateCode: String?, parkCode: String?): Flow<Resource<List<Park>>> = flow {
        try {
            emit(Resource.Loading())
            val parks = getParksResponse(stateCode, parkCode).toParkList()
            emit(Resource.Success(parks))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server. Check your internet connection."))
        }
    }

    private suspend fun getParksResponse(stateCode: String?, parkCode: String?): Response {
        return if (parkCode.isNullOrEmpty()) {
            api.getParksResponse(stateCode = stateCode, parkCode = null)
        } else {
            api.getParksResponse(stateCode = null, parkCode = parkCode)
        }
    }
}