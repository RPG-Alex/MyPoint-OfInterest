package com.tba.mypoint_ofinterest

import android.content.ClipDescription
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_location_info.*
import kotlin.properties.Delegates

class LocationInfo : AppCompatActivity() {
    private var longitude by Delegates.notNull<Double>()
    private var latitude by Delegates.notNull<Double>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)
        btnSaveLocation.setOnClickListener { view -> saveInfo(view) }

        longitude = intent.getDoubleExtra("LONG_DATA", 0.0)
        latitude = intent.getDoubleExtra("LAT_DATA", 0.0)

        txtLocation.text = longitude.toString() + "/" + latitude.toString()
    }

    fun saveInfo(x: View?) {
        val saveIntent: Intent = Intent(this, MainActivity::class.java)
        startActivity(saveIntent)
    }
}



