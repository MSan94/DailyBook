package com.prj.dailybook.util.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bucket::class, Schedule::class], version = 4)
abstract class RoomObject : RoomDatabase(){

    abstract fun bucketDao() : BucketDao
    abstract fun ScheduleDao() : ScheduleDao

    companion object{
        private var INSTANCE : RoomObject? = null
        private var Sch_INSTANCE : RoomObject? = null

        fun getInstance(context : Context) : RoomObject? {
            if(INSTANCE == null){
                synchronized(RoomObject::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoomObject::class.java, "bucket.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
        fun getScheduleInstance(context : Context) : RoomObject? {
            if(Sch_INSTANCE == null){
                synchronized(RoomObject::class){
                    Sch_INSTANCE = Room.databaseBuilder(context.applicationContext,
                        RoomObject::class.java, "schedule.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return Sch_INSTANCE
        }

        fun delInstance(){
            INSTANCE = null
        }

        fun delSchInstance(){
            Sch_INSTANCE = null
        }

    }

}