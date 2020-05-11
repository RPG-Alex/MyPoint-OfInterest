package com.tba.mypoint_ofinterest

import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_location_info.*

class LocationInfo : AppCompatActivity() {
    val longitude = intent.getStringExtra("LONG_DATA")
    val latitude = intent.getStringExtra("LAT_DATA")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)
        btnSaveLocation.setOnClickListener{view ->saveInfo(view)}
       textView2.text = longitude.toString()+"/"+latitude.toString()
    }
    fun saveInfo(x: View?){
        val saveIntent: Intent = Intent(this,MainActivity::class.java)
        startActivity(saveIntent)
    }
}


