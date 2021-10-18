package com.prj.dailybook.contract

import androidx.fragment.app.Fragment
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.BookListData

interface MusicContract {


    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        var view : BookContract.View
        var book : BookListData
        var adapterModel : AdapterContract.Model
        var adapterView : AdapterContract.View
    }

}