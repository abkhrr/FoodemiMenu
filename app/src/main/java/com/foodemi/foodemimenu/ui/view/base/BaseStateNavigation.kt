package com.foodemi.foodemimenu.ui.view.base

interface BaseStateNavigation<T> {
    fun handleError(message: String?)
    fun handleSuccess(message: String?)
}