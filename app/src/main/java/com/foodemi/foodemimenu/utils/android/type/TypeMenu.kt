package com.foodemi.foodemimenu.utils.android.type

object TypeMenu {
    
    const val ITEM_FOODEMI_HEADER_MENU            = -1
    const val ITEM_FOODEMI_LIST_MENU              = -2
    const val ITEM_FOODEMI_MENU_OTHER             = -3


    //HOLDER ITEM NAME
    const val FOODEMI_HOLDER_FOODEMI_HEADER_MENU        = "HOLDER_HEADER_MENU_LIST"
    const val FOODEMI_HOLDER_FOODEMI_LIST_MENU          = "HOLDER_MENU_LIST"
    const val FOODEMI_HOLDER_FOODEMI_MENU_OTHER         = "OTHER"

    fun getMenuListHolder(holderName : Any) = when(holderName){
        FOODEMI_HOLDER_FOODEMI_HEADER_MENU    -> ITEM_FOODEMI_HEADER_MENU
        FOODEMI_HOLDER_FOODEMI_LIST_MENU      -> ITEM_FOODEMI_LIST_MENU
        else                                  -> ITEM_FOODEMI_MENU_OTHER
    }
    
}