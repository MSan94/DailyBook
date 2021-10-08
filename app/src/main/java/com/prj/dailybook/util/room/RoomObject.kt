package com.prj.dailybook.util.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Bucket::class], version = 2)
abstract class RoomObject : RoomDatabase(){

    abstract fun bucketDao() : BucketDao

    companion object{
        private var INSTANCE : RoomObject? = null
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

        fun delInstance(){
            INSTANCE = null
        }
    }

}