package com.meazza.receta.data.network.model

import com.google.gson.annotations.SerializedName
import com.meazza.receta.business.domain.Recipe

data class RecipeDto(
    @SerializedName("pk")
    var pk: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("publisher")
    var publisher: String? = null,

    @SerializedName("featured_image")
    var featuredImage: String? = null,

    @SerializedName("rating")
    var rating: Int? = 0,

    @SerializedName("source_url")
    var sourceUrl: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("cooking_instructions")
    var cookingInstructions: String? = null,

    @SerializedName("ingredients")
    var ingredients: List<String>? = null,

    @SerializedName("date_added")
    var dateAdded: String? = null,

    @SerializedName("date_updated")
    var dateUpdated: String? = null,
){

    companion object{
        fun fromRecipe(recipe: Recipe): RecipeDto {
            return RecipeDto(
                pk = recipe.id,
                title = recipe.title,
                featuredImage = recipe.featuredImage,
                rating = recipe.rating,
                publisher = recipe.publisher,
                sourceUrl = recipe.sourceUrl,
                description = recipe.description,
                cookingInstructions = recipe.cookingInstructions,
                ingredients = recipe.ingredients,
                dateAdded = recipe.dateAdded,
                dateUpdated = recipe.dateUpdated,
            )
        }
    }

    fun toRecipe(): Recipe {
        return Recipe(
            id = pk,
            title = title,
            featuredImage = featuredImage,
            rating = rating,
            publisher = publisher,
            sourceUrl = sourceUrl,
            description = description,
            cookingInstructions = cookingInstructions,
            ingredients = ingredients.orEmpty(),
            dateAdded = dateAdded,
            dateUpdated = dateUpdated,
        )
    }
}