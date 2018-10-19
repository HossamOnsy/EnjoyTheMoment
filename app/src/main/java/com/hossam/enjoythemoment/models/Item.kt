package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("position") val position: List<Double>,
    @SerializedName("distance") val distance: Int,
    @SerializedName("title") val title: String,
    @SerializedName("averageRating") val averageRating: Int,
    @SerializedName("category") val category: Category,
    @SerializedName("icon") val icon: String,
    @SerializedName("vicinity") val vicinity: String,
    @SerializedName("having") val having: List<Any>,
    @SerializedName("type") val type: String,
    @SerializedName("href") val href: String,
    @SerializedName("id") val id: String
)