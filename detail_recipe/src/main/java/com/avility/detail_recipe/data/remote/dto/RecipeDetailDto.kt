package com.avility.detail_recipe.data.remote.dto


import com.avility.detail_recipe.domain.model.RecipeDetail
import com.google.gson.annotations.SerializedName

data class RecipeDetailDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredients")
    val ingredients: List<IngredientDto>,
    @SerializedName("instructions")
    val instructions: String,
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("title")
    val title: String
)

fun RecipeDetailDto.toRecipeDetail() = RecipeDetail(
    id,
    title,
    description,
    image,
    country,
    ingredients.map { it.toIngredient() },
    instructions,
    location.toLocation()
)