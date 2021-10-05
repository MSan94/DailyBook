package com.prj.dailybook.contract

import android.content.Context
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData

interface BookContract {

    interface View : BaseView<Presenter> {
        override fun init()
        fun showProgress()
        fun hideProgress()
        fun search(search: String)
        fun recyclerInit();
    }

    interface Presenter : BasePresenter {
        var view : BookContract.View
        var book : BookListData
        var adapterModel : AdapterContract.Model
        var adapterView : AdapterContract.View

        fun getBookList(search : String, context: Context, isClear: Boolean)
    }

}