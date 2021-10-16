package com.prj.dailybook.util.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ScheduleDao {

    @Query("SELECT * FROM schedule")
    fun getAll() : List<Schedule>

    @Insert
    fun insertSchedule(schedule : Schedule)

    @Query("UPDATE schedule SET Yn =:Yn WHERE id =:id")
    fun updateYn(Yn : String , id : Int)

    @Query("SELECT count(*) FROM schedule")
    fun getCountSchedule() : Int

    @Query("SELECT count(*) FROM schedule WHERE Yn = 'Y'")
    fun getCountYnSchedule() : Int

    @Query("DELETE FROM schedule")
    fun deleteAll()
}