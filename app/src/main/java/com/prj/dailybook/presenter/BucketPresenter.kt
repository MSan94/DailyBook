package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.prj.dailybook.contract.BucketAdapterContract
import com.prj.dailybook.contract.BucketContract
import com.prj.dailybook.util.model.BucketListData
import com.prj.dailybook.util.room.Bucket
import com.prj.dailybook.util.room.RoomObject
import kotlin.concurrent.thread

class BucketPresenter : BucketContract.Presenter {

    override lateinit var view: BucketContract.View
    override lateinit var bucket: BucketListData
    override lateinit var adapterModel: BucketAdapterContract.Model
    override lateinit var adapterView: BucketAdapterContract.View

    private var roomObject: RoomObject? = null

    override fun getBucketItem(context: Context, type: String) {
        thread(start = true) {
            when (type) {
                "1" -> {
                    var checkSize = 0
                    roomObject = RoomObject.getInstance(context)
                    var list = roomObject?.bucketDao()?.selectBucket("book")
                    if (list?.size!! > 0) {
                        for(i in list.indices){
                            if(list[i].readYn == "1"){
                                checkSize++
                            }
                        }
                        view.setRecyclerView("1", list.size,checkSize)
                        adapterModel.addItems(list as ArrayList<Bucket>)
                    } else {
                        view.setEmptyItem()
                    }
                    RoomObject.delInstance()
                    Log.d("ThreadTest", " 끝")
                }
            }
        }
    }

    override fun delBucketBook(context: Context, bucket: Bucket) {
        thread(start = true){
            roomObject = RoomObject.getInstance(context)
            roomObject?.bucketDao()?.selectDelBook(bucket.itemId)
            var checkSize = 0
            var list = roomObject?.bucketDao()?.selectBucket("book")
            if (list != null) {
                for(i in list.indices){
                    if(list[i].readYn == "1"){
                        checkSize++
                    }
                }
                view.setRecyclerView("1", list.size,checkSize)
                adapterModel.addItems(list as ArrayList<Bucket>)
            } else {
                view.setEmptyItem()
            }
            RoomObject.delInstance()
        }
    }

    override fun updateBucket(context: Context, readYn: String, itemId: Long, type: String) {
        Log.d("TestThread", "실행")
        thread(start = true){
            var changeYn = if(readYn == "1") "0" else "1"
            roomObject = RoomObject.getInstance(context)
            roomObject?.bucketDao()?.updateBucket(changeYn,itemId,type)
            RoomObject.delInstance()
        }
    }
}