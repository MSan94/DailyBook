package com.prj.dailybook.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.prj.dailybook.R
import com.prj.dailybook.contract.MainContract
import com.prj.dailybook.databinding.ActivityMainBinding
import com.prj.dailybook.presenter.MainPresenter
import com.prj.dailybook.view.fragment.DailyFragment
import com.prj.dailybook.view.fragment.HomeFragment
import com.prj.dailybook.view.fragment.MyFragment

class MainActivity : AppCompatActivity(), MainContract.View {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override lateinit var presenter: MainContract.Presenter
    private val homeFragment = HomeFragment()
    private val dailyFragment = DailyFragment()
    private val myFragment = MyFragment()
    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        initFragment()
    }

    /** 초기화 **/
    override fun init() {
        presenter = MainPresenter()
        presenter.setView(this)
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> {
                    replaceFragment(homeFragment)
                }
                R.id.daily -> {
                    replaceFragment(dailyFragment)
                }
                R.id.my -> {
                    replaceFragment(myFragment)
                }
            }
            true
        }
    }
    
    /** 초기 프래그먼트 할당 **/
    override fun initFragment() {
        replaceFragment(homeFragment)
    }

    override fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .apply {
                replace(R.id.fragmentContainer, fragment)
                commit()
            }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}