package com.petsup.models.cliente

import java.io.Serializable

data class DetalhesEndereco (
    val formattedAddress: String,
    val neighborhood: String,
    val city: String
) : Serializable