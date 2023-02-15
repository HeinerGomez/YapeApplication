package com.avility.detail_recipe.domain.usescases

import com.avility.core.common.Constants
import com.avility.core.common.Resource
import com.avility.detail_recipe.data.remote.dto.toRecipeDetail
import com.avility.detail_recipe.domain.model.RecipeDetail
import com.avility.detail_recipe.domain.repository.DetailRecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetRecipeDetailUseCase @Inject constructor(
    private val repository: DetailRecipeRepository
) {
    operator fun invoke(): Flow<Resource<RecipeDetail>> = flow {
        try {
            emit(Resource.Loading())
            val responseDetailRecipeDto = repository.getDetailRecipe()
            delay(1_000)
            emit(Resource.Success(responseDetailRecipeDto.data.toRecipeDetail()))
        } catch (e: HttpException) {
            emit(Resource.Error(Constants.GENERIC_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Constants.NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error(Constants.GENERIC_ERROR))
        }
    }
}