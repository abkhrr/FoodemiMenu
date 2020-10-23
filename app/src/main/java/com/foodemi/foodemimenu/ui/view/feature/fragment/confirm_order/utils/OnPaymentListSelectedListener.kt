package com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils

import com.foodemi.foodemimenu.data.model.response.PaymentMethodeModel

interface OnPaymentListSelectedListener<T> {
    fun setItemPaymentMethode(items: T)
}