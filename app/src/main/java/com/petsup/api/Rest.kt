package com.petsup.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Rest {
    fun getInstance(): Retrofit {
        return Retrofit
            .Builder()
            //.baseUrl("https://ec2-54-211-207-232.compute-1.amazonaws.com:8080")
            .baseUrl("http://seu.enderecc.de.ip:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}