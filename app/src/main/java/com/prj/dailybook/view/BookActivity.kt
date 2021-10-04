package com.prj.dailybook.view

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.databinding.ActivityBookBinding
import com.prj.dailybook.presenter.BookPresenter
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.SearchBookDto
import com.prj.dailybook.util.retrofit.RetrofitObject
import com.prj.dailybook.view.dialog.BookDetailFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookActivity : AppCompatActivity(), BookContract.View, DetailInterface {

    override lateinit var presenter: BookContract.Presenter
    val binding by lazy { ActivityBookBinding.inflate(layoutInflater) }
    lateinit var adapter : BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        init()
        recyclerInit()
    }

    override fun init() {
        /** 뒤로가기 안먹힘 **/
//        binding.editTextSearch.setOnKeyListener { v, keyCode, event ->
//            if(keyCode == KeyEvent.KEYCODE_ENTER ){
//                search(binding.editTextSearch.text.toString())
//                return@setOnKeyListener true
//            }
//            return@setOnKeyListener true
//        }
        binding.btnSearch.setOnClickListener {
            if(binding.editTextSearch.text.isEmpty()){
                Toast.makeText(this,"검색어를 입력해주세요.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            search(binding.editTextSearch.text.toString())
        }

        presenter = BookPresenter()
        presenter.setView(this)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun search(search: String) {
        /** presenter로 옮길 예정 **/
        val responseService = RetrofitObject.apiService.getBooksByName(PropertiesData.SERVICE_KEY,search)
            .enqueue(object : Callback<SearchBookDto> {
                override fun onResponse(call: Call<SearchBookDto>, response: Response<SearchBookDto>) {
                    if(response.isSuccessful.not()){
                        Log.d("TestApp", "에러1")
                        return
                    }
                    response.body()?.let {
                        Log.d("TestApp", it.books.toString())
                        adapter.submitList(it.books)
                    }

                }
                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {

                    Log.d("TestApp", "${t.toString()}")
                }
            })
        recyclerInit()
    }

    override fun recyclerInit() {
        adapter = BookAdapter(this)
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    override fun getModel(model: Book) {
        val dialog = BookDetailFragment(model)
        dialog.show(supportFragmentManager, "detailDialog")
    }

}