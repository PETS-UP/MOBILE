package com.petsup.models.petshop

import java.io.Serializable
import java.time.LocalTime

data class PetshopMediaPreco (
    val id: Int,
    val nome: String,
    val media: Double,
    val imagemPerfil: String,
    val isOpen: Boolean,
    val nota: Double,
    val horaAbertura: String,
    val horaFechamento: String,
    val rua: String,
    val numero: String
) : Serializable