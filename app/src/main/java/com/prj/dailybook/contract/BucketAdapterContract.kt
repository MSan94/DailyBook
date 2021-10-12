package com.prj.dailybook.contract

import com.prj.dailybook.util.room.Bucket


interface BucketAdapterContract {


    interface View{
        fun notifyAdapter()
    }

    interface Model{
        fun addItems(items : ArrayList<Bucket>)
        fun clearItem()
    }


}