package com.prj.dailybook.presenter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Toast
import com.prj.dailybook.contract.MyContract
import com.prj.dailybook.util.room.RoomObject
import kotlin.concurrent.thread

class MyPresenter : MyContract.Presenter {
    private var view : MyContract.View? = null

    private var roomObject : RoomObject? = null

    override fun setView(view: MyContract.View) {
        this.view = view
    }

    override fun initData(context : Context) {
        thread(start = true){
            roomObject = RoomObject.getInstance(context)
            roomObject?.bucketDao()?.deleteAll()
            RoomObject.delInstance()
            roomObject = RoomObject.getScheduleInstance(context)
            roomObject?.ScheduleDao()?.deleteAll()
            RoomObject.delSchInstance()
            var handler = Handler(Looper.getMainLooper())
            handler.postDelayed(Runnable{
                Toast.makeText(context,"저장 정보를 초기화하였습니다.",Toast.LENGTH_SHORT).show()
            },0)
        }
    }

}