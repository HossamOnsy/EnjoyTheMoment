package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Results(
    @SerializedName("items") val items: List<Item>
)