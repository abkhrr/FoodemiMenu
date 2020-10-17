package com.foodemi.foodemimenu.ui.view.widget.nestedMenuList

import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.ui.view.adapter.AdapterMenuCollection

class MenuListSimple(private val menuList: List<ModelMenuSectioned.SectionMenu>, private val adapter: AdapterMenuCollection) {

    fun setAllList(){
        val myList = menuList.toSet().toList()
        myList.forEach {
            val listTwo        = arrayListOf<ModelMenuSectioned.MenuFoodemi>()
            listTwo += it.categoryData
            val menuHeader      = arrayListOf<ModelMenuSectioned.SectionMenu>()
            menuHeader += myList
            adapter.setHeaderMenuList(menuHeader)
        }
    }

}