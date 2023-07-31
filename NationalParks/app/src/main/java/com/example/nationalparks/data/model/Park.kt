package com.example.nationalparks.data.model

data class Park(
    val description: String? = "",
    val designation: String? = "",
    val directionsInfo: String? = "",
    val directionsURL: String? = "",
    val fullName: String? = "",
    val id: String? = "",
    val latitude: String? = "",
    val longitude: String? = "",
    val name: String? = "",
    val parkCode: String? = "",
    val states: String? = "",
    val url: String? = "",
    val weatherInfo: String? = "",
    val activities: List<String> = emptyList(),
    val images: List<ParkImage>? = emptyList()
)

data class ParkImage(
    val credit: String? = "",
    val altText: String? = "",
    val title: String? = "",
    val caption: String? = "",
    val url: String? = ""
)
