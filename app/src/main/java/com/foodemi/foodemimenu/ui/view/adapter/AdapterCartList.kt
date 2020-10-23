package com.foodemi.foodemimenu.ui.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.databinding.ViewConfirmPesananItemBinding
import com.foodemi.foodemimenu.ui.view.base.BaseCartAdapter
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnItemUpdateListener
import com.foodemi.foodemimenu.ui.view.widget.counter.CounterView
import com.foodemi.foodemimenu.utils.android.image.ImageUtils

class AdapterCartList(context: Context) : BaseCartAdapter<Cart, AdapterCartList.ProductViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductViewHolder(ViewConfirmPesananItemBinding.inflate(layoutInflater, parent,false))
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ProductViewHolder(val binding: ViewConfirmPesananItemBinding) : RecyclerView.ViewHolder(binding.root){

        fun onBind(currentMenu: Cart){
            with(binding){
                menu = currentMenu
                counterView.counterValue      = currentMenu.getQuantity()
                viewTextMenuNameConfirm.text  = currentMenu.getName()
                viewTextMenuPriceConfirm.text = currentMenu.getPrice().toString()
                ImageUtils.loadImageWithoutPlaceholder(viewImageMenuListConfirm, currentMenu.getImage() , root.context)
                updateItemListenerFunction(currentMenu)
                executePendingBindings()
            }
        }

        private fun updateItemListenerFunction(currentMenu: Cart){
            with(binding){

                counterView.addCounterValueChangeListener(object : CounterView.CounterValueChangeListener{

                    override fun onValueDelete(count: Int) {
                        currentMenu.itemQuantity = count
                        removeMenu(adapterPosition, currentMenu)
                        checkEmptyItem()
                        updateItemListener?.onItemUpdated(currentMenu, adapterPosition, binding.root)
                    }

                    override fun onValueAdd(count: Int) {
                        currentMenu.itemQuantity = count
                        updateItemListener?.onItemUpdated(currentMenu, adapterPosition, binding.root)
                    }
                })

            }
        }

        private fun removeMenu(position: Int, menu: Cart){
            with(binding){
                if (menu.itemQuantity == 0 && counterView.counterValue == 0){
                    items.removeAt(position)
                    notifyItemRemoved(position)
                    notifyItemRangeChanged(position, items.size)
                    notifyDataSetChanged()
                }
            }
        }

        private fun checkEmptyItem(){
            if(itemCount == 0 && getItems().isEmpty()){
                emptyItemListener?.isEmptyView(itemCount, items)
            }
        }

    }
}