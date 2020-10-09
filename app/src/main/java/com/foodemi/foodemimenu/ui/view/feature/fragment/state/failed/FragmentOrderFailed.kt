package com.foodemi.foodemimenu.ui.view.feature.fragment.state.failed

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentOrderFailedBinding
import com.foodemi.foodemimenu.databinding.FragmentThankyouBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.failed.OrderFailedViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.thankyou.ThankyouViewModel
import javax.inject.Inject

class FragmentOrderFailed : CoreFragment<FragmentOrderFailedBinding, OrderFailedViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var orderFailedViewModel: OrderFailedViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_order_failed

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: OrderFailedViewModel
        get() {
            orderFailedViewModel = ViewModelProvider(this, factory).get(OrderFailedViewModel::class.java)
            return orderFailedViewModel as OrderFailedViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderFailedViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}