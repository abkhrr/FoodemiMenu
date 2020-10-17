package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.navigation.MenuNavigation
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel
import kotlinx.coroutines.launch

class MenuListViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<MenuNavigation>(application, appDataManager) {

    private val allProductData: MutableLiveData<List<ModelMenuSectioned.SectionMenu>> = MutableLiveData()

    private fun fetchAllProduct() {
        launch {
            when (val result = appDataManager.getRemoteData().getSectionedMenu()) {
                is ApiResponse.Success<ModelMenuSectioned> -> {
                    result.data.data.let {
                        navigator?.handleMenuSuccess(it!!)
                    }
                }
                is ApiResponse.Error -> {
                    navigator?.handleMenuError(result.message)
                }
            }
        }
    }

    init {
        fetchAllProduct()
    }

    val allProductLiveData: LiveData<List<ModelMenuSectioned.SectionMenu>>
        get() = allProductData

}