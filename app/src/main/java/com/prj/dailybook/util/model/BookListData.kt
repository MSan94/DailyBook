package com.prj.dailybook.util.model

import android.content.Context

object BookListData {

    fun getBookListData(context: Context, size: Int, book: List<Book>, type:String) : ArrayList<Book>{
        val list = ArrayList<Book>()
        for(i in 0 until size){
            val rank = if(type == "1") book[i].rank else "null"
            list.add(Book(book[i].itemId,book[i].title,book[i].description,book[i].coverSmallUrl,book[i].categoryId, book[i].categoryName,book[i].author,rank))
        }
        return list
    }

}