package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class LocationX(
    @SerializedName("position") val position: List<Double>,
    @SerializedName("address") val address: Address
)