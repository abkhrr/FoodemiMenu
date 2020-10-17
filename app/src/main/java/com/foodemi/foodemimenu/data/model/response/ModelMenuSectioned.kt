package com.foodemi.foodemimenu.data.model.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class ModelMenuSectioned {

    val message: String? = null
    val status: String? = null
    val data: List<SectionMenu>? = null

    @Parcelize
    data class SectionMenu(

        @SerializedName("menuCategory")
        val menuCategory: String,

        @SerializedName("categoryData")
        val categoryData: List<MenuFoodemi>

    ) : Parcelable

    @Parcelize
    data class MenuFoodemi(

        @SerializedName("_id")
        var id: String,

        @SerializedName("menuCategory")
        var menuCategory: String,

        @SerializedName("menuName")
        var menuName: String,

        @SerializedName("menuImage")
        var menuImage: String,

        @SerializedName("menuPrice")
        var menuPrice: Int,

        @SerializedName("menuDiscount")
        var menuDiscount: Int,

        @SerializedName("menuQty")
        var menuQty: Int,

        @SerializedName("menuDesc")
        var menuDesc: String,

        @SerializedName("isAvailable")
        var isAvailable: Boolean,

        @SerializedName("isRecommended")
        var isRecommended: Boolean,

        @SerializedName("menuPromo")
        var menuPromo: String,

        @SerializedName("isPromo")
        var isPromo: Boolean,

        @SerializedName("isDiscount")
        var isDiscount: Boolean,

        @SerializedName("discountPrice")
        var discountPrice: Int,

    ) : Parcelable

}