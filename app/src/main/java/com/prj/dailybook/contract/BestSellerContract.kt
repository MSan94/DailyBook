package com.prj.dailybook.contract

import android.content.Context
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData


interface BestSellerContract {

    interface View : BaseView<Presenter> {
        override fun init()
        fun setBucketBook(type : String)
    }

    interface Presenter : BasePresenter {
        var view : BestSellerContract.View
        var book : BookListData
        var adapterModel : AdapterContract.Model
        var adapterView : AdapterContract.View

        fun getBestSeller(context: Context, isClear: Boolean)
        fun saveBook(context: Context, book : Book)
    }

}