package com.prj.dailybook.util.listener

import com.prj.dailybook.util.room.Bucket

interface BucketInterface {
    fun delBucket(bucket : Bucket)
    fun updateBucket(readYn : String, itemId: Long, type: String)
}