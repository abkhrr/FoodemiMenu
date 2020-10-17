package com.foodemi.foodemimenu.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.databinding.ViewHolderHeaderCollectionMenuListBinding
import com.foodemi.foodemimenu.ui.view.base.BaseEmptyItemListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.BaseMenuListClickedListener

class AdapterMenuCollection: RecyclerView.Adapter<AdapterMenuCollection.MenuCollectionHolder>() {

    private var listMenuHeader: MutableList<ModelMenuSectioned.SectionMenu> = mutableListOf()

    private lateinit var mListener: MenuItemListener

    fun setHeaderMenuList(list: List<ModelMenuSectioned.SectionMenu>){
        listMenuHeader.let {
            listMenuHeader.clear()
            listMenuHeader.addAll(list)
            notifyDataSetChanged()
        }
    }

    fun setListener(listener: MenuItemListener){
        mListener = listener
    }

    interface MenuItemListener : BaseMenuListClickedListener<ModelMenuSectioned.MenuFoodemi>,
        BaseEmptyItemListener

    private val viewPool = RecyclerView.RecycledViewPool()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterMenuCollection.MenuCollectionHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MenuCollectionHolder(ViewHolderHeaderCollectionMenuListBinding.inflate(layoutInflater, parent,false))
    }

    override fun onBindViewHolder(holder: AdapterMenuCollection.MenuCollectionHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return listMenuHeader.size
    }

    inner class MenuCollectionHolder(
        private val binding: ViewHolderHeaderCollectionMenuListBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int){
            with(binding){
                val currentMenuCategory = listMenuHeader[position]
                val layoutManager = LinearLayoutManager(root.context)
                layoutManager.orientation = RecyclerView.VERTICAL

                val adapter = MenuAdapter(mListener)
                adapter.setMenu(currentMenuCategory.categoryData)
                menu = currentMenuCategory

                rvListMenu.setRecycledViewPool(viewPool)
                rvListMenu.adapter = adapter
                rvListMenu.layoutManager = layoutManager
                executePendingBindings()
            }
        }

    }

}