package com.prj.dailybook.util.api

import com.prj.dailybook.util.model.BestSellerDto
import com.prj.dailybook.util.model.MusicDto
import com.prj.dailybook.util.model.SearchBookDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface BookService {

    @GET("/api/bestSeller.api?output=json&categoryId=100")
    fun getBestSellerBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>

    @GET("/api/bestSeller.api?output=json&categoryId=200")
    fun getBestSellerForeBooks(
        @Query("key") apiKey: String
    ): Call<BestSellerDto>

    @GET("/api/search.api?maxResults=100&output=json")
    fun getBooksByName(
        @Query("key") apiKey : String,
        @Query("query") keyword : String
    ): Call<SearchBookDto>


    @GET("/api/recommend.api?categoryId=300&output=json")
    fun getMusic(
        @Query("key") apiKey : String,
    ): Call<MusicDto>

    @GET("/api/recommend.api?categoryId=126&output=json")
    fun getHealth(
        @Query("key") apiKey : String,
    ): Call<BestSellerDto>
}