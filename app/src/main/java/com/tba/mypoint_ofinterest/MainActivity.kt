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
            val getGps = prefs.getString("Longitude", "") + "," + prefs.getString("Latitude", "")
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

    //a function to open the an activity to edit this location. Need to figure this shit out


/*
        val exampleList = generateDummyList(100)

        recycler_view.adapter=ExampleAdapter(exampleList)
        recycler_view.layoutManager=LinearLayoutManager(this)
        recycler_view.setHasFixedSize(true)
    }

    private fun generateDummyList(size:Int):List<ExampleItem>{
        val list = ArrayList<ExampleItem>()
        for (i in 0 until size){
            val drawable = when (i % 3){
                0 -> R.drawable.android_guy
                1 -> R.drawable.talkie
                else -> R.drawable.bizniz
            }
            val item = ExampleItem(drawable,"Item $i","Line 2")
            list += item
        }
        return list
    }
}



Modify This so that you can chop off the .xml for the files
//To get the selected item

String item = (String) sp.getSelectedItem();
    //remove .xml from the file name
    String preffile = item.substring(0, item.length()-4);

    SharedPreferences sp2 = getSharedPreferences(preffile, MODE_PRIVATE);
    Map<String, ?> map = sp2.getAll();

    for (Entry<String, ?> entry : map.entrySet()){
        System.out.println("key is "+ entry.getKey() + " and value is " + entry.getValue());
    }
 */