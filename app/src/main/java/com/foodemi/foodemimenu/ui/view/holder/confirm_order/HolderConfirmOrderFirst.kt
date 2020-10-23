package com.foodemi.foodemimenu.ui.view.holder.confirm_order

import android.text.Editable
import android.text.TextWatcher
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.databinding.ViewHolderConfirmOrderFirstMenuBinding
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnOrderTypeSelectListener
import kotlinx.android.synthetic.main.view_holder_confirm_order_first_menu.view.*
import java.text.FieldPosition

@Suppress("DEPRECATION")
class HolderConfirmOrderFirst(
    val binding: ViewHolderConfirmOrderFirstMenuBinding,
    private val listener: OnOrderTypeSelectListener?
): RecyclerView.ViewHolder(binding.root) {

    fun bind(){
        with(binding){
            setupRadioButton()
            setupEditCatatanOrder()
            executePendingBindings()
        }
    }

    private fun setupRadioButton(){
        with(binding){

            var textSelected: String

            val radioButton1 = groupOfRadio.findViewById<RadioButton>(groupOfRadio.checkedRadioButtonId)
            textSelected                  = radioButton1.text.toString()
            listener?.getOrderTypeFoodemi(textSelected)

            groupOfRadio.setOnCheckedChangeListener { group, checkedId ->
                val radioButton = group.findViewById<RadioButton>(checkedId)
                when(checkedId){
                    R.id.view_radio_dine_in -> {
                        viewRadioTakeAway.isChecked = false
                        viewRadioDineIn.isChecked   = true
                        viewRadioDineIn.setTextColor(root.resources.getColor(R.color.white))
                        viewRadioTakeAway.setTextColor(root.resources.getColor(R.color.FOODEMI_COLOR_TEXT_MAIN))
                        textSelected = radioButton.text.toString()
                        listener?.getOrderTypeFoodemi(textSelected)
                    }

                    R.id.view_radio_take_away -> {
                        viewRadioDineIn.isChecked   = false
                        viewRadioTakeAway.isChecked = true
                        viewRadioDineIn.setTextColor(root.resources.getColor(R.color.FOODEMI_COLOR_TEXT_MAIN))
                        viewRadioTakeAway.setTextColor(root.resources.getColor(R.color.white))
                        textSelected = radioButton.text.toString()
                        listener?.getOrderTypeFoodemi(textSelected)
                    }
                }
            }
        }
    }

    private fun setupEditCatatanOrder(){
        with(binding){
            val catatanOrder        = viewTextCatatanOrder.text.toString()
            viewTextCatatanOrder.addTextChangedListener(object : TextWatcher{

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                }

                override fun afterTextChanged(s: Editable?) {
                    listener?.getCurrentCatatanOrder(s.toString())
                }

            })

        }
    }

}