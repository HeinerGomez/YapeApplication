package com.avility.recipe_list.domain.usescases.recipeitem

import com.avility.core.common.Constants
import com.avility.core.common.Resource
import com.avility.recipe_list.data.remote.dto.toRecipeItem
import com.avility.recipe_list.domain.model.RecipeItem
import com.avility.recipe_list.domain.repository.RecipeRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import javax.inject.Inject

class GetRecipeListUseCase @Inject constructor(
    private val repository: RecipeRepository
) {

    operator fun invoke() : Flow<Resource<List<RecipeItem>>> = flow {

        try {
            emit(Resource.Loading())
            val responseRecipeDto = repository.getRecipes()
            val recipeList = responseRecipeDto.data.map { it.toRecipeItem() }
            delay(2_000)
            emit(Resource.Success(recipeList))
        } catch (e: HttpException) {
            emit(Resource.Error(Constants.GENERIC_ERROR))
        } catch (e: IOException) {
            emit(Resource.Error(Constants.NETWORK_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error(Constants.GENERIC_ERROR))
        }

    }

}