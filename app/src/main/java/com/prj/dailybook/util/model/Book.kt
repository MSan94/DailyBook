package com.prj.dailybook.util.model

import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("itemId") val itemId: Long,
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String?,
    @SerializedName("coverSmallUrl") val coverSmallUrl: String?,
    @SerializedName("categoryId") val categoryId: String?,
    @SerializedName("categoryName") val categoryName: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("rank") val rank: String?,
    @SerializedName("priceSales") val priceSales: String?,
    @SerializedName("coverLargeUrl") val coverLargeUrl: String?,
    @SerializedName("priceStandard") val priceStandard: String?,
    @SerializedName("publisher") val publisher: String?,
    @SerializedName("customerReviewRank") val customerReviewRank: Double?,
    @SerializedName("additionalLink") val additionalLink: String?,
    @SerializedName("discountRate") val discountRate: String?
)
