package com.meazza.receta.business.repository

import com.meazza.receta.business.domain.Recipe

interface RecipesRepository {

    suspend fun searchRecipes(token: String, page: Int, query: String): List<Recipe>

    suspend fun getRecipe(token: String, id: Int): Recipe
}