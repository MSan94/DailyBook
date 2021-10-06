package com.prj.dailybook.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prj.dailybook.contract.DailyContract
import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.contract.MainContract
import com.prj.dailybook.databinding.FragmentDailyBinding

class DailyFragment : Fragment() , DailyContract.View{
    override lateinit var presenter: DailyContract.Presenter
    private val binding by lazy { FragmentDailyBinding.inflate(layoutInflater) }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return binding.root
    }

    override fun init() {

    }

}