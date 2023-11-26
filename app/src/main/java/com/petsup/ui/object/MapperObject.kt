package com.petsup.ui.`object`

import com.petsup.models.petshop.Petshop
import com.petsup.models.petshop.PetshopExibicao
import com.petsup.models.petshop.PetshopMediaAvaliacao
import com.petsup.models.petshop.PetshopMediaPreco

object MapperObject {
    fun petshopToPetshopExibicao(petshop: Petshop): PetshopExibicao {
        return PetshopExibicao(
            id = petshop.id,
            nome = petshop.nome,
            rua = petshop.rua,
            numero = petshop.numero,
            horaAbertura = petshop.horaAbertura.toString(),
            horaFechamento = petshop.horaFechamento.toString(),
            nota = petshop.nota,
            isOpen = petshop.isOpen,
            imagemPerfil = petshop.imagemPerfil,
            media = null
        )
    }

    fun petshopAvaliacaoToPetshopExibicao(petshop: PetshopMediaAvaliacao): PetshopExibicao {
        return PetshopExibicao(
            id = petshop.id,
            nome = petshop.nome,
            rua = petshop.rua,
            numero = petshop.numero,
            horaAbertura = petshop.horaAbertura.toString(),
            horaFechamento = petshop.horaFechamento.toString(),
            nota = petshop.nota,
            isOpen = petshop.isOpen,
            imagemPerfil = petshop.imagemPerfil,
            media = null
        )
    }

    fun petshopPrecoToPetshopExibicao(petshop: PetshopMediaPreco): PetshopExibicao {
        return PetshopExibicao(
            id = petshop.id,
            nome = petshop.nome,
            rua = petshop.rua,
            numero = petshop.numero,
            horaAbertura = petshop.horaAbertura.toString(),
            horaFechamento = petshop.horaFechamento.toString(),
            nota = petshop.nota,
            isOpen = petshop.isOpen,
            imagemPerfil = petshop.imagemPerfil,
            media = petshop.media
        )
    }

    fun listPetshopToListPetshopExibicao(petshops: List<Petshop>): List<PetshopExibicao> {
        return petshops.map {
            petshopToPetshopExibicao(it)
        }
    }

    fun listPetshopAvaliacaoToListPetshopExibicao(petshops: List<PetshopMediaAvaliacao>): List<PetshopExibicao> {
        return petshops.map {
            petshopAvaliacaoToPetshopExibicao(it)
        }
    }

    fun listPetshopPrecoToListPetshopExibicao(petshops: List<PetshopMediaPreco>): List<PetshopExibicao> {
        return petshops.map {
            petshopPrecoToPetshopExibicao(it)
        }
    }
}