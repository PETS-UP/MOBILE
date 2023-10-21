package com.petsup.models

import java.io.Serializable
import java.time.LocalDateTime

data class Agendamento(
    val id: Int,
    val dataHora: LocalDateTime,
    val nomeCliente: String,
    val emailCliente: String,
    val nomePetshop: String,
    val nomePet: String,
    val especie: String,
    val sexo: String,
    val servico: String,
    val preco: Double
) : Serializable
