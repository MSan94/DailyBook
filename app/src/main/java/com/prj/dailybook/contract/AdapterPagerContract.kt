package com.prj.dailybook.contract

import com.prj.dailybook.util.model.Book

interface AdapterPagerContract {

    interface View{

        fun notifyAdapter()
    }

    interface Model{
        fun addItems(items : ArrayList<Book>)
        fun clearItem()
    }
}