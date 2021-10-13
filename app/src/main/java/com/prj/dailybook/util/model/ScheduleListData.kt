package com.prj.dailybook.util.model

import android.content.Context

object ScheduleListData {

    fun getScheduleListData(context: Context, size: Int, schedule: List<Schedule>, type:String) : ArrayList<Schedule>{
        val list = ArrayList<Schedule>()
        for(i in 0 until size){
            list.add(Schedule(schedule[i].Id,schedule[i].date,schedule[i].contents,schedule[i].Yn))
        }
        return list
    }

}