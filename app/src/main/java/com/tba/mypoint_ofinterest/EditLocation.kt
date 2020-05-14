package com.tba.mypoint_ofinterest

import android.content.ClipDescription
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_edit_location.*
import kotlin.properties.Delegates

class EditLocation : AppCompatActivity() {

    //This is used for working with the files we will store the user data in
    lateinit var preferences: SharedPreferences
    lateinit var editor: SharedPreferences.Editor
    //This is for getting the specific file data
    lateinit var file:String
    lateinit var title:String
    lateinit var description: String
    var latitude by Delegates.notNull<Double>()
    var longitude by Delegates.notNull<Double>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)

        //pass the file name for editing
        file = intent.getStringExtra("FILE_DATA")

        //get file content
        preferences = getSharedPreferences(file,Context.MODE_PRIVATE)
        editor = preferences.edit()
        txtTitle.setText(preferences.getString("Title",""))
        txtShortDescription.setText(preferences.getString("Description",""))
        txtLocation.text = preferences.getString("Longitude","") + ", "+ preferences.getString("Latitude","")
        //This is used for saving the data
        btnUpdateLocation.setOnClickListener {
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
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)
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
