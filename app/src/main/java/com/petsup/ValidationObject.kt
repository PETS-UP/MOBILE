package com.petsup

import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

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
}