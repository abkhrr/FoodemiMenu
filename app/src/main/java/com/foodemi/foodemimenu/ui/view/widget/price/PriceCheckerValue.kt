package com.foodemi.foodemimenu.ui.view.widget.price

import android.annotation.SuppressLint
import androidx.appcompat.widget.AppCompatTextView
import java.text.DecimalFormat

class PriceCheckerValue {

    @SuppressLint("SetTextI18n")
    fun checkStringValue(value: String, textView: AppCompatTextView){
        when (value.length) {
            4 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("#,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            5 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("##,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            6 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            7 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("#,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            8 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("##,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            9 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("###,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            10 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("#,###,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            11 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("##,###,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
            12 -> {
                val longval = value.toLong()
                val formatter = DecimalFormat("###,###,###,###")
                val formattedString: String = formatter.format(longval)
                textView.text = formattedString
            }
        }
    }

    fun checkIntValueToString(pToCheck: String): String {
        return when(pToCheck.length){
            4 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("#,###")
                return formatter.format(longval)
            }
            5 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("##,###")
                return formatter.format(longval)
            }
            6 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("###,###")
                return formatter.format(longval)
            }
            7 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("#,###,###")
                return formatter.format(longval)
            }
            8 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("##,###,###")
                return formatter.format(longval)
            }
            9 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("###,###,###")
                return formatter.format(longval)
            }
            10 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("#,###,###,###")
                return formatter.format(longval)
            }
            11 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("##,###,###,###")
                return formatter.format(longval)
            }
            12 -> {
                val longval = pToCheck.toLong()
                val formatter = DecimalFormat("###,###,###,###")
                return formatter.format(longval)
            }
            else -> ""
        }
    }

}