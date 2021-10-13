package com.prj.dailybook.contract

import com.prj.dailybook.util.room.Schedule


interface ScheduleAdapterContract {

    interface View{
        fun notifyAdapter()
    }

    interface Model{
        fun addItems(items : ArrayList<Schedule>)
        fun clearItem()
    }
}