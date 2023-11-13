package com.petsup.ui.`object`

import java.text.SimpleDateFormat
import java.util.Locale

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

    fun formatDateTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

        return formatter.format(timestamp)
    }

    fun formatDate(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yy")

        return formatter.format(timestamp)
    }

    fun formatTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("HH'h'mm")

        return formatter.format(timestamp)
    }
}