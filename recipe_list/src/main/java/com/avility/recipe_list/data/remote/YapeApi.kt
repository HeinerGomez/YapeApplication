package com.avility.recipe_list.data.remote

import com.avility.core.common.Constants
import com.avility.recipe_list.data.remote.dto.ResponseRecipeDto
import retrofit2.http.GET

interface YapeApi {

    @GET(Constants.END_POINT_RECIPES)
    suspend fun getRecipes(): ResponseRecipeDto
}