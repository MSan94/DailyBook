package com.prj.dailybook.util.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("itemId") val itemId : Long,
    @SerializedName("title") val title : String,
    @SerializedName("description") val description : String,
    @SerializedName("coverSmallUrl") val coverSmallUrl : String,
    @SerializedName("categoryId") val categoryId : String,
    @SerializedName("categoryName") val categoryName : String,
    @SerializedName("author") val author : String,
    @SerializedName("rank") val rank : String
    )
