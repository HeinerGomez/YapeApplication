package com.avility.detail_recipe.data.remote.dto


import com.avility.detail_recipe.domain.model.Ingredient
import com.google.gson.annotations.SerializedName

data class IngredientDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String
)

fun IngredientDto.toIngredient() = Ingredient(
    id, name
)