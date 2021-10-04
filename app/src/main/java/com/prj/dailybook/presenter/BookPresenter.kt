package com.prj.dailybook.presenter

import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.BookContract
import java.util.logging.Handler

class BookPresenter : BookContract.Presenter {

    private var view : BookContract.View? = null
    override fun setView(view: BookContract.View) {
        this.view = view
    }

    override fun getBookList(search : String) {
        view?.showProgress()
    }
}