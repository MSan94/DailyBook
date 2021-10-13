package com.prj.dailybook.view.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.prj.dailybook.R
import com.prj.dailybook.databinding.DialogScheduleBinding

class ScheduleDialogFragment : DialogFragment() {


    private var _binding: DialogScheduleBinding? = null
    private val binding get() = _binding!!
    private lateinit var buttonClickListener: OnButtonListener

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogScheduleBinding.inflate(inflater, container, false)
        binding.btnCancel.setOnClickListener {
            dismiss()
        }
        binding.btnOk.setOnClickListener {
            if(binding.editTextSchedule.text.isEmpty())
                binding.textViewAlert.text = "일정을 작성해주세요!!"
            else
                buttonClickListener.saveSchedule(binding.editTextSchedule.text.toString())
        }
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        val width = resources.getDimensionPixelSize(R.dimen.schedule_width)
        val height = resources.getDimensionPixelSize(R.dimen.schedule_height)
        dialog?.window?.setLayout(width,height)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    interface OnButtonListener {
        fun saveSchedule(schedule : String)
    }

    fun setButtonClickListener(buttonClickListener : OnButtonListener){
        this.buttonClickListener = buttonClickListener
    }

}