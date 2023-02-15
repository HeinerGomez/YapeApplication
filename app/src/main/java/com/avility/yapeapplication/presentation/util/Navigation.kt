package com.avility.yapeapplication.presentation.util

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.avility.core.common.Constants
import com.avility.core.navigation.Screen
import com.avility.detail_recipe.presentation.detail_recipe.DetailRecipeScreen
import com.avility.map_recipe.presentation.map_recipe.MapRecipeScreen
import com.avility.recipe_list.presentation.recipe_list.RecipeListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.RecipeListScreen.route
    ) {
        composable(Screen.RecipeListScreen.route) {
            RecipeListScreen(navController)
        }
        composable(Screen.DetailRecipeScreen.route) {
            DetailRecipeScreen(navController)
        }
        composable(
            Screen.MapRecipeScreen.route + "/{lat}/{lng}",
            arguments = listOf(
                navArgument(Constants.PARAM_LAT_MAP_RECIPE) { type = NavType.StringType },
                navArgument(Constants.PARAM_LNG_MAP_RECIPE) { type = NavType.StringType }
            )
        ) { backStackEntry ->

            val lat = backStackEntry.arguments?.getString(Constants.PARAM_LAT_MAP_RECIPE)?.toDoubleOrNull()
            val lng = backStackEntry.arguments?.getString(Constants.PARAM_LNG_MAP_RECIPE)?.toDoubleOrNull()

            MapRecipeScreen(navController, lat, lng)
        }
    }
}