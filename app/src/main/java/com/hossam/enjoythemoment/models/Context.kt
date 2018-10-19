package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Context(
    @SerializedName("location") val location: LocationX,
    @SerializedName("type") val type: String,
    @SerializedName("href") val href: String
)