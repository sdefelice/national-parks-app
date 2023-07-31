package com.example.nationalparks.data.remote

import com.example.nationalparks.common.Constants
import com.example.nationalparks.data.remote.dto.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NationalParksAPIClient {

    @GET("v1/parks")
    suspend fun getParksResponse(
        @Query("api_key") api_key: String = Constants.API_KEY,
        @Query("stateCode") stateCode: String?,
        @Query("parkCode") parkCode: String?
    ): Response

}