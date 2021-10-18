package com.prj.dailybook.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.prj.dailybook.contract.MusicContract
import com.prj.dailybook.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity(), MusicContract.View {
    override lateinit var presenter : MusicContract.Presenter
    private val binding by lazy { ActivityMusicBinding.inflate(layoutInflater) }

    override fun onCreateView(name: String, context: Context, attrs: AttributeSet): View? {
        return binding.root
    }

    override fun init() {
        TODO("Not yet implemented")
    }

}