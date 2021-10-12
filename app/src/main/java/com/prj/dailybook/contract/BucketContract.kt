package com.prj.dailybook.contract

import android.content.Context
import androidx.fragment.app.Fragment
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.Bucket
import com.prj.dailybook.util.model.BucketListData

interface BucketContract {

    interface View : BaseView<Presenter> {
        override fun init()
        fun trasferList(type : String)
        fun setRecyclerView(type : String, size : Int, checkSize : Int)
        fun setEmptyItem()
    }

    interface Presenter : BasePresenter {
        var view : BucketContract.View
        var bucket : BucketListData
        var adapterModel : BucketAdapterContract.Model
        var adapterView : BucketAdapterContract.View

        fun getBucketItem(context: Context, type : String)
    }
}