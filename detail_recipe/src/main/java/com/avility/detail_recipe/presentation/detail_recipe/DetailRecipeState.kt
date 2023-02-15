package com.avility.detail_recipe.presentation.detail_recipe

import com.avility.core.utils.emptyValue
import com.avility.detail_recipe.domain.model.RecipeDetail

data class DetailRecipeState(
    val isLoading: Boolean = false,
    val recipe: RecipeDetail? = null,
    val error: String = String.emptyValue()
)
