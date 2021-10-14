package com.prj.dailybook.contract

import android.content.Context
import com.prj.dailybook.BasePresenter
import com.prj.dailybook.BaseView
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.ScheduleListData
import com.prj.dailybook.util.room.Schedule

interface DailyContract {
    interface View : BaseView<Presenter> {
        override fun init()
        fun setInsert()
        fun setEmptyItem()
        fun showItems()
    }

    interface Presenter : BasePresenter {
        var view : DailyContract.View
        var schedule : ScheduleListData
        var adapterModel : ScheduleAdapterContract.Model
        var adapterView : ScheduleAdapterContract.View
        fun insertSchedule(context: Context,schedule : Schedule)
        fun getSchedule(context: Context)
        fun updateYn(context:Context , Yn : String, Id : Int)
    }
}