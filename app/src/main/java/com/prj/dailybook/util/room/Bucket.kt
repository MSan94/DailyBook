package com.prj.dailybook.util.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "bucket")
data class Bucket(
    @ColumnInfo val itemId: Long,
    @ColumnInfo val title: String,
    @ColumnInfo val author: String?,
    @ColumnInfo val coverSmallUrl: String?
){
    @PrimaryKey(autoGenerate = true) var id = 0
}
