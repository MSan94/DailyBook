package com.prj.dailybook.util.listener

import com.prj.dailybook.util.model.Book

interface BookInterface {
    fun getModel(model : Book)
    fun goBuy(url : String)
    fun goStore(model : Book)
}