package com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist

import android.annotation.SuppressLint
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
import com.foodemi.foodemimenu.data.model.response.ModelMenuSectioned
import com.foodemi.foodemimenu.databinding.FragmentMenuListBinding
import com.foodemi.foodemimenu.ui.navigation.MenuNavigation
import com.foodemi.foodemimenu.ui.view.adapter.AdapterMenuCollection
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.view.widget.nestedMenuList.MenuListSimple
import com.foodemi.foodemimenu.ui.view.widget.price.PriceCheckerValue
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist.MenuListViewModel
import com.foodemi.foodemimenu.utils.constant.Const
import kotlinx.android.synthetic.main.fragment_menu_list.*
import okhttp3.internal.notifyAll
import javax.inject.Inject


class FragmentMenuList : CoreFragment<FragmentMenuListBinding, MenuListViewModel>(),
    MenuNavigation, AdapterMenuCollection.MenuItemListener {

    private val listToCart: MutableList<ModelMenuSectioned.MenuFoodemi>     = mutableListOf()
    private val totalListItems: MutableList<ModelMenuSectioned.MenuFoodemi> = mutableListOf()

    @Inject
    lateinit var factory: ViewModelFactory

    private var menuListViewModel: MenuListViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_menu_list

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: MenuListViewModel
        get() {
            menuListViewModel = ViewModelProvider(this, factory).get(MenuListViewModel::class.java)
            return menuListViewModel as MenuListViewModel
        }

    private var tableNumber: String? = null

    private val adapter     = AdapterMenuCollection()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuListViewModel?.setNavigator(this)
        if (arguments != null){
            tableNumber = arguments?.getString(Const.TABLE_NUMBER_PICK)
        }
        adapter.setListener(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun addDefaultItems(){
        val item = ModelMenuSectioned.MenuFoodemi(
            id = "",
            menuCategory = "",
            menuName = "0",
            menuImage = "",
            menuPrice = 0,
            menuDiscount = 0,
            menuQty = 0,
            menuDesc = "",
            isAvailable = false,
            isRecommended = false,
            menuPromo = "",
            isPromo = false,
            isDiscount = false,
            discountPrice = 0
        )
        listToCart.add(0, item)
    }

    override fun handleMenuError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

    override fun handleMenuSuccess(menuList: List<ModelMenuSectioned.SectionMenu>) {
        return MenuListSimple(menuList, adapter).setAllList()
    }

    private fun setup(){
        initRecyclerView()
        setupButton()
    }

    private fun initRecyclerView(){
        getViewDataBinding().lifecycleOwner = this

        val layoutManager = LinearLayoutManager(activity)
        layoutManager.orientation = RecyclerView.VERTICAL

        getViewDataBinding().lifecycleOwner = this
        getViewDataBinding().viewMenuListCollectionRV.adapter = adapter
        getViewDataBinding().viewMenuListCollectionRV.layoutManager = layoutManager
        getViewDataBinding().viewMenuListCollectionRV.itemAnimator = null
    }

    @SuppressLint("SetTextI18n")
    override fun onButtonMenuClicked(menu: ModelMenuSectioned.MenuFoodemi) {

        val slideUp: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.button_menu_list_animation_show
        )
        val slideDown: Animation = AnimationUtils.loadAnimation(
            context,
            R.anim.button_menu_list_animation_hide
        )

        val targetItem = listToCart.singleOrNull {
            it.id == menu.id
        }

        menu.let {
            if (isAlreadyInCart(it.id)){
                menu.menuPrice = menu.menuPrice
                for (i in 0 until listToCart.size){
                    if (listToCart[i].id == it.id){
                        listToCart[i].menuQty++
                        val price = listToCart[i].menuPrice * listToCart[i].menuQty
                        listToCart[i].menuPrice = price
                    }
                }
            }
            else {
                listToCart.add(menu)
            }

            totalListItems.add(menu)

            if (totalListItems.isEmpty() && listToCart.isEmpty()){
                //view_btn_cart.startAnimation(slideDown)
                view_btn_cart.visibility = View.GONE
            } else {
                view_btn_cart.startAnimation(slideUp)
                view_btn_cart.visibility = View.VISIBLE

                val grandTotalItem = totalListItems.size
                if (grandTotalItem > 1){
                    view_text_totalItems_menu_list.text  = "$grandTotalItem items"
                }
                else {
                    view_text_totalItems_menu_list.text  = "$grandTotalItem item"
                }

                for (i in 0 until listToCart.size){
                    var total = 0
                    total += listToCart[i].menuPrice
                    val readableValue   = PriceCheckerValue().checkIntValueToString(total.toString())
                    view_text_total_price_menu_list.text = readableValue
                }
            }
        }
    }

    private fun setupButton(){
        view_btn_cart.setOnClickListener {
            Log.e("LIST CARD", listToCart.toString())
            Log.e("LIST CARD 2", totalListItems.toString())

            Log.e("LIST CARD SIZE", listToCart.size.toString())
            Log.e("LIST CARD 2 SIZE", totalListItems.size.toString())
        }
    }

    private fun isAlreadyInCart(targetItemId: String): Boolean {
        var isAlreadyInCart = false
        for (i in 0 until listToCart.size) {
            if (targetItemId == listToCart[i].id) {
                isAlreadyInCart = true
                break
            }
        }
        return isAlreadyInCart
    }

}