package com.avility.recipe_list.data.repository

import com.avility.recipe_list.data.remote.YapeApi
import com.avility.recipe_list.data.remote.dto.ResponseRecipeDto
import com.avility.recipe_list.domain.repository.RecipeRepository
import javax.inject.Inject

class RecipeRepositoryImpl @Inject constructor(
    private val api: YapeApi
) : RecipeRepository {

    override suspend fun getRecipes(): ResponseRecipeDto {
        return api.getRecipes()
    }
}