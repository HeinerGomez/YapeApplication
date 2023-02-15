package com.avility.recipe_list.domain.usescases.recipeitem

import com.avility.core.common.Constants
import com.avility.core.common.Resource
import com.avility.recipe_list.domain.model.RecipeItem
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FilterRecipesUseCase @Inject constructor() {

    operator fun invoke(recipes: List<RecipeItem>, query: String) : Flow<Resource<List<RecipeItem>>> = flow {
        try {
            emit(Resource.Loading())
            val recipesFiltered = recipes.filter {
                ( it.ingredients.lowercase().contains(query) || it.title.lowercase().contains(query))
            }
            delay(2_000)
            emit(Resource.Success(recipesFiltered))
        } catch (e: Exception) {
            emit(Resource.Error(Constants.GENERIC_ERROR))
        }
    }

}