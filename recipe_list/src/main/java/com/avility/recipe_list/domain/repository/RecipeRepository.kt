package com.avility.recipe_list.domain.repository

import com.avility.recipe_list.data.remote.dto.ResponseRecipeDto

interface RecipeRepository {
    suspend fun getRecipes(): ResponseRecipeDto
}