package com.petsup.models.petshop

import java.io.Serializable

data class PetshopAvaliacao (
    val id: Int,
    val nome: String,
    val media: Double
) : Serializable