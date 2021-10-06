package com.prj.dailybook.contract

import androidx.appcompat.widget.AppCompatButton
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView

interface HomeContract {

    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        fun setView(view : HomeContract.View)
    }
}