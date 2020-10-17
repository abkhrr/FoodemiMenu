package com.foodemi.foodemimenu.ui.view.base

import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned

interface BaseMenuNavigator<T> {
    fun handleMenuError(message: String?)
    fun handleMenuSuccess(menuList: List<ModelMenuSectioned.SectionMenu>)
}