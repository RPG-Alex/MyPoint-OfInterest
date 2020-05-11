package com.tba.mypoint_ofinterest

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_add_location.*
import kotlinx.android.synthetic.main.activity_location_info.*
import kotlinx.android.synthetic.main.activity_main.*

class AddLocation : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        lateinit var fusedLocationProviderClient: FusedLocationProviderClient
        lateinit var locationRequest: LocationRequest
        lateinit var locationCallback: LocationCallback
        var REQUEST_CODE = 1000

        btn_accept.setOnClickListener{view->addInfo(view)}
    }
    fun addInfo(x:View?){
        val infoIntent: Intent = Intent(this,LocationInfo::class.java)
        startActivity(infoIntent)
    }
}


/*

class MainActivity : AppCompatActivity() {


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE->{
                if (grantResults.size > 0)
                {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this,"Permission Granted",Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this,"Permission Denied",Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check for permission!

        if(ActivityCompat.shouldShowRequestPermissionRationale(this,android.Manifest.permission.ACCESS_FINE_LOCATION))
            ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
        else {
            buildLocationRequest()
            buildLocationCallback()

            //Create fused provider client
            fusedLocationProviderClinet = LocationServices.getFusedLocationProviderClient(this)

            //set event
            btn_start_update.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
                    return@OnClickListener
                }
                fusedLocationProviderClinet.requestLocationUpdates(locationRequest,locationCallback,
                    Looper.myLooper())

                //change the state of the buttons
                btn_start_update.isEnabled =   !btn_start_update.isEnabled
                btn_stop_update.isEnabled = !btn_stop_update.isEnabled
            })

            btn_stop_update.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(this,android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(this, arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),REQUEST_CODE)
                    return@OnClickListener
                }
                fusedLocationProviderClinet.removeLocationUpdates(locationCallback)

                //change the state of the buttons
                btn_start_update.isEnabled =   !btn_start_update.isEnabled
                btn_stop_update.isEnabled = !btn_stop_update.isEnabled
            })
        }
    }

    private fun buildLocationCallback() {
        locationCallback = object :LocationCallback(){
            override fun onLocationResult(p0: LocationResult?) {
                var location = p0!!.locations.get(p0!!.locations.size-1) //get last location
                txt_location.text = location.latitude.toString()+"/"+location.longitude.toString()
            }
        }
    }

    private fun buildLocationRequest() {
        locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000
        locationRequest.smallestDisplacement = 10f
    }
}

 */