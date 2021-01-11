package com.meazza.receta.data.repository

import com.meazza.receta.business.domain.Recipe
import com.meazza.receta.business.repository.RecipesRepository
import com.meazza.receta.data.network.RecipeService

class RecipesRepositoryImpl(
    private val recipeService: RecipeService
) : RecipesRepository {

    override suspend fun searchRecipes(token: String, page: Int, query: String): List<Recipe> {
        return recipeService.searchRecipes(token, page, query).recipes.map { it.toRecipe() }
    }

    override suspend fun getRecipe(token: String, id: Int): Recipe {
        return recipeService.getRecipe(token, id).toRecipe()
    }
}