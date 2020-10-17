package com.foodemi.foodemimenu.data.source.local.db.source

import androidx.lifecycle.LiveData
import com.foodemi.foodemimenu.data.source.local.db.model.Cart

interface DBDataSource {
    suspend fun insertCartItem(cart: Cart)
    suspend fun deleteCartItem(cart: Cart)
    suspend fun updateCartItem(id: String, menuQty: Int)
    suspend fun getAllCartItem()
}