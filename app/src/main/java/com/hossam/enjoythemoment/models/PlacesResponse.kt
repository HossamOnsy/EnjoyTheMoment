package com.hossam.enjoythemoment.models

import com.google.gson.annotations.SerializedName

data class PlacesResponse(@SerializedName("next_page_token")
                          val nextPageToken: String = "",
                          @SerializedName("results")
                          val results: List<ResultsItem>?,
                          @SerializedName("status")
                          val status: String = "")