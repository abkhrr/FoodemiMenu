package com.foodemi.foodemimenu.ui.view.feature.fragment.picktable

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentMenuListBinding
import com.foodemi.foodemimenu.databinding.FragmentPickTableBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist.MenuListViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.picktable.PickTableViewModel
import javax.inject.Inject

class FragmentPickTable : CoreFragment<FragmentPickTableBinding, PickTableViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var pickTableViewModel: PickTableViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_pick_table

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: PickTableViewModel
        get() {
            pickTableViewModel = ViewModelProvider(this, factory).get(PickTableViewModel::class.java)
            return pickTableViewModel as PickTableViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickTableViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}