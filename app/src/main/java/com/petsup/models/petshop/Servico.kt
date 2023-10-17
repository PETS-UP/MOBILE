package com.petsup.models.petshop

import java.io.Serializable

data class Servico (
    val id: Int,
    val nome: String,
    val preco: Double,
    val descricao: String
) : Serializable