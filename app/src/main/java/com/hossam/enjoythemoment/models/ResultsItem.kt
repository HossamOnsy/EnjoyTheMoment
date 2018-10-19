package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class ResultsItem(@SerializedName("reference")
                       val reference: String = "",
                       @SerializedName("types")
                       val types: List<String>?,
                       @SerializedName("scope")
                       val scope: String = "",
                       @SerializedName("icon")
                       val icon: String = "",
                       @SerializedName("name")
                       val name: String = "",
                       @SerializedName("opening_hours")
                       val openingHours: OpeningHours,
                       @SerializedName("rating")
                       val rating: Double = 0.0,
                       @SerializedName("geometry")
                       val geometry: Geometry,
                       @SerializedName("vicinity")
                       val vicinity: String = "",
                       @SerializedName("id")
                       val id: String = "",
                       @SerializedName("photos")
                       val photos: List<PhotosItem>?,
                       @SerializedName("place_id")
                       val placeId: String = "")