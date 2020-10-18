package com.foodemi.foodemimenu.data.source.local.db.source

import androidx.lifecycle.LiveData
import androidx.room.ColumnInfo
import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.source.local.db.model.Cart

interface SealedCart {

    var itemQuantity: Int

    fun getId(): String

    fun getName():String

    fun getPrice(): Int

    fun getQuantity(): Int

    fun getTotal() : Int

    fun getImage(): String

    fun getPromoStringDesc(): String

    fun menuIsDiscount(): Boolean

    fun menuIsAvailable(): Boolean

    fun menuIsPromo(): Boolean

    fun menuDiscountPrice(): Int

}