package com.avility.recipe_list.domain.model

data class RecipeItem (
   val id: Int,
   val title: String,
   val description: String,
   val country: String,
   val image: String,
   val ingredients: String
)