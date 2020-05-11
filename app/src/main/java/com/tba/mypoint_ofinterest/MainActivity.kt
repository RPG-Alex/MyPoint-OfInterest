package com.tba.mypoint_ofinterest

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Pressing GPS icon will go to adding a new location
        fabAddLocation.setOnClickListener { view -> addLocation(view) }
        }

    fun addLocation(x:View?){
        val locationIntent: Intent = Intent(this,AddLocation::class.java)
        startActivity(locationIntent)
    }
    }




