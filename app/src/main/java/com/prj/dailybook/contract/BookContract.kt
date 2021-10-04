package com.prj.dailybook.contract

import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.Book

interface BookContract {


    interface View : BaseView<Presenter> {
        override fun init()
        fun showProgress()
        fun hideProgress()
    }

    interface Presenter : BasePresenter {
        fun setView(view : BookContract.View)
        fun getBookList(search : String)
    }

}