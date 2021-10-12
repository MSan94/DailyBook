package com.prj.dailybook.util.model

import android.content.Context

object BucketListData {

    fun getBucketListData(context: Context, size: Int, bucket: List<Bucket>, type:String) : ArrayList<Bucket>{
        val list = ArrayList<Bucket>()
        for(i in 0 until size){
            list.add(Bucket(bucket[i].itemId,bucket[i].title,bucket[i].author,bucket[i].coverSmallUrl,bucket[i].type, bucket[i].readYn))
        }
        return list
    }

}