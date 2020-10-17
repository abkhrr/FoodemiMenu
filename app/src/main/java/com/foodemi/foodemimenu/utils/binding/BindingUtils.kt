package com.foodemi.foodemimenu.utils.binding

import android.widget.ImageView
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.foodemi.foodemimenu.ui.view.base.BaseAdapter


object BindingUtils {

    @Suppress("UNCHECKED_CAST")
    @JvmStatic
    @BindingAdapter("adapter")
    fun <T> setRecyclerViewData(recyclerView: RecyclerView, items: List<T>?) {
        items?.let {
            (recyclerView.adapter as? BaseAdapter<T>)?.apply {
                clearItems()
                addItems(items)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imgUrl")
    fun setImageUrl(imageView: AppCompatImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            //.error(R.drawable.sos_placeholder)
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageUrlCrop")
    fun setImageUrlCrop(imageView: AppCompatImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            .apply(RequestOptions.circleCropTransform())
            .into(imageView)
    }

    @JvmStatic
    @BindingAdapter("imageWithPlaceHolder")
    fun setImageUrlWithPlaceHolder(imageView: AppCompatImageView, url: String?) {
        Glide.with(imageView.context)
            .load(url)
            //.placeholder(R.drawable.sos_placeholder)
            .into(imageView)
    }
}