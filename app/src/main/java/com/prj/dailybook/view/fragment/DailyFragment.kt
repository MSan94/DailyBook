package com.prj.dailybook.view.fragment

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.os.Bundle
import android.text.style.ForegroundColorSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.prj.dailybook.contract.DailyContract
import com.prj.dailybook.databinding.FragmentDailyBinding
import com.prj.dailybook.util.calendar.Saturday
import com.prj.dailybook.util.calendar.Sunday
import com.prj.dailybook.util.calendar.Today
import com.prj.dailybook.view.dialog.ScheduleDialogFragment
import com.prolificinteractive.materialcalendarview.*
import java.util.*

class DailyFragment : Fragment() , DailyContract.View{
    override lateinit var presenter: DailyContract.Presenter
    private val binding by lazy { FragmentDailyBinding.inflate(layoutInflater) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return binding.root
    }

    override fun init() {

        binding.dailyCalendar.selectedDate = CalendarDay.today()
        binding.dailyCalendar.addDecorator(Sunday())
        binding.dailyCalendar.addDecorator(Saturday())
        binding.dailyCalendar.addDecorator(Today())

        binding.btnAddSchedule.setOnClickListener {
            val dialog = ScheduleDialogFragment()
            dialog.setButtonClickListener(object : ScheduleDialogFragment.OnButtonListener{
                    override fun saveSchedule(schedule: String) {
                        var year = binding.dailyCalendar.selectedDate.year
                        var month = binding.dailyCalendar.selectedDate.month+1
                        var day = binding.dailyCalendar.selectedDate.day
                        var sb : StringBuilder = java.lang.StringBuilder().append(year).append("-").append(month).append("-").append(day)
                        Toast.makeText(activity,"$sb",Toast.LENGTH_SHORT).show()
                    }
                })
            dialog.show(parentFragmentManager,"ScheduleDialog")
        }
    }

}




