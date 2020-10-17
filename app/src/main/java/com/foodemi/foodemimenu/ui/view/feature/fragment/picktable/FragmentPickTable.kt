package com.foodemi.foodemimenu.ui.view.feature.fragment.picktable

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.foodemi.foodemimenu.BR
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.FragmentPickTableBinding
import com.foodemi.foodemimenu.ui.navigation.AppNavigation
import com.foodemi.foodemimenu.ui.view.base.CoreFragment
import com.foodemi.foodemimenu.ui.viewmodel.factory.ViewModelFactory
import com.foodemi.foodemimenu.ui.viewmodel.feature.fragment.picktable.PickTableViewModel
import com.foodemi.foodemimenu.utils.constant.Const
import javax.inject.Inject


class FragmentPickTable : CoreFragment<FragmentPickTableBinding, PickTableViewModel>(),
    AppNavigation {

    @Inject
    lateinit var factory: ViewModelFactory

    private var pickTableViewModel: PickTableViewModel? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val layoutId: Int
        get() = R.layout.fragment_pick_table

    override val DefaultbackPressed: Boolean
        get() = true

    override val viewModel: PickTableViewModel
        get() {
            pickTableViewModel = ViewModelProvider(this, factory).get(PickTableViewModel::class.java)
            return pickTableViewModel as PickTableViewModel
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pickTableViewModel?.setNavigator(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setup()
    }

    private fun setup(){
        setupDropDownTableNum()
        setupButtonClick()
    }

    private fun setupDropDownTableNum(){
        with(getViewDataBinding()){

            val tableNum = resources.getStringArray(R.array.TableNumber)

            val nomorMejaAdapter = ArrayAdapter(
                requireContext(),
                android.R.layout.select_dialog_item,
                tableNum
            )

            viewDropdownPicktable.setAdapter(nomorMejaAdapter)
            //viewDropdownPicktable.threshold = 1
            viewDropdownPicktable.setOnClickListener {
                viewDropdownPicktable.showDropDown()
            }
        }
    }

    private fun setupButtonClick(){
        with(getViewDataBinding()){
            viewBtnActionToMenuList.setOnClickListener {
                val bundle = Bundle()
                bundle.putString(Const.TABLE_NUMBER_PICK, viewDropdownPicktable.text.toString())
                getNavController().navigate(R.id.pickTable_to_menu_list, bundle)
                //Toast.makeText(requireContext(), "Nomor Meja: ${viewDropdownPicktable.text}", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun handleError(message: String?) {
        Log.e("Error_Message", message.toString())
    }

}