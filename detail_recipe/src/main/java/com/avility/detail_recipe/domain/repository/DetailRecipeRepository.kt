package com.avility.detail_recipe.domain.repository

import com.avility.detail_recipe.data.remote.dto.ResponseDetailRecipeDto

interface DetailRecipeRepository {

    suspend fun getDetailRecipe(): ResponseDetailRecipeDto
}