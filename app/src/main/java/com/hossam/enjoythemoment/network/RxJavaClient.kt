package com.hossam.enjoythemoment.network

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RXJavaClient {

//    private val BASE_URL = "https://maps.googleapis.com/maps/api/place/nearbysearch/"
    private val BASE_URL = "https://places.cit.api.here.com/"
//    https://places.cit.api.here.com/
//      places/v1/discover/explore?
//          in=30.0060,30.9707;r=20&Accept-Language=en-US%2Cen%3Bq%3D0.9%2Car%3Bq%3D0.8&app_id=xWrrunA384GAvtK9VCbL&app_code=lNCUijUTBUvnvyQbNQ7PsQ
    lateinit var sMyAPI: RxJavaApi
    private val KEY = "AIzaSyBNl6-SUsRUlkB2rGx-OAf4UkzmH_ljjG0"

    companion object {
        var latitude: Double = 0.0
        var longitude: Double = 0.0
    }

    fun init(lat: Double, long: Double) :RxJavaApi{
        val client = OkHttpClient.Builder()
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        client.addInterceptor(loggingInterceptor);

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        sMyAPI = retrofit.create(RxJavaApi::class.java)
//        sMyAPI.getPlacesResponse(
//            lat, long,
//            "Theatres", KEY
//        )
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()


        //If you break up your API into different interfaces you can initialize them all here.
        return sMyAPI

    }
}