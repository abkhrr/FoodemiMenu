package com.foodemi.foodemimenu.data.source.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.foodemi.foodemimenu.data.source.local.db.model.Cart

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(article: Cart)

    @Delete
    suspend fun delete(article: Cart)

    @Query("SELECT * FROM cart WHERE id = :id")
    suspend fun findById(id: String): Cart

    @Query("SELECT * FROM cart")
    fun loadAll(): LiveData<List<Cart>>
}