package com.avility.detail_recipe.data.repository

import com.avility.detail_recipe.data.remote.YapeApi
import com.avility.detail_recipe.data.remote.dto.ResponseDetailRecipeDto
import com.avility.detail_recipe.domain.repository.DetailRecipeRepository
import javax.inject.Inject

class DetailRecipeRepositoryImpl @Inject constructor(
    private val api: YapeApi
) : DetailRecipeRepository {

    override suspend fun getDetailRecipe(): ResponseDetailRecipeDto {
        return api.getDetailRecipe()
    }
}