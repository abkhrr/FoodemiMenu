package com.foodemi.foodemimenu.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.foodemi.foodemimenu.data.source.local.db.dao.CartDao
import com.foodemi.foodemimenu.data.source.local.db.model.Cart

@Database(entities = [Cart::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cartDao(): CartDao
}