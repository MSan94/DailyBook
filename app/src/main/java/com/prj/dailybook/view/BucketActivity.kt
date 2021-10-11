package com.prj.dailybook.view

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.prj.dailybook.contract.BucketContract
import com.prj.dailybook.databinding.ActivityBucketBinding

/**
 * @author 안명성
 */

class BucketActivity : AppCompatActivity() , BucketContract.View {
    override lateinit var presenter: BucketContract.Presenter
    private val binding by lazy { ActivityBucketBinding.inflate(layoutInflater) }

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
    }
    
    /** 컨텐츠 전환 **/
    override fun trasferList(type : String) {
        binding.constraintContents.visibility = GONE
        binding.constraintList.visibility = VISIBLE
        when(type){
            "1" -> {

            }
            "2" -> {

            }
        }
    }

}