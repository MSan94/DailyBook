package com.prj.dailybook.presenter

import android.os.Handler
import android.util.Log
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.util.model.Book

class BookPresenter : BookContract.Presenter {

    private var view : BookContract.View? = null
    private var books = mutableListOf<Book>()
    override fun setView(view: BookContract.View) {
        this.view = view
    }

    override fun getBookList(search : String){
    }


    override fun onDestroy() {
        view = null
    }

}