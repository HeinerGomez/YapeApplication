package com.avility.detail_recipe.domain.usescases

import com.avility.core.common.Resource
import com.avility.detail_recipe.data.remote.dto.ResponseDetailRecipeDto
import com.avility.detail_recipe.domain.repository.DetailRecipeRepository
import com.avility.detail_recipe.utils.TestDispatcherRule
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import retrofit2.HttpException

@OptIn(ExperimentalCoroutinesApi::class)
class GetRecipeDetailUseCaseTest {


    private lateinit var sut: GetRecipeDetailUseCase
    private lateinit var detailRecipeRepositoryMock: DetailRecipeRepository

    @get:Rule
    val dispatcherRule = TestDispatcherRule()

    @Before
    fun setUp() {
        detailRecipeRepositoryMock = mock(DetailRecipeRepository::class.java)
    }

    @Test
    fun `invoke returns resource success correctly`() = runTest {
        // Given
        val recipeListMock = mockk<ResponseDetailRecipeDto>(relaxed = true)

        `when`(
            detailRecipeRepositoryMock.getDetailRecipe()
        ).thenReturn(recipeListMock)

        // When
        sut = GetRecipeDetailUseCase(detailRecipeRepositoryMock)
        val resource = sut.invoke().last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Success)
    }

    @Test
    fun `invoke returns resource error correctly with HTTPException`() = runTest {
        // Given
        `when`(
            detailRecipeRepositoryMock.getDetailRecipe()
        ).thenThrow(HttpException::class.java)

        sut = GetRecipeDetailUseCase(detailRecipeRepositoryMock)
        val resource = sut.invoke().last()
        advanceUntilIdle()

        // Then
        assertTrue(resource is Resource.Error)
    }

    @Test
    fun `invoke returns resource loading when the first value is emitted`() = runTest {
        // Given
        `when`(
            detailRecipeRepositoryMock.getDetailRecipe()
        ).thenThrow(HttpException::class.java)

        sut = GetRecipeDetailUseCase(detailRecipeRepositoryMock)

        // Then
        sut.invoke().collectIndexed { index, value ->
            if (index == 0) {
                assertTrue(value is Resource.Loading)
            }
        }
    }
}