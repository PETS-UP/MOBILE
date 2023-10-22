package com.petsup.models.cliente

import java.io.Serializable

data class ClienteCadastro(
    val nome: String,
    val email: String,
    val senha: String
) : Serializable
