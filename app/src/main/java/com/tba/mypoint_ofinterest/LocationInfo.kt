package com.tba.mypoint_ofinterest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_location_info.*

class LocationInfo : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_location_info)
        btnSaveLocation.setOnClickListener{view ->saveInfo(view)}
    }
    fun saveInfo(x: View?){
        val saveIntent: Intent = Intent(this,MainActivity::class.java)
        startActivity(saveIntent)
    }
}
