package com.prj.dailybook.util.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bucket")
data class Bucket(
    @PrimaryKey val itemId: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String?,
    @ColumnInfo val coverSmallUrl: String?,
    @ColumnInfo val type : String,
    @ColumnInfo val readYn : String = "0"
)
