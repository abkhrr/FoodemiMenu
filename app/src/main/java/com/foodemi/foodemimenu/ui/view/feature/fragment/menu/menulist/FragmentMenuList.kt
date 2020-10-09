package com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentMenuDetailBinding
import com.foodemi.foodemimenu.databinding.FragmentMenuListBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menudetails.MenuDetailsViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist.MenuListViewModel
import javax.inject.Inject

class FragmentMenuList : CoreFragment<FragmentMenuListBinding, MenuListViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var menuListViewModel: MenuListViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_menu_list

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: MenuListViewModel
        get() {
            menuListViewModel = ViewModelProvider(this, factory).get(MenuListViewModel::class.java)
            return menuListViewModel as MenuListViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuListViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}