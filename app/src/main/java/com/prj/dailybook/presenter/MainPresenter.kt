package com.prj.dailybook.presenter

import com.prj.dailybook.contract.MainContract

class MainPresenter : MainContract.Presenter {

    private var view : MainContract.View? = null

    override fun setView(view: MainContract.View) {
        this.view = view
    }
}