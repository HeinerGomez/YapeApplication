package com.avility.recipe_list.presentation.recipe_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avility.core.common.Constants
import com.avility.core.common.Resource
import com.avility.core.utils.emptyValue
import com.avility.recipe_list.domain.model.RecipeItem
import com.avility.recipe_list.domain.usescases.recipeitem.FilterRecipesUseCase
import com.avility.recipe_list.domain.usescases.recipeitem.GetRecipeListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getRecipeListUseCase: GetRecipeListUseCase,
    private val filterRecipesUseCase: FilterRecipesUseCase
) : ViewModel() {

    private val _state = mutableStateOf(RecipeListState())
    val state: State<RecipeListState> = _state

    private val _searchQuery = mutableStateOf(String.emptyValue())
    val searchQuery: State<String> = _searchQuery

    private var searchJob: Job? = null

    private lateinit var recipes: List<RecipeItem>
    private lateinit var recipesFiltered: List<RecipeItem>

    init {
        getRecipeList()
    }

    private fun getRecipeList() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getRecipeListUseCase().onEach { result ->
                    configureState(result)
                }
            }.launchIn(this)
        }
    }

    fun searchRecipes(query: String) {
        _searchQuery.value = query

        searchJob?.cancel()

        searchJob = viewModelScope.launch {
            delay(500L)

            withContext(Dispatchers.IO) {
                filterRecipesUseCase(recipes, query.lowercase()).onEach { result ->
                    configureState(result, true)
                }
            }.launchIn(this)
        }
    }

    private fun configureState(result: Resource<List<RecipeItem>>, searchMode: Boolean = false) {
        when (result) {
            is Resource.Success -> {
                if (searchMode) {
                    recipesFiltered = result.data ?: emptyList()
                } else {
                    recipes = result.data ?: emptyList()
                    recipesFiltered = recipes
                }
                _state.value = state.value.copy(
                    isLoading = false,
                    recipes = recipesFiltered
                )
            }
            is Resource.Error -> {
                recipes = emptyList()
                recipesFiltered = recipes

                _state.value = state.value.copy(
                    isLoading = false,
                    recipes = emptyList(),
                    error = result.message ?: Constants.UNKNOWN_ERROR
                )
            }
            is Resource.Loading -> {
                recipes = emptyList()
                recipesFiltered = recipes

                _state.value = state.value.copy(
                    isLoading = true,
                    recipes = emptyList(),
                    error = String.emptyValue()
                )
            }
        }
    }
}