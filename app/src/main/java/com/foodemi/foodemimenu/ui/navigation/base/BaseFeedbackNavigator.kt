package com.foodemi.foodemimenu.ui.navigation.base

interface BaseFeedbackNavigator<T> {
    fun handleFeedbackError(message: String?)
    fun handleFeedbackSuccess(data: Boolean)
}