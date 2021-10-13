package com.prj.dailybook.util.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true) val Id: Int = 0,
    @ColumnInfo val date: String,
    @ColumnInfo val contents: String,
    @ColumnInfo val Yn: String
)
