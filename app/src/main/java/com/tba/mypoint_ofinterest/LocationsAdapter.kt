package com.tba.mypoint_ofinterest

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.saved_location_layout.view.*

class LocationsAdapter(private val locationList:List<LocationItem>):RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        //passes the layout to the recycler
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.saved_location_layout, parent, false)

        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        val currentItem = locationList[position]

        holder.imageButton.setImageResource(currentItem.image)

        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.textView3.text = currentItem.text3
 

    }
    //sets the count to the number of locations in the list
    override fun getItemCount() = locationList.size

    class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val imageButton: ImageButton = itemView.btn_location
        val textView1: TextView = itemView.text_view_1
        val textView2: TextView = itemView.text_view_2
        val textView3: TextView = itemView.text_view_3
        val editButton:Button = itemView.btn_edit
    }
}