package com.example.praktikum11.network

import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance(): ApiService {
        val mHttpLoggingInterceptor = HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY)
        val mOkHttpClient = okhttp3.OkHttpClient.Builder().addInterceptor(mHttpLoggingInterceptor).build()
        val builder = Retrofit.Builder()
            .baseUrl("https://thetestrequest.com")
            .client(mOkHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return builder.create(ApiService::class.java)
    }
}