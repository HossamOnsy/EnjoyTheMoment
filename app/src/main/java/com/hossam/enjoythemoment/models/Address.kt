package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("text") val text: String,
    @SerializedName("district") val district: String,
    @SerializedName("city") val city: String,
    @SerializedName("county") val county: String,
    @SerializedName("country") val country: String,
    @SerializedName("countryCode") val countryCode: String
)