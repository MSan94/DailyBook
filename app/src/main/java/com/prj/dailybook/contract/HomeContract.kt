package com.prj.dailybook.contract

import android.content.Context
import androidx.appcompat.widget.AppCompatButton
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.Music

interface HomeContract {

    interface View : BaseView<Presenter> {
        override fun init()
        fun setMusic(musicList : List<Music>)
        fun setForeBook(foreBook : Book)
        fun setHealthBook(healthBook : Book)
        fun textReSize()
        fun reFreshRate(totalCount : Int , yCount : Int)
    }

    interface Presenter : BasePresenter {
        var view : HomeContract.View
        var book : BookListData
        var adapterModel : AdapterPagerContract.Model
        var adapterView : AdapterPagerContract.View
        fun getData(context: Context, isClear: Boolean)
        fun getMusic()
        fun getForeBook()
        fun getHealthBook()
        fun getBookRatio(context : Context)
    }
}