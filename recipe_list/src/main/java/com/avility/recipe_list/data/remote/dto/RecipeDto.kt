package com.avility.recipe_list.data.remote.dto


import com.avility.recipe_list.domain.model.RecipeItem
import com.google.gson.annotations.SerializedName

data class RecipeDto(
    @SerializedName("country")
    val country: String,
    @SerializedName("Description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("ingredients")
    val ingredients: String,
    @SerializedName("title")
    val title: String
)

fun RecipeDto.toRecipeItem(): RecipeItem {
    return RecipeItem(
        id,
        title,
        description,
        country,
        image,
        ingredients
    )
}