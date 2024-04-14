package com.arjungupta08.hotelmanager

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PropertyAdapter(val context: Context, private val itemList: List<PropertyDataClass>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_properties, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]

        holder.hotelName.text = item.hotelName
        holder.location.text = item.location
        holder.ratingText.text = item.ratingText
        holder.rooms.text = "${item.rooms} Rooms"
        Glide.with(context).load(item.imageUrl).into(holder.propertyLogo)

    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val hotelName: TextView = itemView.findViewById(R.id.propertyName)
        val location: TextView = itemView.findViewById(R.id.location)
        val ratingText: TextView = itemView.findViewById(R.id.ratingText)
        val rooms: TextView = itemView.findViewById(R.id.rooms)
        val propertyLogo : ImageView = itemView.findViewById(R.id.propertyLogo)

    }
}