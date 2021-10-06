package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.retrofit.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BestSellerPresenter : BestSellerContract.Presenter {


    override lateinit var view: BestSellerContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterContract.Model
    override lateinit var adapterView: AdapterContract.View

    override fun getBestSeller(context: Context, isClear: Boolean) {
        val responseService = RetrofitObject.apiService.getBestSellerBooks(PropertiesData.SERVICE_KEY)
            .enqueue(object : Callback<BestSellerDto> {
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if(response.isSuccessful.not()){
                        Log.d("TestApp2", "에러1")
                        return
                    }
                    response.body()?.let {
                        book.getBookListData(context , it.books.size, it.books, "1").let { _ ->
                            if(isClear){
                                adapterModel.clearItem()
                            }
                            adapterModel.addItems(it.books as ArrayList<Book>)
                        }
                    }

                }
                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

    }


}