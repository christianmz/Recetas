package com.meazza.receta.data.network.response

import com.google.gson.annotations.SerializedName
import com.meazza.receta.data.network.model.RecipeDto

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)