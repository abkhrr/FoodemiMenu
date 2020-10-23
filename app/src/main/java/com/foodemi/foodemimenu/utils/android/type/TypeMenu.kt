package com.foodemi.foodemimenu.utils.android.type

object TypeMenu {
    
    const val ITEM_FOODEMI_FIRST                        = -1
    const val ITEM_FOODEMI_LIST_ORDER                   = -2
    const val ITEM_FOODEMI_PAYMENT_M                    = -3
    const val ITEM_FOODEMI_PAYMENT_DETAILS              = -4
    const val ITEM_FOODEMI_MENU_OTHER                   = -5


    //HOLDER ITEM NAME
    const val FOODEMI_HOLDER_FOODEMI_FIRST                  = "HOLDER_HEADER_MENU_FIRST"
    const val FOODEMI_HOLDER_FOODEMI_LIST_ORDER             = "HOLDER_LIST_ORDER"
    const val FOODEMI_HOLDER_PAYMENT_M                      = "HOLDER_PAYMENT_METHODE"
    const val FOODEMI_HOLDER_PAYMENT_DETAILS                = "HOLDER_PAYMENT_DETAILS"
    const val FOODEMI_HOLDER_FOODEMI_MENU_OTHER             = "OTHER"

    fun getMenuListHolder(holderName : Any) = when(holderName){
        FOODEMI_HOLDER_FOODEMI_FIRST            -> ITEM_FOODEMI_FIRST
        FOODEMI_HOLDER_FOODEMI_LIST_ORDER       -> ITEM_FOODEMI_LIST_ORDER
        FOODEMI_HOLDER_PAYMENT_M                -> ITEM_FOODEMI_PAYMENT_M
        FOODEMI_HOLDER_PAYMENT_DETAILS          -> ITEM_FOODEMI_PAYMENT_DETAILS
        else                                    -> ITEM_FOODEMI_MENU_OTHER
    }
    
}