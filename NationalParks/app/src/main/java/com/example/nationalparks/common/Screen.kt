package com.example.nationalparks.common

sealed class Screen(val route: String) {
    object PhoneHomeScreen: Screen("phone_home")
    object TabletHomeScreen: Screen("tablet_home")
    object ParkDetailScreen: Screen("park_detail")
}
