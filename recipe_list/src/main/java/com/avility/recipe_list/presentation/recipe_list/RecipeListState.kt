package com.avility.recipe_list.presentation.recipe_list

import com.avility.core.utils.emptyValue
import com.avility.recipe_list.domain.model.RecipeItem

data class RecipeListState (
    val isLoading: Boolean = false,
    val recipes: List<RecipeItem> = emptyList(),
    val error: String = String.emptyValue()
)