package com.foodemi.foodemimenu.ui.view.widget.snackBar

import android.content.Context
import android.graphics.Typeface
import android.os.Build
import android.view.ViewGroup.MarginLayoutParams
import android.widget.TextView
import androidx.core.view.ViewCompat
import com.foodemi.foodemimenu.R
import com.google.android.material.snackbar.Snackbar


object SnackBarHelper {

    fun configSnackbar(context: Context, snack: Snackbar) {
        addMargins(snack)
        setRoundBordersBg(context, snack)
        setTypeFace(context, snack)
        ViewCompat.setElevation(snack.view, 0f)
        changeActionTextColor(snack, context)
    }

    private fun changeActionTextColor(snackbar: Snackbar, context: Context) {
        snackbar.setActionTextColor(context.resources.getColor(android.R.color.transparent))
    }

    private fun addMargins(snack: Snackbar) {
        val params = snack.view.layoutParams as MarginLayoutParams
        params.setMargins(12, 12, 12, 12)
        snack.view.layoutParams = params
    }

    private fun setRoundBordersBg(context: Context, snackbar: Snackbar) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            snackbar.view.background = context.getDrawable(R.drawable.snack_bar_container)
        }
    }

    private fun setTypeFace(context: Context, snackbar: Snackbar) {
        val tv = snackbar.view.findViewById<TextView>(R.id.snackbar_text)
        tv.setTextColor(context.resources.getColor(R.color.FOODEMI_COLOR_TRANSPARENT))
    }
}