package com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils

import android.view.View

interface OnItemUpdateListener<T> {
    fun onItemUpdated(item: T, position: Int, view: View)
}