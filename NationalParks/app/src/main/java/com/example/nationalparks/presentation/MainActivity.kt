package com.example.nationalparks.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.nationalparks.common.Screen
import com.example.nationalparks.presentation.screens.ParkDetailScreen
import com.example.nationalparks.presentation.screens.PhoneHome
import com.example.nationalparks.presentation.screens.TabletHome
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = when(rememberWindowInfo()) {
                    DeviceType.PHONE -> Screen.PhoneHomeScreen.route
                    else -> Screen.TabletHomeScreen.route
                }
            ) {
                composable(
                    route = Screen.PhoneHomeScreen.route
                ) {
                    PhoneHome(navController = navController)
                }
                composable(
                    route = Screen.TabletHomeScreen.route
                ) {
                    TabletHome(navController)
                }
                composable(
                    route = Screen.ParkDetailScreen.route + "/{parkCode}",
                    arguments = listOf(navArgument("parkCode") {defaultValue = ""})
                ) {
                    ParkDetailScreen(navController = navController)
                }
            }
        }
    }
}