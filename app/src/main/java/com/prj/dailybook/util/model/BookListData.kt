package com.prj.dailybook.util.model

import android.content.Context

object BookListData {

    fun getBookListData(context : Context, size : Int , book : Book, type:String) : ArrayList<Book>{
        val list = ArrayList<Book>()
        for(i in 0..size){
            val rank = if(type == "1") book.rank else "null"
            list.add(Book(book.itemId,book.title,book.description,book.coverSmallUrl,book.categoryId, book.categoryName,book.author,rank))
        }
        return list
    }

}