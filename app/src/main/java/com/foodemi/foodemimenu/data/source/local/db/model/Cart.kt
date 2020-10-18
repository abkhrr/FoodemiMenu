package com.foodemi.foodemimenu.data.source.local.db.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.foodemi.foodemimenu.data.source.local.db.source.SealedCart

@Entity(tableName = "cart")
data class Cart(

    @field: PrimaryKey
    @ColumnInfo(name = "id")
    var menuId: String,

    @field: ColumnInfo(name = "name")
    var menuName: String,

    @field: ColumnInfo(name = "price")
    var menuPrice: Int = 0,

    @field: ColumnInfo(name = "imageUrl")
    var menuImage: String,

    @field: ColumnInfo(name = "menuQty")
    override var itemQuantity: Int = 0,

    @field: ColumnInfo(name = "isDiscount")
    var isDiscount: Boolean,

    @field: ColumnInfo(name = "isAvailable")
    var isAvailable: Boolean,

    @field: ColumnInfo(name = "menuPromo")
    var isPromo: Boolean,

    @field: ColumnInfo(name = "isPromo")
    var promoDesc: String,

    @field: ColumnInfo(name = "discountPrice")
    var discountPrice: Int = 0,

) : SealedCart {

    constructor(sealedCart: SealedCart) : this (
        sealedCart.getId(),
        sealedCart.getName(),
        sealedCart.getPrice(),
        sealedCart.getImage(),
        sealedCart.getQuantity(),
        sealedCart.menuIsDiscount(),
        sealedCart.menuIsAvailable(),
        sealedCart.menuIsPromo(),
        sealedCart.getPromoStringDesc(),
        sealedCart.menuDiscountPrice()
    )

    override fun getId(): String {
        return menuId
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
        return promoDesc
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