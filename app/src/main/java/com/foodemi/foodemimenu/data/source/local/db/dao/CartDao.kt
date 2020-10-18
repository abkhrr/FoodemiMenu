package com.foodemi.foodemimenu.data.source.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.foodemi.foodemimenu.data.source.local.db.model.Cart

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cart: Cart)

    @Query("DELETE FROM cart Where id=:id")
    suspend fun deleteCartItem(id: String): Int

    @Query("SELECT * FROM cart WHERE id = :id")
    suspend fun findById(id: String): Cart

    @Query("UPDATE cart SET menuQty = :menuQty  Where id = :id")
    suspend fun updateCartItem( id:String, menuQty:Int )

    @Query("SELECT * FROM cart")
    fun getAllCartItem(): List<Cart>

}