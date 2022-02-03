package com.example.eraldhaka.androidarchitecture.network

import com.example.eraldhaka.androidarchitecture.database.Repositories
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

private val service: MainNetwork by lazy {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/users/eraldhaka/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    retrofit.create(MainNetwork::class.java)
}

fun getNetworkService() = service

interface MainNetwork {
    @GET("repos")
    suspend fun fetchDataTest(): List<Repositories>
}