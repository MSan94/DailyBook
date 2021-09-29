package com.prj.dailybook.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.HomeContract
import com.prj.dailybook.databinding.ActivityHomeBinding
import com.prj.dailybook.presenter.HomePresenter
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.adapter.BookAdapter
import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.retrofit.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity() : AppCompatActivity(), HomeContract.View {

    val binding by lazy { ActivityHomeBinding.inflate(layoutInflater) }
    override lateinit var presenter: HomeContract.Presenter

    val adapter = BookAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        presenter = HomePresenter()

        val responseService = RetrofitObject.apiService.getBestSellerBooks(PropertiesData.SERVICE_KEY)
                .enqueue(object : Callback<BestSellerDto> {
                    override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                        if(response.isSuccessful.not()){
                            return
                        }
                        response.body()?.let {
                            adapter.submitList(it.books)
                        }

                    }
                    override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                        TODO("Not yet implemented")
                    }
                })

        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
    }

    override fun init() {
    }

    companion object{
        private const val TAG = "RetrofitResult"
    }
}