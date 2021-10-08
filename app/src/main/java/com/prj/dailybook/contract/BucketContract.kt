package com.prj.dailybook.contract

import androidx.fragment.app.Fragment
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView

interface BucketContract {

    interface View : BaseView<Presenter> {
        override fun init()
        fun trasferList()
    }

    interface Presenter : BasePresenter {
        fun setView(view : BucketContract.View)
    }
}