package com.petsup.models.cliente

import java.io.Serializable

data class ClienteToken (
    val id: Int,
    val nome: String,
    val email: String,
    val token: String
) : Serializable