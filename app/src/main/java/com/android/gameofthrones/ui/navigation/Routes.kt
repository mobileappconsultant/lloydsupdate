package com.android.gameofthrones.ui.navigation

sealed class Routes(val route: String) {
    object Home : Routes("homeScreen")
    object Details : Routes("detailsScreen/{character}") {
        fun createRoute(item: String) = "detailsScreen/$item"
    }
}
