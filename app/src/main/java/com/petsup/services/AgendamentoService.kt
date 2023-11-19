package com.petsup.services

import com.petsup.models.AgendamentoResposta
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import java.time.LocalDateTime

interface AgendamentoService {
    @POST("agendamentos")
    fun postAgendamento(
        @Query("dataHora") dataHora: LocalDateTime,
        @Query("idCliente") idCliente: Int,
        @Query("idPetshop") idPetshop: Int,
        @Query("idPet") idPet: Int,
        @Query("idServico") idServico: Int
    ): Call<Unit>

    @GET("agendamentos/cliente")
    fun getAgendamentosCliente(@Query("idCliente") idCliente: Int): Call<List<AgendamentoResposta>>
}