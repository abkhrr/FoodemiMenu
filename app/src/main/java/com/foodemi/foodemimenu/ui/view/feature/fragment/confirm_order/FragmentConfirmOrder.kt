package com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.BR
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.model.response.*
import com.foodemi.foodemimenu.data.source.local.db.model.Cart
import com.foodemi.foodemimenu.databinding.FragmentConfirmOrderBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.navigation.OrderNavigator
import com.foodemi.foodemimenu.ui.view.adapter.AdapterCollectionConfirmOrder
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnCartItemEmptyListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnItemUpdateListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnGetCurrentPaymentMethodeListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnOrderTypeSelectListener
import com.foodemi.foodemimenu.ui.view.widget.price.PriceCheckerValue
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.confirm_order.ConfirmOrderViewModel
import com.foodemi.foodemimenu.utils.constant.Const
import kotlinx.android.synthetic.main.fragment_confirm_order.*
import kotlinx.android.synthetic.main.fragment_confirm_order.view_btn_cart
import kotlinx.android.synthetic.main.fragment_menu_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

class FragmentConfirmOrder : CoreFragment<FragmentConfirmOrderBinding, ConfirmOrderViewModel>(),
    OrderNavigator {

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
            confirmOrderViewModel = ViewModelProvider(this, factory).get(ConfirmOrderViewModel::class.java)
            return confirmOrderViewModel as ConfirmOrderViewModel
        }

    private val adapter                   = AdapterCollectionConfirmOrder()
    private var tableNumber: String?      = null
    private var tipeOrder: String?        = null
    private var catatanOrder: String?     = null
    private var jenisPembayaran: String?  = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        confirmOrderViewModel?.setNavigator(this)
        if (arguments != null){
            tableNumber  = arguments?.getString(Const.TABLE_NUMBER_PICK)
        }
        adapter.setCartTotal(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupViewItemsText()
        setupRv()
        setupButtonConfirm()
    }

    private fun setupViewItemsText(){
        confirmOrderViewModel?.subscribeTotal()?.observe(viewLifecycleOwner, { cartTotal ->
            val readableValue = PriceCheckerValue().checkIntValueToStringWithRp(cartTotal.totalAmount.toString())
            view_text_total_amount_confirm_order.text = readableValue
        })
    }

    private fun setupRv(){
        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL

        preSetupAdapter()
        getViewDataBinding().viewMenuListCollectionRV.adapter = adapter
        getViewDataBinding().viewMenuListCollectionRV.layoutManager = layoutManager
        getViewDataBinding().viewMenuListCollectionRV.itemAnimator = null
        getViewDataBinding().lifecycleOwner = this
    }

    private fun preSetupAdapter(){
        adapter.realMenuPositionList = viewModel.getMenuPositionList()
        val cartItems= viewModel.getAllData()

        adapter.setCart(cartItems)
        adapter.setTableNumberInt(tableNumber.toString())
        addAdapterUpdateCounterListener()
        addEmptyViewForCart()
        getCurrentOrderTypesListener()
        getCurrentPaymentMethodsListener()
        setPaddingToRv(cartItems)
    }

    private fun addAdapterUpdateCounterListener() {

        adapter.addItemUpdateListener(object : OnItemUpdateListener<Cart> {

            override fun onItemUpdated(item: Cart, position: Int, view: View) {
                CoroutineScope(Dispatchers.Main).launch {
                    confirmOrderViewModel?.updateItems(item)
                }
            }

        })

    }

    private fun addEmptyViewForCart(){
        adapter.addEmptyItemListener(object : OnCartItemEmptyListener<Cart> {

            override fun isEmptyView(count: Int, items: ArrayList<Cart>) {
                if (count == 0 && items.isEmpty()) {
                    getNavController().navigate(R.id.confirm_order_to_menuList)
                }
            }

        })
    }

    private fun getCurrentOrderTypesListener(){
        adapter.addCurrentOrderTypesListener(object : OnOrderTypeSelectListener{
            override fun getOrderTypeFoodemi(orderTypes: String) {
                tipeOrder    = orderTypes
            }

            override fun getCurrentCatatanOrder(orderNotes: String) {
                catatanOrder = orderNotes
            }

        })
    }

    private fun getCurrentPaymentMethodsListener(){
        adapter.addCurrentPaymentMethodListener(object : OnGetCurrentPaymentMethodeListener<PaymentMethodeModel>{
            override fun getPaymentMethodListener(items: PaymentMethodeModel) {
                jenisPembayaran = items.text
            }
        })
    }

    private fun setPaddingToRv(cartItems: ArrayList<Cart>) {

        when (cartItems.size) {
            1 -> {
                changePixelDp(120, getViewDataBinding().viewMenuListCollectionRV)
            }
            2 -> {
                changePixelDp(110, getViewDataBinding().viewMenuListCollectionRV)
            }
            else -> {
                changePixelDp(100, getViewDataBinding().viewMenuListCollectionRV)
            }
        }

        Log.e("PADDONG", getViewDataBinding().viewMenuListCollectionRV.paddingBottom.toString())
    }

    private fun changePixelDp(dp: Int, view: RecyclerView){
        val density      = requireContext().resources.displayMetrics.density
        val paddingPixel : Int  = (dp * density + 0.5f).toInt()
        view.setPadding(0, view.paddingTop, 0, paddingPixel)
    }

    private fun setupButtonConfirm(){
        with(getViewDataBinding()){
            viewBtnCart.setOnClickListener {

                val max                    = 1000000
                val min                    = 100000
                val rand                   = Random()
                val randomNum: Int         = rand.nextInt(max - min + 1) + min


                val now          = Calendar.getInstance()
                val orderDate       = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(now.time).toString()

                val orderId                 = "0809/$tableNumber/$orderDate/$randomNum"
                val kasirName               = "Putu"
                val totalPrice         = getTotalPrice()
                val queueNumber             = 1
                val orderServed             = false
                val orderPrepared           = true
                val paymentStatus           = false
                val isGojek                 = false
                val tableNum        = convertNullStringToString(tableNumber)
                val orderType       = convertNullStringToString(tipeOrder)
                val paymentMethod   = convertNullStringToString(jenisPembayaran)
                val orderNotes      = convertNullStringToString(catatanOrder)

                val a                      = confirmOrderViewModel?.getAllData()
                val listMenuInOrder: ArrayList<MenuOrder>   = arrayListOf()

                a?.let { menu ->
                    for (i in 0 until menu.size){
                        val prId                          = MenuId(menu[i].getId())
                        val qty                      = menu[i].getQuantity()
                        val newOrder                      = MenuOrder(prId, qty)
                        val lst          = listOf<MenuOrder>(newOrder)
                        listMenuInOrder.addAll(lst)
                    }
                }

                val orderStatus = OrderStatus(orderPrepared, orderServed)

                val body = ModelOrder(
                    orderId,
                    tableNum,
                    orderDate,
                    orderType,
                    paymentMethod,
                    orderNotes,
                    totalPrice,
                    queueNumber,
                    kasirName,
                    isGojek,
                    orderStatus,
                    paymentStatus,
                    listMenuInOrder
                )
                confirmOrderViewModel?.sendOrder(body)
            }
        }
    }

    private fun getTotalPrice(): Int {
        var total = 0
        confirmOrderViewModel?.subscribeTotal()?.observe(viewLifecycleOwner, {
            total = it.totalAmount
        })
        return total
    }

    private fun convertNullStringToString(tableNum: String?): String {
        var num = ""
        tableNum?.let {
            num = it
        }
        return num
    }

    override fun handleOrderError(message: String?) {
        Log.e("Error_Message", message.toString())
        CoroutineScope(Dispatchers.Main).launch {
            confirmOrderViewModel?.removeDb()
        }
        getNavController().navigate(R.id.confirm_order_to_order_failed)
    }

    override fun handleOrderSuccess(data: Boolean) {
        if (data){
            Toast.makeText(requireContext(), "Order Berhasil Dibuat!", Toast.LENGTH_SHORT).show()
            CoroutineScope(Dispatchers.Main).launch {
                confirmOrderViewModel?.removeDb()
            }
            val bundle = Bundle()
            bundle.putString(Const.TABLE_NUMBER_PICK, tableNumber)
            getNavController().navigate(R.id.confirm_order_to_order_success, bundle)
        }
    }

}