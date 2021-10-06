package com.prj.dailybook.util.`interface`

import com.prj.dailybook.util.model.Book
import java.text.FieldPosition

interface DetailInterface {
    fun getModel(model : Book)
    fun goBuy(url : String)
    fun goStore(model : Book)
}