package com.petsup.models.cliente

import java.io.Serializable
import java.time.LocalDate

data class ClienteAtualiza (
    val id: Int,
    val nome: String?,
    val email: String?,
    val senha: String?,
    val cep: String?,
    val telefone: String?,
    val estado: String?,
    val cidade: String?,
    val bairro: String?,
    val rua: String?,
    val numero: String?,
    val dataNasc: LocalDate?,
    val cpf: String?,
) : Serializable