package com.prj.dailybook.presenter

import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.contract.MusicContract
import com.prj.dailybook.util.model.BookListData

class MusicPresenter : MusicContract.Presenter {

    override lateinit var view: BookContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterContract.Model
    override lateinit var adapterView: AdapterContract.View

}