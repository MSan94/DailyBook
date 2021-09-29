package com.prj.dailybook.util.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.prj.dailybook.BuildConfig
import com.prj.dailybook.util.PropertiesData
import com.prj.dailybook.util.api.BookService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit

object RetrofitObject {
    private val baseUrl = PropertiesData.BASE_URL

    private fun createOkHttpClient() : OkHttpClient{
       val interceptor = HttpLoggingInterceptor()

       if(BuildConfig.DEBUG){
           interceptor.level = HttpLoggingInterceptor.Level.BODY
       }else{
           interceptor.level = HttpLoggingInterceptor.Level.NONE
       }

       return OkHttpClient.Builder()
           .connectTimeout(20, TimeUnit.SECONDS)
           .readTimeout(360, TimeUnit.SECONDS)
           .writeTimeout(20, TimeUnit.SECONDS)
           .addNetworkInterceptor(interceptor)
           .build()
    }

    private val retrofitService = Retrofit.Builder()
       .baseUrl(baseUrl)
       .addConverterFactory(GsonConverterFactory.create())
       .client(createOkHttpClient())
       .build()

    val apiService : BookService = retrofitService.create(BookService::class.java)
}