package com.foodemi.foodemimenu.ui.view.feature.fragment.thankyou

import android.os.Bundle
import android.util.Log
import com.foodemi.foodemimenu.BR
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentPickTableBinding
import com.foodemi.foodemimenu.databinding.FragmentThankyouBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.picktable.PickTableViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.thankyou.ThankyouViewModel
import javax.inject.Inject


class FragmentThankyou : CoreFragment<FragmentThankyouBinding, ThankyouViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var thankyouViewModel: ThankyouViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_thankyou

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: ThankyouViewModel
        get() {
            thankyouViewModel = ViewModelProvider(this, factory).get(ThankyouViewModel::class.java)
            return thankyouViewModel as ThankyouViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        thankyouViewModel?.setNavigator(this)
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}