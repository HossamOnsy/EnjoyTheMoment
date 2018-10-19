package com.hossam.enjoythemoment.services

import android.Manifest
import android.annotation.SuppressLint
import android.app.IntentService
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import android.media.AudioManager
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.util.Log
import android.widget.Toast
import com.hossam.enjoythemoment.network.RXJavaClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class ServiceTryout : IntentService(ServiceTryout::class.simpleName) {


    var locationManager: LocationManager? = null

    @SuppressLint("CheckResult")
    override fun onHandleIntent(intent: Intent?) {

        if (locationManager == null)
            locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager


        try {
            val myLocation = getLastKnownLocation()
            val latitude = myLocation!!.latitude
            val longitude = myLocation.longitude
            var s = "Latitude : " + latitude + "Longitude : " + longitude
            var mainHandler = Handler(getMainLooper())

            var rxClient = RXJavaClient()
            var rxJavaApi = rxClient.init(latitude, longitude)
            var explore = "$latitude,$longitude;r=200"
            var checkForPlacesToMuteDevice = false
            rxJavaApi.getNearbyPlaces(explore,"xWrrunA384GAvtK9VCbL","lNCUijUTBUvnvyQbNQ7PsQ")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ x ->
                    if (x.results.items.size > 0) {
                        for (j in x.results.items) {
                            if (j.title.toLowerCase().contains("cinema") || j.title.toLowerCase().contains("restaurant")) {
                                checkForPlacesToMuteDevice = true
                                break
                            } else {
                                checkForPlacesToMuteDevice = false
                            }
                        }
                        if (checkForPlacesToMuteDevice)
                            mainHandler.post {

                                // Do your stuff here related to UI, e.g. show toast
                                val mode = this.getSystemService(Context.AUDIO_SERVICE) as AudioManager
                                if (mode.ringerMode != AudioManager.RINGER_MODE_SILENT)
                                    mode.ringerMode = AudioManager.RINGER_MODE_VIBRATE
                                Log.e("Latitude", s)
                                Toast.makeText(applicationContext, s, Toast.LENGTH_SHORT).show()
                            }

                    }


                }, { error ->
                    Log.e("Error", error.message)
                })


        } catch (e: Exception) {
            Log.e("Error", e.message)
        }

    }


    private fun getLastKnownLocation(): Location? {
        locationManager = applicationContext.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val providers = locationManager!!.getProviders(true)
        var bestLocation: Location? = null
        var l: Location? = null
        for (provider in providers) {
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) == PackageManager.PERMISSION_GRANTED
            )
                l = locationManager!!.getLastKnownLocation(provider) ?: continue
            if (l != null) {
                if (bestLocation == null || l.getAccuracy() < bestLocation.accuracy) {
                    // Found best last known location: %s", l);
                    bestLocation = l
                }
            }
        }
        return bestLocation
    }


}
