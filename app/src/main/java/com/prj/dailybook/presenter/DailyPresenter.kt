package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.contract.DailyContract
import com.prj.dailybook.contract.ScheduleAdapterContract
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.ScheduleListData
import com.prj.dailybook.util.room.Bucket
import com.prj.dailybook.util.room.RoomObject
import com.prj.dailybook.util.room.Schedule
import kotlin.concurrent.thread

class DailyPresenter : DailyContract.Presenter {

    override lateinit var view: DailyContract.View
    override lateinit var schedule: ScheduleListData
    override lateinit var adapterModel: ScheduleAdapterContract.Model
    override lateinit var adapterView: ScheduleAdapterContract.View

    private var roomObject : RoomObject? = null

    override fun insertSchedule(context: Context, schedule : Schedule) {
        thread(start = true){
            roomObject = RoomObject.getScheduleInstance(context)
            roomObject?.ScheduleDao()?.insertSchedule(schedule)
            var list = roomObject?.ScheduleDao()?.getAll()
            list?.sortedByDescending { it.date }
            if (list?.size!! > 0) {
                view.setInsert()
                adapterModel.addItems(list as ArrayList<Schedule>)
            } else {
                view.setEmptyItem()
            }
            RoomObject.delSchInstance()
        }
    }

    override fun getSchedule(context: Context) {
        thread(start = true){
            roomObject = RoomObject.getScheduleInstance(context)
            var list = roomObject?.ScheduleDao()?.getAll()
//            var sortedList = list?.sortedBy { it.date }
//            for(i in 0 until list?.size!!){
//                Log.d("TestList", list[i].toString())
//            }
            if (list?.size!! > 0) {
                view.showItems()
                adapterModel.addItems(list as ArrayList<Schedule>)
            } else {
                view.setEmptyItem()
            }

            RoomObject.delSchInstance()
        }
    }

}