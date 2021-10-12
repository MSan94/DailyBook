package com.prj.dailybook.util.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Bucket(
    val itemId: Long,
    val title: String,
    val author: String?,
    val coverSmallUrl: String?,
    val type : String,
    val readYn : String = "0"
)
