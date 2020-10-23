package com.foodemi.foodemimenu.data.model.response

import com.google.gson.annotations.SerializedName

data class ModelFeedback (
    @SerializedName("tableNumber")
    var tableNumber: String,
    @SerializedName("feeling")
    var feeling: String,
    @SerializedName("feedback")
    var feedback: String,
    @SerializedName("date")
    var date: String
)