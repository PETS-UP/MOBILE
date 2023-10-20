package com.petsup.models.cliente

import java.io.Serializable

data class ClienteLocalizacao(
    val longitude: Double,
    val latitude: Double
) : Serializable