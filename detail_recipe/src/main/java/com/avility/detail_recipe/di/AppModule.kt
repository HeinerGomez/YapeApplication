package com.avility.detail_recipe.di

import com.avility.core.common.Constants
import com.avility.detail_recipe.data.remote.YapeApi
import com.avility.detail_recipe.data.repository.DetailRecipeRepositoryImpl
import com.avility.detail_recipe.domain.repository.DetailRecipeRepository
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
    fun provideDetailRecipeRepository(api: YapeApi): DetailRecipeRepository {
        return DetailRecipeRepositoryImpl(api)
    }
}