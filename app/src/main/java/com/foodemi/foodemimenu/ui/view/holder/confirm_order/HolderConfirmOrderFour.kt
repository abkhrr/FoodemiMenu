package com.foodemi.foodemimenu.ui.view.holder.confirm_order

import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.databinding.ViewHolderConfirmOrderFirstMenuBinding
import com.foodemi.foodemimenu.databinding.ViewHolderTotalDetailsConfirmOrderBinding
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartTotal
import com.foodemi.foodemimenu.ui.view.widget.price.PriceCheckerValue
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HolderConfirmOrderFour(private val binding: ViewHolderTotalDetailsConfirmOrderBinding,
                             private val cartTotal: FragmentConfirmOrder?,
                             private val tableNum: String
): RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        with(binding){
            cartTotal?.let {
                viewModel = it.viewModel
                viewModel?.subscribeTotal()?.observe(it.viewLifecycleOwner, {
                    val readableValue  = PriceCheckerValue().checkIntValueToStringWithRp(it.totalAmount.toString())
                    textTablePesananTotal.text = readableValue
                    textTotalAllAmount.text    = readableValue
                })
            }

            textTableNumber.text = tableNum
            executePendingBindings()
        }
    }

}