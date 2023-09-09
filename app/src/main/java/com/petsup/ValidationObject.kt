package com.petsup

import android.widget.EditText

object ValidationObject {
    fun nameValidation(etName: EditText): Boolean {
        return if (etName.text.toString().isEmpty()) {
            etName.error = "Preencha o campo."
            false
        } else if (etName.text.toString().length < 3) {
            etName.error = "Nome precisa ter ao menos 3 letras."
            false
        } else {
            true
        }
    }

    fun emailValidation(etEmail: EditText): Boolean {
        val str: String = ".com"
        return if (etEmail.text.toString().isEmpty()) {
            etEmail.error = "Preencha o campo."
            false
        } else if (!etEmail.text.toString().contains('@') ||
            !etEmail.text.toString().contains(str)
        ) {
            etEmail.error = "Insira um e-mail válido!"
            false
        } else {
            true
        }
    }

    fun passwordValidation(etPassword: EditText): Boolean {
        val str: String = ".com"
        return if (etPassword.text.toString().isEmpty()) {
            false
        } else if (etPassword.text.toString().length >= 8 &&
            etPassword.text.toString().length <= 15){
            var ch: Char
            var capitalFlag = false
            var lowerCaseFlag = false
            var numberFlag = false
            for (i in 0 until str.length) {
                ch = str[i]
                if (Character.isDigit(ch)) {
                    numberFlag = true
                } else if (Character.isUpperCase(ch)) {
                    capitalFlag = true
                } else if (Character.isLowerCase(ch)) {
                    lowerCaseFlag = true
                }
                if (numberFlag && capitalFlag && lowerCaseFlag) return true
            }
            return false
            etPassword.error = "Insira um e-mail válido!"
            false
        } else {
            etPassword.error = "A senha deve possuir entre 8 e 15 caracteres"
            false
        }
    }
}