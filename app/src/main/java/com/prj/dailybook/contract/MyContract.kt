package com.prj.dailybook.contract

import android.content.Context
import androidx.fragment.app.Fragment
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView

interface MyContract {

    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        fun setView(view : MyContract.View)
        fun initData(context : Context)
    }

}