package com.prj.dailybook.contract

import android.content.Context
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.BookListData

interface DailyContract {
    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        fun getBooks()
    }
}