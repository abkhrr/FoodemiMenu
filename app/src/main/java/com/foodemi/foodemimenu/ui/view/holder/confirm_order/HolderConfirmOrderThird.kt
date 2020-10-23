package com.foodemi.foodemimenu.ui.view.holder.confirm_order

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.model.response.PaymentMethodeModel
import com.foodemi.foodemimenu.databinding.ViewHolderLayoutChoosePaymentBinding
import com.foodemi.foodemimenu.ui.view.adapter.AdapterDialogChoosePayment
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.OnPaymentListSelectedListener
import com.foodemi.foodemimenu.ui.view.feature.fragment.confirm_order.utils.order.OnGetCurrentPaymentMethodeListener


class HolderConfirmOrderThird(
    val binding: ViewHolderLayoutChoosePaymentBinding,
    private val currentPaymentMethodeListener: OnGetCurrentPaymentMethodeListener<PaymentMethodeModel>?
): RecyclerView.ViewHolder(binding.root) {

    private val adapterDialogChoosePayment = AdapterDialogChoosePayment(binding.root.context)

    private var lisOfPayment = listOf(
        PaymentMethodeModel(R.drawable.ic_payment_methode, "Pembayaran Dengan Cash"),
        PaymentMethodeModel(R.drawable.ic_ovo_logo, "Pembayaran Dengan OVO")
    )

    var currentLstPayment: PaymentMethodeModel? = null

    fun bind(){
        with(binding){
            showOnClickMethod()
            executePendingBindings()
        }
    }

    @SuppressLint("InflateParams")
    private fun showOnClickMethod(){

        with(binding){
            layoutChoosePayment.setOnClickListener {
                val alertDialog             = AlertDialog.Builder(root.context)
                val inflater = LayoutInflater.from(root.context)
                val dialogView      = inflater.inflate(R.layout.dialog_choose_payment, null)

                alertDialog.setView(dialogView)
                alertDialog.setCancelable(true)
                alertDialog.setTitle("Pilih Metode Pembayaran")

                val layoutManager = LinearLayoutManager(root.context)
                layoutManager.orientation = RecyclerView.VERTICAL

                val rv = dialogView?.findViewById<RecyclerView>(R.id.dialog_rv_choose_payment_method)
                adapterDialogChoosePayment.giveList(lisOfPayment)
                addPaymentMethodeChangeListener()

                rv?.adapter       = adapterDialogChoosePayment
                rv?.layoutManager = layoutManager
                rv?.itemAnimator  = null

                alertDialog.setPositiveButton("CONFIRM") { dialog, _ ->
                    currentLstPayment?.let {
                        titleConfirmOrderSecond.text = it.text
                        imageConfirmOrderPaymentLogo.setImageResource(it.image)
                        currentPaymentMethodeListener?.getPaymentMethodListener(it)
                    }
                    dialog.dismiss()
                }
                alertDialog.show()
            }
        }
    }

    private fun addPaymentMethodeChangeListener(){
        adapterDialogChoosePayment.addPaymentMethodeChangeListener(object : OnPaymentListSelectedListener<PaymentMethodeModel>{
            override fun setItemPaymentMethode(items: PaymentMethodeModel) {
                currentLstPayment = items
            }
        })
    }

}