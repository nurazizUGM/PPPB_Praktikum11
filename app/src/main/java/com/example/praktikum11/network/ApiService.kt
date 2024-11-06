package com.example.praktikum11.network

import com.example.praktikum11.model.Authors
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("authors")
    fun getAuthors(): Call<List<Authors>>
}