package com.foodemi.foodemimenu.ui.view.feature.fragment.menu.menulist

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.lifecycle.Observer
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
import com.foodemi.foodemimenu.ui.view.widget.snackBar.SnackBarHelper
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.menu.menulist.MenuListViewModel
import com.foodemi.foodemimenu.utils.constant.Const
import com.google.android.material.snackbar.Snackbar
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fragment_menu_list.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@Suppress("DEPRECATION")
class FragmentMenuList : CoreFragment<FragmentMenuListBinding, MenuListViewModel>(),
    MenuNavigation, AdapterMenuCollection.MenuItemListener {

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

    private val listMenu: MutableList<ModelMenuSectioned.MenuFoodemi>     = mutableListOf()

    private val listMenuAll        = ArrayList<ModelMenuSectioned.MenuFoodemi>()
    private val listMenuToSubmit   = ArrayList<ModelMenuSectioned.MenuFoodemi>()

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


    override fun handleMenuError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

    override fun handleMenuSuccess(menuList: List<ModelMenuSectioned.SectionMenu>) {
        return MenuListSimple(menuList, adapter).setAllList()
    }

    private fun setup() {
        initRecyclerView()
        showVisibility()
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

    override fun onButtonMenuClicked(menu: ModelMenuSectioned.MenuFoodemi) {
        CoroutineScope(Dispatchers.Main).launch {

            val slideUp: Animation = AnimationUtils.loadAnimation(
                context,
                R.anim.button_menu_list_animation_show
            )

            if (listMenu.size >= 0){
                view_btn_cart.startAnimation(slideUp)
                view_btn_cart.visibility = View.VISIBLE
            }
            else {
                view_btn_cart.visibility = View.GONE
            }

            menuListViewModel?.updateItems(menu)
            showSnackbarCustom("berhasil tambah menu : ${menu.menuName}, x${menu.itemQuantity}")


            listMenu.add(menu)
            listMenuAll.addAll(listMenu)
            mapProductWithCart()
        }
    }

    private fun setMyCustomToast(message: String){
        val toast = Toasty.custom(
            requireContext(),
            message,
            R.drawable.background_ios_alert,
            R.color.white,
            Toast.LENGTH_SHORT,
            false,
            true
        )
        toast.setGravity(Gravity.BOTTOM, 0, -10000)
        return toast.show()
    }

    private fun showSnackbarCustom(message: String) {
        val snack = Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT)
        SnackBarHelper.configSnackbar(requireContext(), snack)
        snack.show()
    }

    private fun mapProductWithCart(){
        CoroutineScope(Dispatchers.Main).launch{
            val menuMap = menuListViewModel?.mapWithCart(listMenuAll)
            listMenuToSubmit.addAll(menuMap!!)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showVisibility(){
        menuListViewModel?.subscribeTotal()?.observe(
            this.viewLifecycleOwner,
            Observer { cartTotal ->
                val readableValue =
                    PriceCheckerValue().checkIntValueToString(cartTotal.totalAmount.toString())
                view_text_total_price_menu_list.text = readableValue

                if (listMenu.size > 2) {
                    view_text_totalItems_menu_list.text = "${listMenu.size} items"
                } else {
                    view_text_totalItems_menu_list.text = "${listMenu.size} items"
                }

                val lt = menuListViewModel?.getAllCartItem()

                view_btn_cart.setOnClickListener {
                    Log.e("MENU_PRICE_TOT", cartTotal.totalAmount.toString())
                    Log.e("MENU_ITEM_TOT", cartTotal.totalItems.toString())
                    Log.e("MENU_ITEM", lt.toString())
                    Log.e("MENU_TOTAL", cartTotal.toString())
                }
            })
    }

    override fun onResume() {
        super.onResume()
        mapProductWithCart()
    }

}