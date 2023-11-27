package com.petsup.models.petshop

import java.io.Serializable
import java.time.LocalTime

data class PetshopMediaAvaliacao(
    val id: Int,
    val nome: String,
    val nota: Double,
    val imagemPerfil: String,
    val isOpen: Boolean,
    val horaAbertura: String,
    val horaFechamento: String,
    val rua: String,
    val numero: String
) : Serializable
