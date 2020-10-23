package com.foodemi.foodemimenu.ui.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.model.response.PaymentMethodeModel
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnPaymentListSelectedListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnGetCurrentPaymentMethodeListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnOrderTypeSelectListener
import kotlinx.android.synthetic.main.bottom_sheet_payment_methode.view.*

class AdapterDialogChoosePayment(val context: Context): RecyclerView.Adapter<AdapterDialogChoosePayment.ViewHolderChoosePayment>() {

    val list: MutableList<PaymentMethodeModel> = mutableListOf()
    private var lastSelectedPosition           = -1
    private var paymentMethodeSelectorListener: OnPaymentListSelectedListener<PaymentMethodeModel>? = null

    fun giveList(listPaymentMethode: List<PaymentMethodeModel>){
        list.let {
            it.clear()
            it.addAll(listPaymentMethode)
            this.notifyDataSetChanged()
        }
    }

    fun addPaymentMethodeChangeListener(listener: OnPaymentListSelectedListener<PaymentMethodeModel>){
        this.paymentMethodeSelectorListener  = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderChoosePayment {
        val inflater = LayoutInflater.from(context).inflate(R.layout.bottom_sheet_payment_methode, parent, false)
        return ViewHolderChoosePayment(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolderChoosePayment, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = list.size

    inner class ViewHolderChoosePayment(itemView: View): RecyclerView.ViewHolder(itemView){

        fun bind(position: Int){
            with(itemView){
                val lisItems = list[position]
                radioBtnSelectedPaymentMethod.isChecked = position == lastSelectedPosition
                setupId(lisItems)
                setupRadioButton(lisItems)
            }
        }

        private fun setupId(items: PaymentMethodeModel){
            with(itemView){
                view_type_icon_payment_methode.setImageResource(items.image)
                view_type_text_payment_methode.text = items.text
            }
        }

        private fun setupRadioButton(items: PaymentMethodeModel){
            with(itemView){
                radioBtnSelectedPaymentMethod.setOnClickListener {
                    val copyOfLastCheckedPosition: Int = lastSelectedPosition
                    lastSelectedPosition = adapterPosition
                    notifyItemChanged(copyOfLastCheckedPosition)
                    notifyItemChanged(lastSelectedPosition)
                    paymentMethodeSelectorListener?.setItemPaymentMethode(items)
                }
            }
        }

    }

}