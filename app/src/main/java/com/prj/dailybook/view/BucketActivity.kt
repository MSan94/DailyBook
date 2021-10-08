package com.prj.dailybook.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.contract.BucketContract
import com.prj.dailybook.databinding.ActivityBucketBinding

class BucketActivity : AppCompatActivity() , BucketContract.View {
    override lateinit var presenter: BucketContract.Presenter
    private val binding by lazy { ActivityBucketBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    override fun init() {

    }

}