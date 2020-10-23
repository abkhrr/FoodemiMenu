package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.data.source.local.db.source.SealedCart
import com.foodemi.foodemimenu.ui.navigation.MenuNavigation
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartAllTotal
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartTotal
import kotlinx.coroutines.launch

class MenuListViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<MenuNavigation>(application, appDataManager) {

    private val cartAllTotal: MutableLiveData<CartAllTotal> by lazy {
        MutableLiveData<CartAllTotal>()
    }

    fun subscribeCartAllTotal(): LiveData<CartAllTotal> {
        return cartAllTotal
    }

    fun updateCartAllTotal(items: ArrayList<ModelMenuSectioned.MenuFoodemi>) {
        cartAllTotal.postValue(CartAllTotal(items))
    }

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

    fun getAllCartItem(): List<Cart> {
        return appDataManager.getDbRepository().getCartItems()
    }

    fun subscribeTotal(): LiveData<CartTotal>{
        return appDataManager.getDbRepository().subscribeCartTotal()
    }

    suspend fun mapWithCart(array: ArrayList<ModelMenuSectioned.MenuFoodemi>): ArrayList<ModelMenuSectioned.MenuFoodemi> {
        return appDataManager.getDbRepository().mapWithCart(array)
    }

    suspend fun updateItems(sealedCart: SealedCart){
        return appDataManager.getDbRepository().updateItem(sealedCart)
    }

}