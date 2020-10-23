package com.foodemi.foodemimenu.data.model.response

import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.google.gson.annotations.SerializedName

data class ModelOrder (
    @SerializedName("orderId")
    var orderId: String,
    @SerializedName("tableNumber")
    var tableNumber: String,
    @SerializedName("order_date")
    var orderDate: String,
    @SerializedName("orderType")
    var orderType: String,
    @SerializedName("paymentMethode")
    var paymentMethod: String,
    @SerializedName("orderNotes")
    var orderNotes: String,
    @SerializedName("totalPrice")
    var totalPrice: Int,
    @SerializedName("quequeNunmber")
    var quequeNumber: Int,
    @SerializedName("kasirName")
    var kasirName: String,
    @SerializedName("isGojek")
    var isGojek: Boolean,
    @SerializedName("order_status")
    var order_status: OrderStatus,
    @SerializedName("paymentStatus")
    var paymentStatus: Boolean,
    @SerializedName("menu")
    var menu: List<MenuOrder>
)

data class OrderStatus (
    @SerializedName("onPrepare")
    var onPrepare: Boolean,
    @SerializedName("served")
    var served : Boolean
)

data class MenuOrder(
    @SerializedName("menuId")
    var menu: MenuId,
    @SerializedName("qty")
    var itemQty: Int
)

data class MenuId(
    @SerializedName("_id")
    val id: String
)