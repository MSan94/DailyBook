package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BestSellerContract
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.retrofit.RetrofitObject
import com.prj.dailybook.util.room.Bucket
import com.prj.dailybook.util.room.RoomObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class BestSellerPresenter : BestSellerContract.Presenter {


    override lateinit var view: BestSellerContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterContract.Model
    override lateinit var adapterView: AdapterContract.View
    private var roomObject : RoomObject? = null

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
                }
            })

    }

    override fun saveBook(context: Context, model : Book) {
        val bucket = Bucket(model.itemId, model.title, model.author, model.coverSmallUrl,"book")
        roomObject = RoomObject.getInstance(context)
        thread(start = true) {
            var result = roomObject?.bucketDao()?.validItem(model.itemId)
            when(result){
                0 -> {
                    roomObject?.bucketDao()?.insertBook(bucket)!!
                    view.setBucketBook("1")
                }
                else -> {
                    view.setBucketBook("2")
                }
            }
            val list : List<Bucket>? = roomObject?.bucketDao()?.getAll()
            for(i in list!!.indices){
                Log.d("ListTest", list[i].toString())
            }
        }
    }


}