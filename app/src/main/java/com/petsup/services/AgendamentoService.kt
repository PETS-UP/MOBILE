package com.petsup.services

import com.petsup.models.AgendamentoResposta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface AgendamentoService {
//    @POST("agendamentos")
//    fun postAgendamento(@Query(""))
    @GET("agendamentos/cliente")
    fun getAgendamentosCliente(@Query("idCliente") idCliente : Int): Call<List<AgendamentoResposta>>
}