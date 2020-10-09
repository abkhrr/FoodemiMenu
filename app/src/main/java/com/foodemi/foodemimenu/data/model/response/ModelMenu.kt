package com.foodemi.foodemimenu.data.model.response

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ModelMenu{
    val message: String? = null
    val status: String? = null
    val data: List<FoodemiMenu>? = null


    data class FoodemiMenu(
        @SerializedName("menuName")
        val menuName: String,

        @SerializedName("menuImage")
        val menuImage: String,

        @SerializedName("menuDesc")
        val menuDesc: String,

        @SerializedName("menuPrice")
        val menuPrice: String,

        @SerializedName("promo")
        val promo: Boolean
    )

}