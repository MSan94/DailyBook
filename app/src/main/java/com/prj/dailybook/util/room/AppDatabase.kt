package com.prj.dailybook.util.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

private var INSTANCE : RoomObject? = null

@Database(entities = [Bucket::class], version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun bucketDao() : BucketDao
}

fun getAppDatabase(context : Context) : AppDatabase{
    // 버전이동 마이크레이션
    val migration_1_2 = object : Migration(1,2){
        override fun migrate(database: SupportSQLiteDatabase) {
            //뭘 바꿧는지
            database.execSQL("CREATE TABLE `BUCKET` ( `itemId` LONG, `review` TEXT, " + "PRIMARY KEY(`itemId`))")
        }
    }

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "BookSearchDB"
    ).addMigrations() //마이그레이션 추가
        .build()
}
