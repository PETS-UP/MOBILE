package com.petsup.models

import java.io.Serializable

data class Pet(
    val id: Int,
    val nome: String,
    val sexo: String,
    val especie: String
) : Serializable