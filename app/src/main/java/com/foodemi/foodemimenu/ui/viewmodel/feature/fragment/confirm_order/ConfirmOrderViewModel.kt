package com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.confirm_order

import android.app.Application
import androidx.lifecycle.LiveData
import com.foodemi.foodemimenu.data.model.api.ApiResponse
import com.foodemi.foodemimenu.data.model.response.BooleanResponse
import com.foodemi.foodemimenu.data.model.response.ModelOrder
import com.foodemi.foodemimenu.data.repository.AppRepository
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.data.source.local.db.source.SealedCart
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.navigation.OrderNavigator
import com.foodemi.foodemimenu.ui.view.base.BaseViewModel
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartTotal
import com.foodemi.foodemimenu.utils.android.type.TypeMenu
import kotlinx.coroutines.launch

class ConfirmOrderViewModel(
    application: Application,
    appDataManager: AppRepository
) : BaseViewModel<OrderNavigator>(application, appDataManager) {

    fun sendOrder(body: ModelOrder){
        launch {
            when(val result = appDataManager.getRemoteData().postNewOrder(body)){
                is ApiResponse.Success<BooleanResponse> -> {
                    var bool = true
                    result.data.data?.let {
                        bool = it
                    }
                    navigator?.handleOrderSuccess(bool)
                }
                is ApiResponse.Error -> {
                    navigator?.handleOrderError(result.message)
                }
            }
        }
    }

    fun subscribeTotal(): LiveData<CartTotal> {
        return appDataManager.getDbRepository().subscribeCartTotal()
    }

    fun getAllData(): ArrayList<Cart> {
        return appDataManager.getDbRepository().getCartItems()
    }

    suspend fun removeDb(){
        return appDataManager.getDbRepository().clearItemsInDb()
    }

    fun getMenuPositionList(): ArrayList<Pair<String,Int>> {
        with(TypeMenu){
            return arrayListOf(
                FOODEMI_HOLDER_FOODEMI_FIRST           to 0,
                FOODEMI_HOLDER_FOODEMI_LIST_ORDER      to 1,
                FOODEMI_HOLDER_PAYMENT_M               to 2,
                FOODEMI_HOLDER_PAYMENT_DETAILS         to 3,
                FOODEMI_HOLDER_FOODEMI_MENU_OTHER      to 4
            )
        }
    }

    init {
        getAllData()
    }

    suspend fun updateItems(sealedCart: SealedCart){
        return appDataManager.getDbRepository().updateItem(sealedCart)
    }

}