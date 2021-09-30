package com.prj.dailybook.view.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.prj.dailybook.R
import com.prj.dailybook.databinding.DialogCloseBinding

class CloseDialogFragment : DialogFragment() {

    private var _binding : DialogCloseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DialogCloseBinding.inflate(inflater,container,false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.btnOk.setOnClickListener {
            btnClickEvent("1")
        }
        binding.btnNo.setOnClickListener {
            btnClickEvent("2")
        }
        return binding.root
    }

    fun btnClickEvent(type : String){
        when(type){
            "1" -> {
                if(Build.VERSION.SDK_INT >= 21){
                    android.os.Process.killProcess((android.os.Process.myPid()))
                }
            }
            "2" ->{
                dismiss()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}