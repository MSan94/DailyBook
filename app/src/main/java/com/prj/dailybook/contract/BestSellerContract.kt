package com.prj.dailybook.contract

import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView


interface BestSellerContract {

    interface View : BaseView<Presenter> {
        override fun init()
    }

    interface Presenter : BasePresenter {
        fun setView(view : BestSellerContract.View)
    }

}