package com.prj.dailybook.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView
import com.prj.dailybook.R
import com.prj.dailybook.contract.MainContract
import com.prj.dailybook.databinding.ActivityMainBinding
import com.prj.dailybook.presenter.MainPresenter
import com.prj.dailybook.util.adapter.CloseHandler
import com.prj.dailybook.view.dialog.CloseDialogFragment
import com.prj.dailybook.view.fragment.DailyFragment
import com.prj.dailybook.view.fragment.HomeFragment
import com.prj.dailybook.view.fragment.MyFragment

/**
 * @author 안명성
 */
class MainActivity : AppCompatActivity(), MainContract.View,
    NavigationView.OnNavigationItemSelectedListener, HomeFragment.OnCloseListener {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    override lateinit var presenter: MainContract.Presenter
    private val homeFragment = HomeFragment()
    private val dailyFragment = DailyFragment()
    private val myFragment = MyFragment()

    lateinit var barNavigationView: NavigationView

    lateinit var bottomNavigationView: BottomNavigationView
    private val closeHandler: CloseHandler = CloseHandler(this)

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
        init()
        initFragment()
    }

    override fun onResume() {
        super.onResume()
        binding.barNavigationView.visibility = GONE
    }

    /** 초기화 **/
    override fun init() {
        binding.barNavigationView.visibility = GONE
        presenter = MainPresenter()
        presenter.setView(this)

        binding.mainLayout.setOnClickListener {
            binding.barNavigationView.visibility = GONE
        }
        binding.btnMenuClose.setOnClickListener {
            binding.barNavigationView.visibility = GONE
        }
        binding.fragmentContainer.setOnClickListener {
            binding.barNavigationView.visibility = GONE
        }
        
        bottomNavigationView = binding.bottomNavigationView
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    binding.barNavigationView.visibility = GONE
                    replaceFragment(homeFragment)
                }
                R.id.daily -> {
                    binding.barNavigationView.visibility = GONE
                    replaceFragment(dailyFragment)
                }
                R.id.my -> {
                    binding.barNavigationView.visibility = GONE
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

    /** 프래그먼트 변경 **/
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
    
    /** 햄버거 아이콘 클릭 **/
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        binding.barNavigationView.visibility = VISIBLE

        return super.onOptionsItemSelected(item)
    }
    
    /** 사이드 메뉴 선택 **/
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item1 -> {
                val intent = Intent(this, BestSellerActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item2 -> {
                val intent = Intent(this, BookActivity::class.java)
                startActivity(intent)
            }
            R.id.menu_item4 -> {
                val dialog = CloseDialogFragment()
                dialog.show(supportFragmentManager, "closeDialog")
            }
        }
        return false
    }
    
    
    /** 뒤로가기 버튼 이벤트 **/
    override fun onBackPressed() {
        if (binding.barNavigationView.visibility == VISIBLE) {
            binding.barNavigationView.visibility = GONE
        } else {
            closeHandler.onBackPress()
        }
    }

    /** 사이드 메뉴 gone **/
    override fun onCloseMenu() {
        binding.barNavigationView.visibility = GONE
    }

    /** 액티비티 전환 **/
    override fun goActivity(type: String) {
        when (type) {
            "2" -> {
                val intent = Intent(this, BookActivity::class.java)
                startActivity(intent)
            }
            "3" -> {
                val intent = Intent(this, BucketActivity::class.java)
                startActivity(intent)
            }
        }
    }


}