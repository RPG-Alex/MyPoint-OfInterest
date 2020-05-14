package com.tba.mypoint_ofinterest

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.saved_location_layout.*
import java.io.File

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        LocationsAdapter.setContext(this)
        populateRecycler()


        //Pressing GPS icon will go to adding a new location
        fabAddLocation.setOnClickListener { view -> addLocation(view) }
    }

    fun addLocation(x: View?) {
        val locationIntent: Intent = Intent(this, AddLocation::class.java)
        startActivity(locationIntent)
        overridePendingTransition(R.anim.slide_in, R.anim.fade_out)

    }

    fun createRecyclerContent(list: Array<String>): ArrayList<LocationItem> {

        //Get the shared preference file data
        var size = list.size
        val itemList = ArrayList<LocationItem>()
        for (i in 0 until size) {
            val name = list[i].substring(0, list[i].length - 4)
            lateinit var prefs: SharedPreferences

            prefs = getSharedPreferences(name, Context.MODE_PRIVATE)

            val getTitle = prefs.getString("Title", "")
            val getDescription = prefs.getString("Description", "")
            val getGps = prefs.getString("Latitude", "") + ", " + prefs.getString("Longitude", "")
            val listItem = LocationItem(
                R.drawable.ic_location_icon,
                getTitle.toString(),
                getDescription.toString(),
                getGps
            )

            itemList += listItem

        }
        return itemList

    }


    fun populateRecycler() {
        val sharedPrefsDir = File(applicationInfo.dataDir, "shared_prefs")
        if (sharedPrefsDir.exists() && sharedPrefsDir.isDirectory()) {
            //verifying that the directory is found and that it has the names
            val locateList = sharedPrefsDir.list();
            //This should be sending the number of recycler items to my createRecyclerContent function, to populate the recycler

            val locationsList = createRecyclerContent(locateList)

            recycler_view.adapter = LocationsAdapter(locationsList)
            (recycler_view.adapter as LocationsAdapter).notifyDataSetChanged()
            recycler_view.layoutManager = LinearLayoutManager(this)
            recycler_view.setHasFixedSize(true)


        }

    }
}
