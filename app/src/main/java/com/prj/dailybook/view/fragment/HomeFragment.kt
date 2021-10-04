package com.prj.dailybook.view.fragment

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.fragment.app.Fragment
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.databinding.FragmentHomeBinding
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.view.BestSellerActivity
import com.prj.dailybook.view.BookActivity
import com.prj.dailybook.view.dialog.CloseDialogFragment

class HomeFragment : Fragment() , HomeContract.View {

    override lateinit var presenter: HomeContract.Presenter
    val binding by lazy { FragmentHomeBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return binding.root
    }

    override fun init() {
        binding.btnBestSeller.setOnClickListener {
            btnClickEvent("1")
        }
        binding.btnFindBook.setOnClickListener {
            btnClickEvent("2")
        }
        binding.btnClose.setOnClickListener {
            btnClickEvent("4")
        }
    }

    override fun btnClickEvent(type : String) {
        when(type){
            "1" -> {
                val intent = Intent(activity,BestSellerActivity::class.java)
                startActivity(intent)
            }
            "2" -> {
                val intent = Intent(activity, BookActivity::class.java)
                startActivity(intent)
            }
            "4" ->{
                val dialog = CloseDialogFragment()
                dialog.show(parentFragmentManager,"closeDialog")
            }
        }
    }


}