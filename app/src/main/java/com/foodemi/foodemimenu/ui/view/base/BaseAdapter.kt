package com.foodemi.foodemimenu.ui.view.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter<T>(var items: MutableList<T>) :
    RecyclerView.Adapter<BaseViewHolder>() {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.onBind(position)
    }

    fun addItems(items: List<T>?) {
        items?.let { this.items.addAll(it) }
        notifyDataSetChanged()
    }

    fun clearItems() {
        items.clear()
    }

}