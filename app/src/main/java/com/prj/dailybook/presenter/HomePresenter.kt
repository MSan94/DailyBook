package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import com.prj.dailybook.contract.HomeContract
import java.net.URLDecoder
import kotlin.coroutines.CoroutineContext

class HomePresenter : HomeContract.Presenter {

    private var view : HomeContract.View? = null


    override fun setView(view: HomeContract.View) {
        this.view = view
    }

}