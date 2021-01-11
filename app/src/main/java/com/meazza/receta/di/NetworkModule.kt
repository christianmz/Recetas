package com.meazza.receta.di

import com.google.gson.GsonBuilder
import com.meazza.receta.business.repository.RecipesRepository
import com.meazza.receta.data.network.RecipeService
import com.meazza.receta.data.repository.RecipesRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named

@Module
@InstallIn(ActivityRetainedComponent::class)
object NetworkModule {

    @ActivityRetainedScoped
    @Provides
    @Named("auth_token")
    fun provideAuthToken(): String {
        return "Token 9c8b06d329136da358c2d00e76946b0111ce2c48"
    }

    @ActivityRetainedScoped
    @Provides
    fun provideRecipeService(): RecipeService {
        return Retrofit.Builder()
            .baseUrl("https://food2fork.ca/api/recipe/")
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .build()
            .create(RecipeService::class.java)
    }

    @ActivityRetainedScoped
    @Provides
    fun provideRecipeRepository(service: RecipeService): RecipesRepository {
        return RecipesRepositoryImpl(service)
    }
}