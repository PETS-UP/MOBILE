package com.petsup.models.pet

import java.io.Serializable

data class PetAtualiza (
    val id: Int,
    val nome: String?,
    val sexo: String?,
    val especie: String?
) : Serializable