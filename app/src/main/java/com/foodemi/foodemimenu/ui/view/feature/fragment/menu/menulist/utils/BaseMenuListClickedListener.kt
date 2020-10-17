package com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils

import android.content.Context

interface BaseMenuListClickedListener<T> {
    fun onButtonMenuClicked(menu: T)
}