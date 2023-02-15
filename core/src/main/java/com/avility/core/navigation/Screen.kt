package com.avility.core.navigation

sealed class Screen(val route: String) {
    object RecipeListScreen : Screen("recipe_list_screen")
    object DetailRecipeScreen : Screen("detail_recipe_screen")
    object MapRecipeScreen : Screen("map_recipe_screen")
}