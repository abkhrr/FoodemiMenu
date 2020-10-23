package com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils

interface OnCartItemEmptyListener<T> {
    fun isEmptyView(count: Int, items: ArrayList<T>)
}