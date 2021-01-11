package com.meazza.receta.ui.ui.recipes

import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.meazza.receta.business.domain.Recipe
import com.meazza.receta.business.repository.RecipesRepository
import com.meazza.receta.vo.FoodCategory
import com.meazza.receta.vo.getFoodCategory
import kotlinx.coroutines.launch
import javax.inject.Named

class RecipesViewModel @ViewModelInject constructor(
    private val repository: RecipesRepository,
    @Named("auth_token") private val token: String
) : ViewModel() {

    val recipes = mutableStateOf<List<Recipe>>(ArrayList())
    val query = mutableStateOf("")
    val selectedCategory = mutableStateOf<FoodCategory?>(null)
    val loading = mutableStateOf(false)
    var categoryScrollPosition: Float = 0f

    init {
        newSearch()
    }

    fun newSearch() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            val result = repository.searchRecipes(token, 1, query.value)
            recipes.value = result
            loading.value = false
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    fun onSelectedCategoryChanged(category: String){
        val newCategory = getFoodCategory(category)
        selectedCategory.value = newCategory
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float){
        categoryScrollPosition = position
    }

    private fun resetSearchState(){
        recipes.value = listOf()
        if(selectedCategory.value?.value != query.value) clearSelectedCategory()
    }

    private fun clearSelectedCategory(){
        selectedCategory.value = null
    }
}