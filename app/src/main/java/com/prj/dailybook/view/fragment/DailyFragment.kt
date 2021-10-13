package com.prj.dailybook.view.fragment

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.DailyContract
import com.prj.dailybook.databinding.FragmentDailyBinding
import com.prj.dailybook.presenter.DailyPresenter
import com.prj.dailybook.util.adapter.ScheduleAdapter
import com.prj.dailybook.util.calendar.Saturday
import com.prj.dailybook.util.calendar.Sunday
import com.prj.dailybook.util.calendar.Today
import com.prj.dailybook.util.model.ScheduleListData
import com.prj.dailybook.util.room.Schedule
import com.prj.dailybook.view.dialog.ScheduleDialogFragment
import com.prolificinteractive.materialcalendarview.*
import java.util.*

class DailyFragment : Fragment() , DailyContract.View{
    override lateinit var presenter: DailyContract.Presenter
    private val binding by lazy { FragmentDailyBinding.inflate(layoutInflater) }

    val adapter = ScheduleAdapter()
    private val dialog = ScheduleDialogFragment()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return binding.root
    }

    override fun init() {
        binding.ScheduleRecyclerView.layoutManager = LinearLayoutManager(activity)
        binding.ScheduleRecyclerView.adapter = adapter
        binding.ScheduleRecyclerView.scrollToPosition(0)
        presenter = DailyPresenter().apply {
            view = this@DailyFragment
            adapterView = adapter
            adapterModel = adapter
            schedule = ScheduleListData
        }
        activity?.let { presenter.getSchedule(it) }

        binding.dailyCalendar.selectedDate = CalendarDay.today()
        binding.dailyCalendar.addDecorator(Sunday())
        binding.dailyCalendar.addDecorator(Saturday())
        binding.dailyCalendar.addDecorator(Today())
        binding.dailyCalendar.setHeaderTextAppearance(R.style.TextAppearance_MaterialCalendarWidget_Date)
        binding.dailyCalendar.setWeekDayTextAppearance(R.style.TextAppearance_MaterialCalendarWidget_Date)

        binding.btnAddSchedule.setOnClickListener {
            if(binding.dailyCalendar.selectedDate == null){
                Toast.makeText(activity,"날짜를 선택해주세요.",Toast.LENGTH_SHORT).show()
            }else {
                dialog.setButtonClickListener(object : ScheduleDialogFragment.OnButtonListener {
                    override fun saveSchedule(contents: String) {
                        var year = binding.dailyCalendar.selectedDate!!.year
                        var month = binding.dailyCalendar.selectedDate!!.month + 1
                        var day = binding.dailyCalendar.selectedDate!!.day
                        var sb: StringBuilder =
                            java.lang.StringBuilder().append(year).append("-").append(month)
                                .append("-").append(day)
                        var model = Schedule(0,sb.toString(),contents,"N")
                        activity?.let { it1 -> presenter.insertSchedule(it1,model) }
                    }
                })
                dialog.show(parentFragmentManager, "ScheduleDialog")
            }
        }
    }

    override fun setInsert() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            Toast.makeText(activity, "데일리에 저장하였습니다.", Toast.LENGTH_SHORT).show()
            dialog.dismiss()
            binding.ScheduleRecyclerView.visibility = VISIBLE
            binding.textViewEmpty.visibility = GONE
        },0)
    }

    override fun setEmptyItem() {
        binding.ScheduleRecyclerView.visibility = GONE
        binding.textViewEmpty.visibility = VISIBLE
    }

    /** 리사이클러뷰 set **/
    override fun showItems() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed(Runnable {
            binding.ScheduleRecyclerView.visibility = VISIBLE
            binding.textViewEmpty.visibility = GONE
        },0)
    }

}




