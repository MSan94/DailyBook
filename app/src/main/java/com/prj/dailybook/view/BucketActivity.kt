package com.prj.dailybook.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.BucketContract
import com.prj.dailybook.databinding.ActivityBucketBinding
import com.prj.dailybook.presenter.BookPresenter
import com.prj.dailybook.presenter.BucketPresenter
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.adapter.BucketAdapter
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.BucketListData

/**
 * @author 안명성
 */

class BucketActivity : AppCompatActivity(), BucketContract.View {
    override lateinit var presenter: BucketContract.Presenter
    private val binding by lazy { ActivityBucketBinding.inflate(layoutInflater) }
    lateinit var adapter: BucketAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
    }

    /** 초기화 **/
    override fun init() {
        binding.btnBucket.setOnClickListener {
            trasferList("1")
            binding.textViewBucketTitle.text = "책 저장 리스트"
        }
        binding.btnMusic.setOnClickListener {
            trasferList("2")
            binding.textViewBucketTitle.text = "음반 저장 리스트"
        }

        adapter = BucketAdapter()

        binding.bucketRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bucketRecyclerView.adapter = adapter
        presenter = BucketPresenter().apply {
            view = this@BucketActivity
            adapterView = adapter
            adapterModel = adapter
            bucket = BucketListData
        }

    }

    /** 컨텐츠 전환 **/
    override fun trasferList(type: String) {
        binding.constraintContents.visibility = GONE
        binding.constraintList.visibility = VISIBLE
        when (type) {
            "1" -> {
                presenter.getBucketItem(this, "1")
            }
            "2" -> {

            }
        }
    }

    override fun setRecyclerView(type: String, size : Int, checkSize : Int) {
        runOnUiThread(Runnable() {
            binding.constraintContents.visibility = GONE
            binding.constraintList.visibility = VISIBLE
            binding.constraintEmpty.visibility = GONE
            adapter.notifyAdapter()
            when (type) {
                "1" -> {
                    var rate : Float = (checkSize / size).toFloat()
                    binding.textViewTitle.text = "찜리스트 - 책 -"
                    binding.textViewRate.text = "진행률 : $rate%"
                }
                "2" -> {
                    binding.textViewTitle.text = "찜리스트 - 음반 -"
                }
            }
        })

    }

    override fun setEmptyItem() {
        runOnUiThread(Runnable() {
            val handler = Handler(Looper.getMainLooper())
            binding.constraintContents.visibility = GONE
            binding.constraintList.visibility = GONE
            binding.constraintEmpty.visibility = VISIBLE
            adapter.notifyAdapter()
        })
    }

}