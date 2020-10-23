package com.foodemi.foodemimenu.data.model.response

import com.google.gson.annotations.SerializedName

data class BooleanResponse (
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("data")
    val data: Boolean?
)