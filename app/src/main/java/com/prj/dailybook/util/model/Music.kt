package com.prj.dailybook.util.model

import com.google.gson.annotations.SerializedName

data class Music(
    @SerializedName("itemId") val itemId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("coverLargeUrl") val coverLargeUrl: String?
)
