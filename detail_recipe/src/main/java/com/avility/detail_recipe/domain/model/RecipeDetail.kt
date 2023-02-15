package com.avility.detail_recipe.domain.model

data class RecipeDetail (
    val id: Int,
    val title: String,
    val description: String,
    val image: String,
    val country: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val location: Location
)