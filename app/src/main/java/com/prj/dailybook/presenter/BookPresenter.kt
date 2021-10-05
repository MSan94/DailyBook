package com.prj.dailybook.presenter

import android.content.Context
import android.os.Handler
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.`interface`.DetailInterface
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.SearchBookDto
import com.prj.dailybook.util.retrofit.RetrofitObject
import okhttp3.internal.notify
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
/*
private val view : BookContract.View,
private val adapterView : AdapterContract.View,
private val adapterModel : AdapterContract.Model,
private val book : BookListData*/
class BookPresenter : BookContract.Presenter {
    override lateinit var view: BookContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterContract.Model
    override lateinit var adapterView: AdapterContract.View
    var idx = 0


    override fun getBookList(search : String, context: Context, isClear: Boolean){
        val responseService = RetrofitObject.apiService.getBooksByName(PropertiesData.SERVICE_KEY,search)
            .enqueue(object : Callback<SearchBookDto> {
                override fun onResponse(call: Call<SearchBookDto>, response: Response<SearchBookDto>) {
                    if(response.isSuccessful.not()){
                        Log.d("TestApp2", "에러1")
                        return
                    }
                    response.body()?.let {
                        Log.d("TestApp2", "${it.books.size}")
                        Log.d("TestApp2", it.books.toString())
                        book.getBookListData(context , it.books.size, it.books[idx++], "2").let { list ->
                            if(isClear){
                                adapterModel.clearItem()
                            }
                            adapterModel.addItems(it.books as ArrayList<Book>)
                            adapterView.notifyAdapter()
/*
                            binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
                            binding.bookRecyclerView.adapter = adapter*/
                        }
                    }
                }
                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                    Log.d("TestApp2", "${t.toString()}")
                }
            })
    }


}