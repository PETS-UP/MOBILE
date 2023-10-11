package com.petsup.models

import java.io.Serializable

data class ClienteLogin (
    val email: String,
    val senha: String
) : Serializable