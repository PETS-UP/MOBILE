package com.petsup.ui.model

sealed class PetListViewHolder {
    class EmptyPetList : PetListViewHolder()
    class PetList : PetListViewHolder()
}