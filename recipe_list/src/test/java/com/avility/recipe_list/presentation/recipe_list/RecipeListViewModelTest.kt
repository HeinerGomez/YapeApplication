package com.avility.recipe_list.presentation.recipe_list

import com.avility.core.common.Resource
import com.avility.recipe_list.domain.usescases.recipeitem.FilterRecipesUseCase
import com.avility.recipe_list.domain.usescases.recipeitem.GetRecipeListUseCase
import com.avility.recipe_list.utils.TestDispatcherRule
import com.avility.recipe_list.utils.getRecipeEmptyList
import io.mockk.spyk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

@OptIn(ExperimentalCoroutinesApi::class)
class RecipeListViewModelTest {

    private lateinit var sut: RecipeListViewModel
    private lateinit var getRecipeListUseCase: GetRecipeListUseCase
    private lateinit var filterRecipesUseCase: FilterRecipesUseCase

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        getRecipeListUseCase = mock(GetRecipeListUseCase::class.java)
        filterRecipesUseCase = mock(FilterRecipesUseCase::class.java)
    }

    @Test
    fun `getRecipeList return correct data`() = runTest {
        // Given
        val resourceSuccess = Resource.Success(spyk(getRecipeEmptyList()))
        `when`(getRecipeListUseCase.invoke()).thenReturn(
            flow {
                emit(resourceSuccess)
            }
        )

        sut = RecipeListViewModel(getRecipeListUseCase, filterRecipesUseCase)
        advanceUntilIdle()

        assertEquals(
            RecipeListState(
                isLoading = false,
                recipes = resourceSuccess.data!!
            ),
            sut.state.value
        )
    }
}