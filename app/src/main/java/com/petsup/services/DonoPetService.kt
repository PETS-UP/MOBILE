package com.petsup.services

import com.petsup.models.LoginRequest
import com.petsup.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface DonoPetService {
    @POST("/clientes/login")
    fun login(@Body loginRequest : LoginRequest):
            Call<LoginResponse>
}