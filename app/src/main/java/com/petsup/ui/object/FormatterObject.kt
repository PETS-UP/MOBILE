package com.petsup.ui.`object`

object FormatterObject {
    fun formatPhoneNumber(phoneNumber: String): String {
        val ddd = phoneNumber.substring(0, 2)
        val head = phoneNumber.substring(2, 7)
        val tail = phoneNumber.substring(7, 11)

        return "($ddd) $head-$tail"
    }

    fun formatStatus(isOpen: Boolean): String {
        return if (isOpen) "Aberto" else "Fechado agora"
    }
}