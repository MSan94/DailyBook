package com.prj.dailybook.util.api

import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>


    @GET("/api/search.api?maxResults=100&output=json")
    fun getBooksByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ): Call<SearchBookDto>

    //http://book.interpark.com/api/recommend.api?key=6A2CE5BEFA0147424241527B53878E9EE626E2AACC55BBA05E22E894DAD40C63&categoryId=100&output=json
}