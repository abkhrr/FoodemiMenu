package com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menudetail

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentFeedbackBinding
import com.foodemi.foodemimenu.databinding.FragmentMenuDetailBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.feedback.FeedbackViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menudetails.MenuDetailsViewModel
import javax.inject.Inject


class FragmentMenuDetail : CoreFragment<FragmentMenuDetailBinding, MenuDetailsViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var menuDetailsViewModel: MenuDetailsViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_menu_detail

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: MenuDetailsViewModel
        get() {
            menuDetailsViewModel = ViewModelProvider(this, factory).get(MenuDetailsViewModel::class.java)
            return menuDetailsViewModel as MenuDetailsViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuDetailsViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}