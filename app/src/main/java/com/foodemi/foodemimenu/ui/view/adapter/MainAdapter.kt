package com.foodemi.foodemimenu.ui.view.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.foodemi.foodemimenu.R
import com.foodemi.foodemimenu.data.model.response.ModelMenu
import kotlinx.android.synthetic.main.item_list.view.*

class MainAdapter(private val activity: AppCompatActivity): RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private val itemList: MutableList<ModelMenu.FoodemiMenu> = mutableListOf()

    fun setupMenu(menu: List<ModelMenu.FoodemiMenu>){
        itemList.let {
            itemList.clear()
            itemList.addAll(menu)
            this.notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MainViewHolder(inflater.inflate(R.layout.item_list, parent, false ))
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(position)

        //holder.itemView.setOnClickListener {
        //
        //    val namaMenu    = itemList[position].menuName
        //    val gambarMenu  = itemList[position].menuImage
        //    val descMenu    = itemList[position].menuDesc
        //
        //    val bundle              = Bundle()
        //
        //    bundle.putString("namaMenu", namaMenu)
        //    bundle.putString("gambarMenu", gambarMenu)
        //    bundle.putString("descMenu", descMenu)
        //
        //    val intent = Intent(activity, Ma::class.java)
        //    intent.putExtras(bundle)
        //    activity.startActivity(intent)
        //}

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    inner class MainViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        fun bind(position: Int){

            val gambar = itemList[position].menuImage
            val nama   = itemList[position].menuName

            Glide.with(itemView.context).load(gambar).into(itemView.gambarMenu)
            itemView.namaMenu.text = nama
        }
    }

}