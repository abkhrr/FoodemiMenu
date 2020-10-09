package com.foodemi.foodemimenu.utils.extension

import android.content.res.Resources
import java.text.DecimalFormat

fun Int.dpToPx(): Int {
    return this * Resources.getSystem().displayMetrics.density.toInt()
}

fun Float.getDistanceMerchant(): String? {
    return DecimalFormat("#.##").format(this)
}

fun Int?.orZero():Int = this ?: 0

fun Int?.plus(value :Int?):Int {
    if(this == null) return 0
    if(value == null) return this
    return this + value
}