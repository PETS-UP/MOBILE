package com.petsup.models

import java.io.Serializable

data class Cliente(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val telefone: String,
    val cep: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val rua: String,
    val numero: String,
) : Serializable
