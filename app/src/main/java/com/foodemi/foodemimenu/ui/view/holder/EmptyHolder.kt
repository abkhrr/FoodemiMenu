package com.foodemi.foodemimenu.ui.view.holder

import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.databinding.HolderEmptyItemBinding

class EmptyHolder(val binding: HolderEmptyItemBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(){
        binding.executePendingBindings()
    }
}