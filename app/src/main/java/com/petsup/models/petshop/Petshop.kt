package com.petsup.models.petshop

import java.io.Serializable
import java.time.DayOfWeek
import java.time.LocalTime

data class Petshop(
    val id: Int,
    val nome: String,
    val email: String,
    val senha: String,
    val cep: String,
    val telefone: String,
    val estado: String,
    val cidade: String,
    val bairro: String,
    val rua: String,
    val numero: String,
    val cnpj: String,
    val razaoSocial: String,
    val horaAbertura: LocalTime,
    val horaFechamento: LocalTime,
    val diasFuncionais: List<DayOfWeek>,
) : Serializable
