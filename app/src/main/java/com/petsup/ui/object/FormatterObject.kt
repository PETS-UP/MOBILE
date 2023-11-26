package com.petsup.ui.`object`

import android.graphics.Color
import java.text.SimpleDateFormat
import java.time.LocalDateTime

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

    fun formatStatusColor(isOpen: Boolean): Int {
        return if (isOpen) Color.GREEN else Color.RED
    }

    fun formatDateTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

        return formatter.format(timestamp)
    }

    fun formatDate(timestamp: Long): String {
        val formatter = SimpleDateFormat("dd/MM/yy")

        return formatter.format(timestamp)
    }

    fun formatDate(date: String): String {
        return date.split(" ")[0]
    }

    fun formatTime(timestamp: Long): String {
        val formatter = SimpleDateFormat("HH'h'mm")

        return formatter.format(timestamp)
    }

    fun formatTime(time: String): String {
        return time.split(" ")[1]
    }

    fun formatServiceName(service: String): String {
        return service
            .lowercase()
            .split("_")
            .joinToString(" ") { it.replaceFirstChar { it.uppercase() } }
    }

    fun formatPrice(preco: String): String {
        val precoFormatado = preco.toDouble()

        return "R$ " + String.format("%.2f", precoFormatado)
    }

    fun formatGrade(grade: Double): String {
        return String.format("%.1f", grade)
    }
}