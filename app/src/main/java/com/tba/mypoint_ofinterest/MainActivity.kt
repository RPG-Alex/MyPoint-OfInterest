package com.tba.mypoint_ofinterest

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPrefsDir = File(applicationInfo.dataDir, "shared_prefs")
        if(sharedPrefsDir.exists() && sharedPrefsDir.isDirectory()){
            val list = sharedPrefsDir.list();
            list.forEach {
                //txtTest.text = txtTest.text.toString()+it.toString()
            }
        }

        //Pressing GPS icon will go to adding a new location
        fabAddLocation.setOnClickListener { view -> addLocation(view) }
        }

    fun addLocation(x:View?){
        val locationIntent: Intent = Intent(this,AddLocation::class.java)
        startActivity(locationIntent)
    }
    }




