package com.petsup.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            //.baseUrl("https://ec2-54-211-207-232.compute-1.amazonaws.com:8080")
            .baseUrl("http://192.168.0.16:8080/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}