package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class NearByPlacesResponse(
    @SerializedName("results") val results: Results,
    @SerializedName("search") val search: Search
)