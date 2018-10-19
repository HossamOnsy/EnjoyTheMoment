package com.hossam.enjoythemoment.network

import com.google.gson.JsonObject
import com.hossam.enjoythemoment.models.NearByPlacesResponse
import com.hossam.enjoythemoment.models.PlacesResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query


interface RxJavaApi {
//   base URl https://maps.googleapis.com/maps/api/place/nearbysearch/
    //key=AIzaSyBNl6-SUsRUlkB2rGx-OAf4UkzmH_ljjG0

    @GET("json?" +
            "location={latitude},{longitude}" +
            "&radius=1" +
            "&types={types}" +
            "&sensor=true" +
            "&key={key}")

    fun getPlacesResponse(@Path("latitude") latitude: Double,
                @Path("longitude") longitude: Double,
                @Path("types") types: String,
                @Path("key") key: String
                ): Single<PlacesResponse>


    @GET("places/v1/discover/explore")

    fun getNearbyPlaces(@Query("in") explore : String,
                        @Query("app_id") app_id : String,
                        @Query("app_code") app_code : String
//                        ,
//                        @Header("app_id") app_id : String,
//                        @Header ("app_code") app_code : String
    ): Single<NearByPlacesResponse>



}