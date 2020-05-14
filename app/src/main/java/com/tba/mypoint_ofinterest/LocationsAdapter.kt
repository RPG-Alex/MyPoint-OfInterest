package com.tba.mypoint_ofinterest


import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.saved_location_layout.view.*

class LocationsAdapter(private val locationList:List<LocationItem>):RecyclerView.Adapter<LocationsAdapter.LocationViewHolder>() {

    companion object {
        private lateinit var context: Context

        fun setContext(con:Context){
            context=con
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {

        //passes the layout to the recycler

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.saved_location_layout, parent, false)

        return LocationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {



            fun EditALocation(context: Context) {
                val intent = Intent(context, EditLocation::class.java).apply {
                    putExtra("TITLE_DATA",holder.textView1.text.toString())
                }
                context.startActivity(intent)
            }
            fun showOnMap(View: Context){
                val location: String = holder.textView3.text.toString()
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("google.navigation:q=$location")
                )
                context.startActivity(intent)

            }

        val currentItem = locationList[position]


        holder.imageButton.setImageResource(currentItem.image)

        holder.textView1.text = currentItem.text1
        holder.textView2.text = currentItem.text2
        holder.textView3.text = currentItem.text3
        holder.editButton.setOnClickListener {
           EditALocation(context)
        }
        holder.imageButton.setOnClickListener {
            showOnMap(context)
        }


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
