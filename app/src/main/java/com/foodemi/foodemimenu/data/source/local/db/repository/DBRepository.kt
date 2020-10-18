package com.foodemi.foodemimenu.data.source.local.db.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.foodemi.foodemimenu.data.source.local.db.AppDatabase
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.data.source.local.db.source.SealedCart
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartTotal
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DBRepository @Inject constructor(private val mAppDatabase: AppDatabase) {

    private val cartTotal: MutableLiveData<CartTotal> by lazy {
        MutableLiveData<CartTotal>()
    }

    private val items = ArrayList<Cart>()

    fun getCartItems(): ArrayList<Cart> {
        val cartItem = ArrayList<Cart>()
        cartItem.addAll(items)
        return cartItem
    }

    fun subscribeCartTotal(): LiveData<CartTotal> {
        return cartTotal
    }

    suspend fun removeItem(id: String): Int = coroutineScope {
        val deleteResults = async { mAppDatabase.cartDao().deleteCartItem(id) }
        updateTotals()
        return@coroutineScope deleteResults.await()
    }

    suspend fun insertCartItem(cart: Cart){

    }

    suspend fun updateItem(saleable: SealedCart) = coroutineScope {

        val readResult = async { mAppDatabase.cartDao().findById(saleable.getId()) }

        val cartItem = readResult.await()

        if (cartItem != null) {

            if (saleable.getQuantity() == 0) {
                mAppDatabase.cartDao().deleteCartItem(saleable.getId())
            } else {
                cartItem.itemQuantity            = saleable.getQuantity()
                val updateResult = async { mAppDatabase.cartDao().updateCartItem(saleable.getId(), saleable.getQuantity()) }
                updateResult.await()
            }
            updateTotals()
        } else {
            if (saleable.getQuantity() > 0) {
                val insertResult = async { mAppDatabase.cartDao().insertCartItem(Cart(saleable)) }
                insertResult.await()
                updateTotals()
            }
        }
    }

    suspend fun <T : SealedCart> mapWithCart(saleable: ArrayList<T>): ArrayList<T> = coroutineScope {

        val products = saleable as ArrayList<SealedCart>

        val result = async {

            products.forEachIndexed { index, product ->

                val cartItem:Cart? = try {
                    items.first { cartItem ->
                        product.getId().equals(cartItem.getId(), false)
                    }
                } catch (exception:Exception){
                    null
                }

                if(cartItem != null){
                    products[index].itemQuantity = cartItem.getQuantity()
                }else{
                    products[index].itemQuantity = 0
                }
            }
        }
        result.await()

        return@coroutineScope products as ArrayList<T>
    }

    fun updateTotals() {

        CoroutineScope(Dispatchers.Default).launch {

            items.clear()

            val readResult = async { items.addAll( mAppDatabase.cartDao().getAllCartItem()) }

            readResult.await()

            var totalCost = 0

            items.forEach {
                totalCost += it.getTotal()
            }

            cartTotal.postValue(CartTotal(totalCost, items.size))
        }

    }

}