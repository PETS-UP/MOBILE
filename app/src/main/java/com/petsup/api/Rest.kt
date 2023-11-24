package com.petsup.api

import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {

    val client = OkHttpClient.Builder().addInterceptor {
        val request = it.request()
        println(request.body().toString())
        it.proceed(request)
    }.build()

    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            //.baseUrl("https://ec2-54-211-207-232.compute-1.amazonaws.com:8080")
            .baseUrl("http://10.0.2.2:8080/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
