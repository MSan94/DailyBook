package com.prj.dailybook.presenter

import com.prj.dailybook.contract.BestSellerContract

class BestSellerPresenter : BestSellerContract.Presenter {

    private var view : BestSellerContract.View? = null


    override fun setView(view: BestSellerContract.View) {
        this.view = view
    }

}