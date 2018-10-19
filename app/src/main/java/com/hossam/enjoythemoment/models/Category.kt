package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("id") val id: String,
    @SerializedName("title") val title: String,
    @SerializedName("href") val href: String,
    @SerializedName("type") val type: String,
    @SerializedName("system") val system: String
)