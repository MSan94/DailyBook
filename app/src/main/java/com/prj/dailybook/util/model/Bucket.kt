package com.prj.dailybook.util.model


data class Bucket(
    val itemId: Long,
    val title: String,
    val author: String?,
    val coverSmallUrl: String?,
    val type : String,
    val readYn : String = "0"
)
