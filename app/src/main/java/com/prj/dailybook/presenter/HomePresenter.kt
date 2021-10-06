package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import com.prj.dailybook.contract.*
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.model.*
import com.prj.dailybook.util.retrofit.RetrofitObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter : HomeContract.Presenter {

    override lateinit var view: HomeContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterPagerContract.Model
    override lateinit var adapterView: AdapterPagerContract.View
    lateinit var music : List<Music>
    lateinit var foreBook : Book
    lateinit var healthBook : Book

    override fun getData(context: Context, isClear: Boolean) {
        val responseService = RetrofitObject.apiService.getBestSellerBooks(PropertiesData.SERVICE_KEY)
            .enqueue(object : Callback<BestSellerDto> {
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if(response.isSuccessful.not()){
                        Log.d(TAG,"getData_not_success")
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
                }
            })
    }

    override fun getMusic() {
        val responseService = RetrofitObject.apiService.getMusic(PropertiesData.SERVICE_KEY)
            .enqueue(object : Callback<MusicDto>{
                override fun onResponse(call: Call<MusicDto>, response: Response<MusicDto>) {
                    if(response.isSuccessful.not()){
                        Log.d(TAG,"getMusic_not_success")
                    }
                    response.body()?.let {
                        music = it.musics
                    }
                    view.setMusic(music)
                }

                override fun onFailure(call: Call<MusicDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun getForeBook() {
        val responseService = RetrofitObject.apiService.getBestSellerForeBooks(PropertiesData.SERVICE_KEY)
            .enqueue(object : Callback<BestSellerDto>{
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if(response.isSuccessful.not()){
                        Log.d(TAG,"getMusic_not_success")
                    }
                    response.body()?.let {
                        foreBook = it.books[0]
                    }
                    view.setForeBook(foreBook)
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    override fun getHealthBook() {
        val responseService = RetrofitObject.apiService.getHealth(PropertiesData.SERVICE_KEY)
            .enqueue(object : Callback<BestSellerDto>{
                override fun onResponse(call: Call<BestSellerDto>, response: Response<BestSellerDto>) {
                    if(response.isSuccessful.not()){
                        Log.d(TAG,"getMusic_not_success")
                    }
                    response.body()?.let {
                        healthBook = it.books[0]
                    }
                    view.setHealthBook(healthBook)
                }

                override fun onFailure(call: Call<BestSellerDto>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    companion object{
        private const val TAG = "Presenter_LOG"
    }
}