package com.petsup.models.cliente

import java.io.Serializable

data class ClienteLogin (
    val email: String,
    val senha: String
) : Serializable