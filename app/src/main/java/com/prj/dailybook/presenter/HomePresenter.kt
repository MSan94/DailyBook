package com.prj.dailybook.presenter

import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.contract.MainContract

class HomePresenter : HomeContract.Presenter {
    private var view : HomeContract.View? = null
    override fun setView(view: HomeContract.View) {
        this.view = view
    }
}