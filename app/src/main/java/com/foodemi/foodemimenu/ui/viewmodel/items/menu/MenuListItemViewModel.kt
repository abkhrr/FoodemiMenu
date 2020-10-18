package com.foodemi.foodemimenu.ui.viewmodel.items.menu

import androidx.databinding.ObservableField
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.ui.view.base.BaseItemListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.BaseMenuListClickedListener

class MenuListItemViewModel(private val menuList: ModelMenuSectioned.MenuFoodemi, private val mListener: AllListMenuListener) {

    val id: ObservableField<String?>           = ObservableField(menuList._id)
    val menuName: ObservableField<String?>     = ObservableField(menuList.menuName)
    val menuPrice: ObservableField<Int?>       = ObservableField(menuList.menuPrice)
    val menuDesc: ObservableField<String?>     = ObservableField(menuList.menuDesc)
    val menuImage: ObservableField<String?>    = ObservableField(menuList.menuImage)

    fun onItemClick() {
        mListener.onButtonMenuClicked(menuList)
    }

    interface AllListMenuListener : BaseMenuListClickedListener<ModelMenuSectioned.MenuFoodemi>

}