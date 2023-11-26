package com.petsup.models.petshop

import java.io.Serializable

data class PetshopExibicao(
    val id: Int?,
    val nome: String?,
    val rua: String?,
    val numero: String?,
    val horaAbertura: String?,
    val horaFechamento: String?,
    val nota: Double?,
    val isOpen: Boolean?,
    val imagemPerfil: String?,
    val media: Double?
): Serializable