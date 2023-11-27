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
//            .baseUrl("https://petsup.sytes.net/api/")
            .baseUrl("http://192.168.22.180:8081/api/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
