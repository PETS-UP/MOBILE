package com.petsup

import android.widget.EditText

object ValidationObject {
    fun nameValidation(etName: EditText): Boolean {
        val name = etName.text.toString()
        return if (name.isEmpty()) {
            etName.error = "Por favor, preencha o campo."
            false
        } else if (name.length < 3) {
            etName.error = "Nome precisa ter ao menos 3 letras."
            false
        } else {
            true
        }
    }

    fun petNameValidation(etName: EditText): Boolean {
        val petName = etName.text.toString()
        return if (petName.isEmpty()) {
            etName.error = "Por favor, digite um nome para o pet."
            false
        } else {
            true
        }
    }

    fun emailValidation(etEmail: EditText): Boolean {
        val str: String = ".com"
        val email = etEmail.text.toString()
        return if (email.isEmpty()) {
            etEmail.error = "Preencha o campo."
            false
        } else if (!email.contains('@') ||
            !email.contains(str)
        ) {
            etEmail.error = "Insira um e-mail válido."
            false
        } else {
            true
        }
    }

    fun passwordValidation(etPassword: EditText): Boolean {
        val password = etPassword.text.toString()
        return if (password.isEmpty()) {
            etPassword.error = "Por favor, digite uma senha."
            false
        } else if (password.length in 8..15) {
            var ch: Char
            var capitalFlag = false
            var lowerCaseFlag = false
            var numberFlag = false
            for (i in password.indices) {
                ch = password[i]
                when {
                    Character.isDigit(ch) -> numberFlag = true
                    Character.isUpperCase(ch) -> capitalFlag = true
                    Character.isLowerCase(ch) -> lowerCaseFlag = true
                }
                if (numberFlag && capitalFlag && lowerCaseFlag) return true
            }
            etPassword.error = "Por favor, digite uma senha válida."
            false
        } else {
            etPassword.error = "A senha deve possuir entre 8 e 15 caracteres."
            false
        }
    }
}