package com.meazza.receta.ui.ui.recipes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.meazza.receta.ui.component.AppBarSearch
import com.meazza.receta.ui.component.CardRecipe
import com.meazza.receta.ui.component.ProgressBarCircular
import com.meazza.receta.vo.getAllFoodCategories
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private val recipesViewModel by viewModels<RecipesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                recipesViewModel.run {

                    val recipes = recipes.value
                    val query = query.value
                    val selectedCategory = selectedCategory.value
                    val loading = loading.value
                    val categoryScrollPosition = categoryScrollPosition

                    Column {

                        AppBarSearch(
                            query = query,
                            onQueryChanged = ::onQueryChanged,
                            onExecuteSearch = ::newSearch,
                            categories = getAllFoodCategories(),
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = ::onSelectedCategoryChanged,
                            scrollPosition = categoryScrollPosition,
                            onChangeScrollPosition = ::onChangeCategoryScrollPosition
                        )

                        Box(modifier = Modifier.fillMaxSize()){
                            LazyColumn {
                                itemsIndexed(items = recipes) { _, recipe ->
                                    CardRecipe(recipe = recipe, onClick = {})
                                }
                            }
                            ProgressBarCircular(isDisplayed = loading)
                        }
                    }
                }
            }
        }
    }
}