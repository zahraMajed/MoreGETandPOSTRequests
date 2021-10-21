package com.example.moregetandpostrequests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.single_item.view.*

class RecycelerAdapter (val namesList:ArrayList<String>):RecyclerView.Adapter<RecycelerAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.single_item,
            parent,
            false
        ))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var name=namesList[position]
        holder.itemView.apply {
            tvNameItem.text=name
        }
    }

    override fun getItemCount(): Int= namesList.size
}