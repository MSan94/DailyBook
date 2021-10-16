package com.prj.dailybook.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.prj.dailybook.contract.MyContract
import com.prj.dailybook.databinding.FragmentMyBinding
import com.prj.dailybook.presenter.MyPresenter

class MyFragment() : Fragment() , MyContract.View{

    override lateinit var presenter: MyContract.Presenter
    val binding by lazy { FragmentMyBinding.inflate(layoutInflater) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        init()
        return binding.root
    }

    override fun init() {
        presenter = MyPresenter()
        presenter.setView(this)

        binding.btnClearSave.setOnClickListener {
            activity?.let { it1 -> presenter.initData(it1) }
        }

    }
}