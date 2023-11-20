package com.petsup.models

import java.io.Serializable
import java.time.LocalDateTime

data class AgendamentoResposta (
    val id: Int,
    val dataHora: String,
    val nomeCliente: String,
    val emailCliente: String,
    val nomePetshop: String,
    val nomePet: String,
    val especie: String,
    val sexo: String,
    val servico: String,
    val preco: String
) : Serializable
