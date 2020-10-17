package com.foodemi.foodemimenu.ui.viewmodel.items.menu

import androidx.databinding.ObservableField
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.ui.view.base.BaseItemListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.BaseMenuListClickedListener

class MenuListViewModel(private val menuList: ModelMenuSectioned.MenuFoodemi, private val mListener: AllListMenuListener) {

    val id: ObservableField<String?>           = ObservableField(menuList.id)
    val menuName: ObservableField<String?>     = ObservableField(menuList.menuName)
    val menuPrice: ObservableField<Int?>       = ObservableField(menuList.menuPrice)
    val menuDesc: ObservableField<String?>     = ObservableField(menuList.menuDesc)
    val menuImage: ObservableField<String?>    = ObservableField(menuList.menuImage)
    val menuQty: ObservableField<Int?>         = ObservableField(menuList.menuQty)

    fun onItemClick() {
        mListener.onButtonMenuClicked(menuList)
    }

    interface AllListMenuListener : BaseMenuListClickedListener<ModelMenuSectioned.MenuFoodemi>

}