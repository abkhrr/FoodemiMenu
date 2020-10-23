package com.foodemi.foodemimenu.ui.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.data.model.response.PaymentMethodeModel
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.databinding.*
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.FragmentConfirmOrder
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnCartItemEmptyListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnItemUpdateListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnGetCurrentPaymentMethodeListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnOrderTypeSelectListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist.utils.CartTotal
import com.foodemi.foodemimenu.ui.view.holder.EmptyHolder
import com.foodemi.foodemimenu.ui.view.holder.confirm_order.HolderConfirmOrderFirst
import com.foodemi.foodemimenu.ui.view.holder.confirm_order.HolderConfirmOrderFour
import com.foodemi.foodemimenu.ui.view.holder.confirm_order.HolderConfirmOrderSecond
import com.foodemi.foodemimenu.ui.view.holder.confirm_order.HolderConfirmOrderThird
import com.foodemi.foodemimenu.utils.android.type.TypeMenu
import com.foodemi.foodemimenu.utils.constant.Const

class AdapterCollectionConfirmOrder: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var updateItemListener: OnItemUpdateListener<Cart>?   = null
    private var emptyItemListener: OnCartItemEmptyListener<Cart>? = null
    private var tableNumber: String?                              = null
    private var cartTotalItems: FragmentConfirmOrder?             = null

    private var getCurrentPaymentMethodListener: OnGetCurrentPaymentMethodeListener<PaymentMethodeModel>?    = null
    private var getCurrentOrderTypesListener: OnOrderTypeSelectListener?                                     = null

    private var arrayCart: ArrayList<Cart>                        = ArrayList()
    private var viewPositionList : ArrayList<Pair<String,Int>>    = arrayListOf()
    var realMenuPositionList : ArrayList<Pair<String,Int>>        = arrayListOf()
        set(value) {
            field.apply {
                clear()
                addAll(value)
            }
            viewPositionList.clear()
            viewPositionList.addAll(value)
        }

    fun setTableNumberInt(tablenum: String){
        tableNumber = "Detail Pemesanan Meja $tablenum"
    }

    fun setCart(cartList: ArrayList<Cart>){
        arrayCart.let {
            arrayCart = cartList
            notifyDataSetChanged()
        }
    }

    fun setCartTotal(total: FragmentConfirmOrder){
        cartTotalItems.let {
            cartTotalItems = total
            notifyDataSetChanged()
        }
    }

    fun addItemUpdateListener(listener: OnItemUpdateListener<Cart>) {
        this.updateItemListener = listener
    }

    fun addEmptyItemListener(listener: OnCartItemEmptyListener<Cart>) {
        this.emptyItemListener = listener
    }

    fun addCurrentPaymentMethodListener(listener: OnGetCurrentPaymentMethodeListener<PaymentMethodeModel>){
        this.getCurrentPaymentMethodListener = listener
    }

    fun addCurrentOrderTypesListener(listener: OnOrderTypeSelectListener){
        this.getCurrentOrderTypesListener = listener
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return when(viewType) {
            TypeMenu.ITEM_FOODEMI_FIRST -> {
                HolderConfirmOrderFirst(ViewHolderConfirmOrderFirstMenuBinding.inflate(layoutInflater, parent,false), getCurrentOrderTypesListener)
            }
            TypeMenu.ITEM_FOODEMI_LIST_ORDER  -> {
                HolderConfirmOrderSecond(ViewHolderCollectionConfirmOrderBinding.inflate(layoutInflater, parent,false), arrayCart, updateItemListener, emptyItemListener)
            }
            TypeMenu.ITEM_FOODEMI_PAYMENT_M -> {
                HolderConfirmOrderThird(ViewHolderLayoutChoosePaymentBinding.inflate(layoutInflater, parent,false), getCurrentPaymentMethodListener)
            }
            TypeMenu.ITEM_FOODEMI_PAYMENT_DETAILS -> {
                HolderConfirmOrderFour(ViewHolderTotalDetailsConfirmOrderBinding.inflate(layoutInflater, parent,false), cartTotalItems, tableNumber.toString())
            }
            else -> {
                EmptyHolder(HolderEmptyItemBinding.inflate(layoutInflater, parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return viewPositionList.size
    }

    override fun getItemViewType(position: Int): Int {
        with(TypeMenu){
            return getMenuListHolder(viewPositionList.getOrElse(position){ Const.FOODEMI_URL_DEFAULT to RecyclerView.NO_POSITION}.first)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder.itemViewType) {
            TypeMenu.ITEM_FOODEMI_FIRST -> {
                (holder as? HolderConfirmOrderFirst)?.bind()
            }
            TypeMenu.ITEM_FOODEMI_LIST_ORDER -> {
                (holder as? HolderConfirmOrderSecond)?.bind()
            }
            TypeMenu.ITEM_FOODEMI_PAYMENT_M -> {
                (holder as? HolderConfirmOrderThird)?.bind()
            }
            TypeMenu.ITEM_FOODEMI_PAYMENT_DETAILS -> {
                (holder as? HolderConfirmOrderFour)?.bind()
            }
            else -> {
                (holder as? EmptyHolder)?.bind()
            }
        }
    }
}