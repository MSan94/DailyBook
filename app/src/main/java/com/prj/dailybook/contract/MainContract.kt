package com.prj.dailybook.contract

import androidx.fragment.app.Fragment
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView

interface MainContract {


    interface View : BaseView<Presenter> {
        override fun init()
        fun initFragment()
        fun replaceFragment(fragment : Fragment)
    }

    interface Presenter : BasePresenter {
        fun setView(view : MainContract.View)
    }

}