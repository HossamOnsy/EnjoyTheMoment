package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("context") val context: Context
)