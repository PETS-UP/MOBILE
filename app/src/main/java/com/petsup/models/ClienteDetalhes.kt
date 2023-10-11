package com.petsup.models

import java.io.Serializable
import java.time.LocalDate

data class ClienteDetalhes (
    val cep: String,
    val telefone: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val rua: String,
    val numero: String,
    val dataNasc: LocalDate,
    val cpf: String
) : Serializable