package com.foodemi.foodemimenu.ui.viewmodel.factory

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.viewmodel.feature.activity.ActivityMainViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.confirm_order.ConfirmOrderViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.feedback.FeedbackViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menudetails.MenuDetailsViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist.MenuListViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.picktable.PickTableViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.failed.OrderFailedViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.state.success.OrderSuccessViewModel
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.thankyou.ThankyouViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val application: Application,
    private val appDataManager: AppRepository
) : ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ActivityMainViewModel::class.java) -> {
                ActivityMainViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(ConfirmOrderViewModel::class.java) -> {
                ConfirmOrderViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(FeedbackViewModel::class.java) -> {
                FeedbackViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(MenuListViewModel::class.java) -> {
                MenuListViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(MenuDetailsViewModel::class.java) -> {
                MenuDetailsViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(PickTableViewModel::class.java) -> {
                PickTableViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(ThankyouViewModel::class.java) -> {
                ThankyouViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(OrderSuccessViewModel::class.java) -> {
                OrderSuccessViewModel(application, appDataManager) as T
            }
            modelClass.isAssignableFrom(OrderFailedViewModel::class.java) -> {
                OrderFailedViewModel(application, appDataManager) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}