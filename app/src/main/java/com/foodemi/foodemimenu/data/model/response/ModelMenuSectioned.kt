package com.foodemi.foodemimenu.data.model.response

import android.os.Parcelable
import com.foodemi.foodemimenu.data.source.local.db.source.SealedCart
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
        var _id: String,

        @SerializedName("menuCategory")
        var menuCategory: String,

        @SerializedName("menuName")
        var menuName: String,

        @SerializedName("menuQty")
        var menuQty: String,

        @SerializedName("menuImage")
        var menuImage: String,

        @SerializedName("menuPrice")
        var menuPrice: Int,

        @SerializedName("menuDiscount")
        var menuDiscount: Int,

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

    ) : Parcelable, SealedCart {

        override var itemQuantity: Int = 0

        override fun getId(): String {
            return _id
        }

        override fun getName(): String {
            return menuName
        }

        override fun getPrice(): Int {
            return menuPrice
        }

        override fun getQuantity(): Int {
            return itemQuantity
        }

        override fun getTotal(): Int {
            return menuPrice * itemQuantity
        }

        override fun getImage(): String {
            return menuImage
        }

        override fun getPromoStringDesc(): String {
            return menuPromo
        }

        override fun menuIsDiscount(): Boolean {
            return isDiscount
        }

        override fun menuIsAvailable(): Boolean {
            return isAvailable
        }

        override fun menuIsPromo(): Boolean {
            return isPromo
        }

        override fun menuDiscountPrice(): Int {
            return discountPrice
        }

    }

}