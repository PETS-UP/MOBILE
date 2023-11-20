package com.petsup.models.servico

import java.io.Serializable

data class Servico (
    val id: Int,
    val nome: String,
    val preco: String,
    val descricao: String
) : Serializable