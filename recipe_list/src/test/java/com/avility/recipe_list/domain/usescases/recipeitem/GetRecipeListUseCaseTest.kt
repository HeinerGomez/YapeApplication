package com.avility.recipe_list.domain.usescases.recipeitem

import com.avility.core.common.Resource
import com.avility.recipe_list.data.remote.dto.ResponseRecipeDto
import com.avility.recipe_list.domain.repository.RecipeRepository
import com.avility.recipe_list.utils.TestDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
internal class GetRecipeListUseCaseTest {

    private lateinit var sut: GetRecipeListUseCase
    private lateinit var recipeRepositoryMock: RecipeRepository

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        recipeRepositoryMock = mock(RecipeRepository::class.java)
    }

    @Test
    fun `invoke returns resource success correctly`() = runTest {
        // Given
        val recipeListMock = mockk<ResponseRecipeDto>(relaxed = true)

        `when`(
            recipeRepositoryMock.getRecipes()
        ).thenReturn(recipeListMock)

        // When
        sut = GetRecipeListUseCase(recipeRepositoryMock)
        val resource = sut.invoke().last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Success)
    }

    @Test
    fun `invoke returns resource error correctly with HTTPException`() = runTest {
        // Given
        `when`(
            recipeRepositoryMock.getRecipes()
        ).thenThrow(HttpException::class.java)

        sut = GetRecipeListUseCase(recipeRepositoryMock)
        val resource = sut.invoke().last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Error)
    }

    @Test
    fun `invoke returns resource loading when the first value is emitted`() = runTest {
        // Given
        `when`(
            recipeRepositoryMock.getRecipes()
        ).thenThrow(HttpException::class.java)

        sut = GetRecipeListUseCase(recipeRepositoryMock)

        // Then
        sut.invoke().collectIndexed { index, value ->
            if (index == 0) {
                assertTrue(value is Resource.Loading)
            }
        }
    }
}