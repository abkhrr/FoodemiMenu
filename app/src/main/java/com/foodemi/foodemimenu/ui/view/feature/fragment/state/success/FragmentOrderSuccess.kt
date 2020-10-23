package com.foodemi.foodemimenu.ui.view.feature.fragment.state.success

import android.os.Bundle
import android.util.Log
import android.view.View
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentOrderFailedBinding
import com.foodemi.foodemimenu.databinding.FragmentOrderSuccessBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.failed.OrderFailedViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.success.OrderSuccessViewModel
import javax.inject.Inject

class FragmentOrderSuccess : CoreFragment<FragmentOrderSuccessBinding, OrderSuccessViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var orderSuccessViewModel: OrderSuccessViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_order_success

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: OrderSuccessViewModel
        get() {
            orderSuccessViewModel = ViewModelProvider(this, factory).get(OrderSuccessViewModel::class.java)
            return orderSuccessViewModel as OrderSuccessViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        orderSuccessViewModel?.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setButtonNavigate()
    }

    private fun setButtonNavigate(){
        with(getViewDataBinding()){
            viewBtnActionToFeedback.setOnClickListener {
                getNavController().navigate(R.id.order_success_to_feedback)
            }
        }
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}