package com.foodemi.foodemimenu.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.databinding.ViewHolderItemCollectionMenuListBinding
import com.foodemi.foodemimenu.ui.view.widget.price.PriceCheckerValue
import com.foodemi.foodemimenu.ui.viewmodel.items.menu.MenuListViewModel

class MenuAdapter(val mListener: AdapterMenuCollection.MenuItemListener): RecyclerView.Adapter<MenuAdapter.MenuListHolder>() {

    val menuList: MutableList<ModelMenuSectioned.MenuFoodemi> = mutableListOf()

    fun setMenu(menu: List<ModelMenuSectioned.MenuFoodemi>){
        menuList.let {
            menuList.clear()
            menuList.addAll(menu)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuAdapter.MenuListHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MenuListHolder(ViewHolderItemCollectionMenuListBinding.inflate(inflater, parent, false))
    }

    override fun onBindViewHolder(holder: MenuAdapter.MenuListHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    inner class MenuListHolder(private val binding: ViewHolderItemCollectionMenuListBinding)
        : RecyclerView.ViewHolder(binding.root), MenuListViewModel.AllListMenuListener {

        fun onBind(position: Int){
            with(binding){
                val currentMenu = menuList[position]
                menu = MenuListViewModel(currentMenu, this@MenuListHolder)
                val price = menu?.menuPrice.toString()
                PriceCheckerValue().checkStringValue(price, viewTextMenuPrice)
            }
        }

        override fun onButtonMenuClicked(menu: ModelMenuSectioned.MenuFoodemi) {
            mListener.onButtonMenuClicked(menu)
        }

    }

}