package com.foodemi.foodemimenu.ui.view.holder.confirm_order

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.databinding.ViewHolderCollectionConfirmOrderBinding
import com.foodemi.foodemimenu.databinding.ViewHolderConfirmOrderFirstMenuBinding
import com.foodemi.foodemimenu.ui.view.adapter.AdapterCartList
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnCartItemEmptyListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnItemUpdateListener

class HolderConfirmOrderSecond(
    val binding: ViewHolderCollectionConfirmOrderBinding,
    val array: ArrayList<Cart>,
    private val updateItemListener: OnItemUpdateListener<Cart>?,
    private val emptyItemListener: OnCartItemEmptyListener<Cart>?
): RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        with(binding){

            val layoutManager = LinearLayoutManager(root.context)
            layoutManager.orientation = RecyclerView.VERTICAL

            val adapter = AdapterCartList(root.context)
            adapter.setItems(array)

            updateItemListener?.let {
                adapter.addItemUpdateListener(updateItemListener)
            }

            emptyItemListener?.let {
                adapter.addCartItemEmptyListener(emptyItemListener)
            }

            viewMenuListCollectionRVConfirm.adapter = adapter
            viewMenuListCollectionRVConfirm.layoutManager = layoutManager
            viewMenuListCollectionRVConfirm.itemAnimator = null
            viewMenuListCollectionRVConfirm.hasFixedSize()
            executePendingBindings()
        }
    }

}