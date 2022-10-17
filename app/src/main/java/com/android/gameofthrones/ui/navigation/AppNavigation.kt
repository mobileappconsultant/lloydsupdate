package com.android.gameofthrones.ui.navigation

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.android.gameofthrones.ui.model.Character
import com.android.gameofthrones.ui.screens.DetailsScreen
import com.android.gameofthrones.ui.screens.HomeScreen
import com.android.gameofthrones.utils.AssetParamType
import com.google.gson.Gson

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Routes.Home.route) {
        composable(route = Routes.Home.route) {
            HomeScreen {
                val json = Uri.encode(Gson().toJson(it))
                navController.navigate(Routes.Details.createRoute(json))
            }
        }

        composable(
            route = Routes.Details.route,
            arguments = listOf(
                navArgument("character") {
                    type = AssetParamType()
                }
            )
        ) {
            val item = it.arguments?.getParcelable<Character>("character")

            requireNotNull(item) { "Item not found" }

            DetailsScreen(item) {
                navController.popBackStack()
            }
        }
    }
}
