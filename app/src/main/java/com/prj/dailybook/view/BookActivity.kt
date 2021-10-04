package com.prj.dailybook.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.databinding.ActivityBookBinding
import com.prj.dailybook.presenter.BookPresenter

class BookActivity : AppCompatActivity(), BookContract.View {

    override lateinit var presenter: BookContract.Presenter
    val binding by lazy { ActivityBookBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = BookPresenter()
        presenter.setView(this)
    }

    override fun init() {

    }

    override fun showProgress() {
        Log.d("Test"," 호출")
    }

    override fun hideProgress() {

    }
}