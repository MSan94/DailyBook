package com.prj.dailybook.view.fragment

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
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
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
//        binding.dailyCalendar.addDecorator()
    }

//    inner class SundayDecorator: DayViewDecorator {
//        private val calendar = Calendar.getInstance()
//        override fun shouldDecorate(day: CalendarDay?): Boolean {
//            day?.copyTo(calendar)
//            val weekDay = calendar.get(Calendar.DAY_OF_WEEK)
//            return weekDay == Calendar.SUNDAY
//        }
//        override fun decorate(view: DayViewFacade?) {
//            view?.addSpan(object: ForegroundColorSpan(Color.RED){})
//        }
//    }
}




