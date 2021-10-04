package com.prj.dailybook.contract

import android.widget.AdapterView
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.model.Book

interface BookAdapterContract {

    interface View{
        fun notifyAdapter()
        fun setOnClickListener(clickListener: DetailInterface)
    }

    interface Model{
        fun setData(books : MutableList<Book>)
    }

}