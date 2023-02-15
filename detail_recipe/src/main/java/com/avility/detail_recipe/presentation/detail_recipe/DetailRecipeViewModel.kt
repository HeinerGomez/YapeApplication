package com.avility.detail_recipe.presentation.detail_recipe

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avility.core.common.Constants
import com.avility.core.common.Resource
import com.avility.core.utils.emptyValue
import com.avility.detail_recipe.domain.usescases.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailRecipeViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val _state = mutableStateOf(DetailRecipeState())
    val state: State<DetailRecipeState> = _state

    init {
        getRecipeDetail()
    }

    private fun getRecipeDetail() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                getRecipeDetailUseCase().onEach { result ->
                    when(result) {
                        is Resource.Success -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                recipe = result.data
                            )
                        }
                        is Resource.Error -> {
                            _state.value = state.value.copy(
                                isLoading = false,
                                recipe = null,
                                error = result.message ?: Constants.UNKNOWN_ERROR
                            )
                        }
                        is Resource.Loading -> {
                            _state.value = state.value.copy(
                                isLoading = true,
                                recipe = null,
                                error = String.emptyValue()
                            )
                        }
                    }
                }
            }.launchIn(this)
        }
    }
}