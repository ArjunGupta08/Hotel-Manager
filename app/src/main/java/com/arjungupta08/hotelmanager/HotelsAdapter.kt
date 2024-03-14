package com.arjungupta08.hotelmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HotelsAdapter(val context: Context, private val itemList: List<HotelsDataClass>) : RecyclerView.Adapter<HotelsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_properties, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.hotelName.text = item.hotelName
        holder.location.text = item.location
        holder.ratingText.text = item.ratingText
//        holder.rooms.text = item.rooms

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hotelName = itemView.findViewById<TextView>(R.id.propertyName)
        val location = itemView.findViewById<TextView>(R.id.location)
        val ratingText = itemView.findViewById<TextView>(R.id.ratingText)

    }
}