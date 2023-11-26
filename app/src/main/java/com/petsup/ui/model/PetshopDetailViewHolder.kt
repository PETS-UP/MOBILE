package com.petsup.ui.model

sealed class PetshopDetailViewHolder {
    class Empty : PetshopDetailViewHolder()
    class Filled : PetshopDetailViewHolder()
}