package com.avility.recipe_list.di

import com.avility.core.common.Constants
import com.avility.recipe_list.data.remote.YapeApi
import com.avility.recipe_list.data.repository.RecipeRepositoryImpl
import com.avility.recipe_list.domain.repository.RecipeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideYapeApi() : YapeApi {
        return Retrofit
            .Builder()
            .baseUrl(Constants.BASE_URL_API)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(api: YapeApi): RecipeRepository {
        return RecipeRepositoryImpl(api)
    }
}