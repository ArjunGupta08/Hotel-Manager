package com.arjungupta08.hotelmanager

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arjungupta08.hotelmanager.dashboard.PropertyProfileActivity
import com.bumptech.glide.Glide
import java.io.Serializable

class PropertyAdapter(val context: Context, private val itemList: List<PropertyData>) : RecyclerView.Adapter<PropertyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_properties, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position].property
        val fullItem = itemList[position]

        holder.hotelName.text = item.hotelName
        holder.location.text = item.location
        holder.ratingText.text = item.ratingText
        holder.rooms.text = "${item.rooms} Rooms"
        Glide.with(context).load(item.imageUrl).into(holder.propertyLogo)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, PropertyProfileActivity::class.java)
            intent.putExtra("documentId", fullItem.documentId)
            intent.putExtra("hotelName", fullItem.property.hotelName)
            intent.putExtra("imageUrl", fullItem.property.imageUrl)
            intent.putExtra("location", fullItem.property.location)
            intent.putExtra("ratingText", fullItem.property.ratingText)
            intent.putExtra("rooms", fullItem.property.rooms)
            context.startActivity(intent)
        }
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