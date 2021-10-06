package com.prj.dailybook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.widget.AppCompatButton
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.prj.dailybook.R
import com.prj.dailybook.contract.MainContract
import com.prj.dailybook.databinding.ActivityMainBinding
import com.prj.dailybook.databinding.HeaderBinding
import com.prj.dailybook.presenter.MainPresenter
import com.prj.dailybook.view.dialog.CloseDialogFragment
import com.prj.dailybook.view.fragment.DailyFragment
import com.prj.dailybook.view.fragment.HomeFragment
import com.prj.dailybook.view.fragment.MyFragment

class MainActivity : AppCompatActivity(), MainContract.View , NavigationView.OnNavigationItemSelectedListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override lateinit var presenter: MainContract.Presenter
    private val homeFragment = HomeFragment()
    private val dailyFragment = DailyFragment()
    private val myFragment = MyFragment()

    lateinit var barNavigationView : NavigationView
    private lateinit var drawerLauout : DrawerLayout

    lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true) // 드로어를꺼낼 홈버튼 활성화
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_dehaze_24) // 햄버거
//        supportActionBar?.setDisplayShowTitleEnabled(false) //툴바에 타이틀 안보이게


        barNavigationView = binding.barNavigationView
        barNavigationView.setNavigationItemSelectedListener(this)

        binding.mainLayout.setOnClickListener {
            binding.barNavigationView.visibility = GONE
        }
        binding.btnMenuClose.setOnClickListener {
            binding.barNavigationView.visibility = GONE
        }


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

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.barNavigationView.visibility = VISIBLE

        return super.onOptionsItemSelected(item)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_item1 -> {
                val intent = Intent(this,BestSellerActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item2 -> {
                val intent = Intent(this, BookActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item4 -> {
                val dialog = CloseDialogFragment()
                dialog.show(supportFragmentManager,"closeDialog")
            }
        }
        return false
    }
}