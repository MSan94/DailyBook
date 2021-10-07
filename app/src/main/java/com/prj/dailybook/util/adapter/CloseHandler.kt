package com.prj.dailybook.util.adapter

import android.app.Activity
import android.widget.Toast

class CloseHandler {
    private var backTime : Long = 0
    lateinit var activity : Activity

    constructor(context : Activity){
        this.activity = context
    }

    fun onBackPress() {
        if(System.currentTimeMillis() > backTime + 2000){
            backTime = System.currentTimeMillis()
            Toast.makeText(activity,"한번 더 눌리면 종료됩니다.", Toast.LENGTH_SHORT).show()
            return;
        }
        if(System.currentTimeMillis() <= backTime + 2000){
            activity.finish()
        }
    }
}