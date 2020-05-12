package com.tba.mypoint_ofinterest

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_location.*
import kotlinx.android.synthetic.main.activity_location_info.*
import kotlinx.android.synthetic.main.activity_location_info.txtLocation
import java.io.File
import kotlin.properties.Delegates

class LocationInfo : AppCompatActivity() {

    //This is used for working with the files we will store the user data in
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor

    //Here we create the variables we will pass the GPS coordinates to
    private var longitude by Delegates.notNull<Double>()
    private var latitude by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)

        //Here we set our GPS variabls to the intent Extras
        longitude = intent.getDoubleExtra("LONG_DATA", 0.0)
        latitude = intent.getDoubleExtra("LAT_DATA", 0.0)
        //This Displays the GPS Coordinates for the user above the info input fields
        txtLocation.text = longitude.toString() + "/" + latitude.toString()





        btnSaveLocation.setOnClickListener {
            if (txtTitle.text.isEmpty() || txtShortDescription.text.isEmpty() || txtShortDescription.text.toString().length > 50 || txtTitle.text.toString().length > 20) {
                Toast.makeText(
                    this,
                    "Please create a title and input a short description (title no more than 20 characters long and description no more than 50 characters)",
                    Toast.LENGTH_LONG
                ).show()
            } else {

                //This grabs the user input so we can save it (We already have the GPS Above)
                var title = txtTitle.text.toString()
                var description = txtShortDescription.text.toString()


                //Based on example given in week 11. This will make a file that is called title.txt. Then fill it with the relevant data
                preferences = getSharedPreferences("$title", Context.MODE_PRIVATE)
                editor = preferences.edit()
                editor.putString("Title", title.toLowerCase())
                editor.putString("Description", description)
                editor.putString("Longitude", longitude.toString())
                editor.putString("Latitude", latitude.toString())
                editor.apply()


                saveInfo()

            }

        }

    }


    //These functions all set the intent to main activity, regardless of method user uses
    fun saveInfo() {


        //This sets the intent we want (go back to home screen)
        val saveIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(saveIntent)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp()
        startActivity(Intent(this, MainActivity::class.java))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, MainActivity::class.java))
    }
}



