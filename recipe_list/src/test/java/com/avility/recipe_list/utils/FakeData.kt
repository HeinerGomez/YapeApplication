package com.avility.recipe_list.utils

import com.avility.recipe_list.domain.model.RecipeItem

const val QUERY_VALID_SOME_INGREDIENT = "Huev"
const val QUERY_INVALID = "Mariscos"

fun getRecipeList(): List<RecipeItem> = listOf(
    RecipeItem(1, "Arroz con pollo", "", "Colombia", "", "Arroz, Pollo"),
    RecipeItem(2, "Bandeja Paisa", "", "Colombia", "", "Arroz, Carne, Huevo"),
)

fun getRecipeEmptyList(): List<RecipeItem> = emptyList()