package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Geometry(@SerializedName("viewport")
                    val viewport: Viewport,
                    @SerializedName("location")
                    val location: Location
)