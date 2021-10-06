package com.prj.dailybook.contract

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.BookListData

interface HomeContract {

    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        var view : HomeContract.View
        var book : BookListData
        var adapterModel : AdapterPagerContract.Model
        var adapterView : AdapterPagerContract.View
        fun getData(context: Context, isClear: Boolean)
    }
}