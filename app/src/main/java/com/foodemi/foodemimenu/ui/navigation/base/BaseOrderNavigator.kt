package com.foodemi.foodemimenu.ui.navigation.base

interface BaseOrderNavigator<T> {
    fun handleOrderError(message: String?)
    fun handleOrderSuccess(data: Boolean)
}