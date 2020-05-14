package com.tba.mypoint_ofinterest


import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import kotlinx.android.synthetic.main.activity_add_location.*


class AddLocation : AppCompatActivity() {

    //variables needed for location grab
    lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    lateinit var locationRequest: LocationRequest
    lateinit var locationCallback: LocationCallback
    var REQUEST_CODE = 1000
    lateinit var userLocation: Location

    //Deal with permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE -> {
                if (grantResults.size > 0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                    else
                        Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_location)

        //Check for permission!

        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        )
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE
            )
        else {
            buildLocationRequest()
            buildLocationCallback()

            //Create fused provider client
            fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

            //get location

            //start getting location
            //btnGetLocation.setOnClickListener(View.OnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )
                    //return@OnClickListener
                }
                fusedLocationProviderClient.requestLocationUpdates(
                    locationRequest, locationCallback,
                    Looper.myLooper()
                )
                //make the button invisible after clicked
                btnGetLocation.visibility = View.INVISIBLE
           // })


        }
        //Listen for clicking add location then turn off GPS and proceed to next view
        btn_accept.setOnClickListener {
            if (this::userLocation.isInitialized){
                if (ActivityCompat.checkSelfPermission(
                        this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this,
                        arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                        REQUEST_CODE
                    )

                }
                fusedLocationProviderClient.removeLocationUpdates(locationCallback)
                locationCallback = object : LocationCallback() {
                    override fun onLocationResult(p0: LocationResult?) {
                        userLocation =
                            p0!!.locations.get(p0!!.locations.size - 1) //get last location
                    }
                }
                var long = userLocation.longitude
                var lat = userLocation.latitude

                addInfo(long, lat)

            } else {

                Toast.makeText(this,"Unable to Ad Location, Please Make sure you have clicked to add your current location",Toast.LENGTH_LONG).show()
            }

        }
    }

    private fun buildLocationCallback() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(p0: LocationResult?) {
                var location = p0!!.locations.get(p0!!.locations.size - 1) //get last location
                userLocation = location
                txtLocation.text =
                    location.latitude.toString() + "/" + location.longitude.toString() + "and accuracy" + location.accuracy.toString()
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

    //This gives intent and takes the GPS data to the next view to be combined with user input
    fun addInfo(long:Double,lat:Double) {

        val infoIntent: Intent = Intent(this, LocationInfo::class.java).apply {
            putExtra("LAT_DATA", lat)
            putExtra("LONG_DATA",long)
        }
            startActivity(infoIntent)
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out)

    }


    //Stop getting location data if back button is pressed
    override fun onSupportNavigateUp(): Boolean {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        return super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
        super.onBackPressed()
    }


}


