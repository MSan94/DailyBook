package com.prj.dailybook.presenter

import android.content.Context
import android.util.Log
import com.prj.dailybook.contract.AdapterContract
import com.prj.dailybook.contract.BookContract
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.model.Book
import com.prj.dailybook.util.model.BookListData
import com.prj.dailybook.util.model.SearchBookDto
import com.prj.dailybook.util.retrofit.RetrofitObject
import com.prj.dailybook.util.room.Bucket
import com.prj.dailybook.util.room.RoomObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.concurrent.thread

class BookPresenter : BookContract.Presenter {
    override lateinit var view: BookContract.View
    override lateinit var book: BookListData
    override lateinit var adapterModel: AdapterContract.Model
    override lateinit var adapterView: AdapterContract.View

    private var roomObject : RoomObject? = null

    var idx = 0


    override fun getBookList(search : String, context: Context, isClear: Boolean){
        val responseService = RetrofitObject.apiService.getBooksByName(PropertiesData.SERVICE_KEY,search)
            .enqueue(object : Callback<SearchBookDto> {
                override fun onResponse(call: Call<SearchBookDto>, response: Response<SearchBookDto>) {
                    if(response.isSuccessful.not()){
                        Log.d(Error_TAG, "response_Nod_Success")
                        return
                    }
                    response.body()?.let {
                        book.getBookListData(context , it.books.size, it.books, "2").let { _ ->
                            if(isClear){
                                adapterModel.clearItem()
                            }
                            adapterModel.addItems(it.books as ArrayList<Book>)
                        }
                    }

                }
                override fun onFailure(call: Call<SearchBookDto>, t: Throwable) {
                    Log.d(Error_TAG, "${t.toString()}")
                }
            })

    }

    override fun saveBook(context: Context, model: Book) {
        val bucket = Bucket(model.itemId, model.title, model.author, model.coverSmallUrl,"book")
        roomObject = RoomObject.getInstance(context)
        thread(start = true) {
            var result = roomObject?.bucketDao()?.validItem(model.itemId)
            when(result){
                0 -> {
                    roomObject?.bucketDao()?.insertBook(bucket)!!
                    view.setBucketBook("1")
                    RoomObject.delInstance()
                }
                else -> {
                    view.setBucketBook("2")
                    RoomObject.delInstance()
                }
            }
        }
    }

    companion object{
        private const val Error_TAG = "Error"
    }
}