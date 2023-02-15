package com.avility.recipe_list.domain.usescases.recipeitem

import com.avility.core.common.Resource
import com.avility.core.utils.emptyValue
import com.avility.recipe_list.domain.model.RecipeItem
import com.avility.recipe_list.utils.QUERY_INVALID
import com.avility.recipe_list.utils.QUERY_VALID_SOME_INGREDIENT
import com.avility.recipe_list.utils.TestDispatcherRule
import com.avility.recipe_list.utils.getRecipeList
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@OptIn(ExperimentalCoroutinesApi::class)
class FilterRecipesUseCaseTest {

    private lateinit var sut: FilterRecipesUseCase
    private lateinit var recipeFakeList: List<RecipeItem>

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        sut = FilterRecipesUseCase()
        recipeFakeList = getRecipeList()
    }

    @Test
    fun `invoke filter returns resource success correctly`() = runTest {
        // Given
        val recipeListItemMock = mockk<List<RecipeItem>>(relaxed = true)

        // When
        val resource = sut.invoke(recipeListItemMock, String.emptyValue()).last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Success)
    }

    @Test
    fun `invoke filter by a some word, must return the filter list`() = runTest {
        // When
        val resource = sut.invoke(recipeFakeList, QUERY_VALID_SOME_INGREDIENT).last()
        advanceUntilIdle()
        // Then
        assertTrue(resource.data != null)
        assertTrue(resource.data!!.size < recipeFakeList.size)
    }

    @Test
    fun `invoke filter with a empty query, must return the same list that it was passed`() = runTest {
        // When
        val resource = sut.invoke(recipeFakeList, String.emptyValue()).last()
        advanceUntilIdle()
        // Then
        assertTrue(resource.data?.size == recipeFakeList.size)
    }

    @Test
    fun `invoke filter with a query without results, must return a empty list`() = runTest {
        // When
        val resource = sut.invoke(recipeFakeList, QUERY_INVALID).last()
        advanceUntilIdle()
        // Then
        assertTrue(resource.data != null)
        assertTrue(resource.data!!.isEmpty())
    }
}