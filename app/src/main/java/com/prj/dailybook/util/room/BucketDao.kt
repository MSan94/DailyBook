package com.prj.dailybook.util.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.Music

@Dao
interface BucketDao {
    @Query("SELECT * FROM bucket")
    fun getAll() : List<Bucket>

    @Insert
    fun insertBook(book : Bucket)

    @Insert
    fun insertMusic(music : Bucket)

    @Query("DELETE FROM bucket")
    fun deleteAll()

    @Query("SELECT count(*) FROM bucket WHERE itemId =:itemId")
    fun validItem(itemId : Long) : Int

    @Query("SELECT * FROM bucket WHERE type =:type")
    fun selectBucket(type : String) : List<Bucket>

    @Delete
    fun deleteBook(book : Bucket)

    @Delete
    fun deleteMusic(music : Bucket)



}