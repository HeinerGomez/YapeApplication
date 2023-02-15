package com.avility.detail_recipe.data.remote

import com.avility.core.common.Constants
import com.avility.detail_recipe.data.remote.dto.ResponseDetailRecipeDto
import retrofit2.http.GET

interface YapeApi {
    @GET(Constants.END_POINT_DETAIL_RECIPE)
    suspend fun getDetailRecipe(): ResponseDetailRecipeDto
}