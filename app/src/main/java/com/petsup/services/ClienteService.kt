package com.petsup.services

import retrofit2.Call
import retrofit2.http.POST

interface ClienteService {
    @POST("/clientes")
    fun postCLiente(): Call<Unit>

    
}