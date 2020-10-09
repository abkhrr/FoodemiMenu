package com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentConfirmOrderBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.confirm_order.ConfirmOrderViewModel
import javax.inject.Inject

class FragmentConfirmOrder : CoreFragment<FragmentConfirmOrderBinding, ConfirmOrderViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var confirmOrderViewModel: ConfirmOrderViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_confirm_order

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: ConfirmOrderViewModel
        get() {
            confirmOrderViewModel =
                ViewModelProvider(this, factory).get(ConfirmOrderViewModel::class.java)
            return confirmOrderViewModel as ConfirmOrderViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        confirmOrderViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}