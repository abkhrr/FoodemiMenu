package com.foodemi.foodemimenu.data.model.api

sealed class ApiResponse<out T : Any> {
    data class Success<out T : Any>(val data: T) : ApiResponse<T>()
    data class Error(val message: String?, val statusCode: Int? = null) : ApiResponse<Nothing>()
}