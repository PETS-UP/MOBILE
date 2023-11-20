package com.petsup.ui.view.activity

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import com.petsup.databinding.ActivityDatetimeSelectionBinding
import com.petsup.models.pet.PetResposta
import com.petsup.models.petshop.Petshop
import com.petsup.models.servico.ServicoResposta
import com.petsup.ui.`object`.FormatterObject

class DatetimeSelectionActivity : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    private lateinit var binding: ActivityDatetimeSelectionBinding
    private val calendar = Calendar.getInstance()
    private lateinit var petshop: Petshop
    private lateinit var servico: ServicoResposta
    private lateinit var pet: PetResposta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatetimeSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        petshop = intent.getSerializableExtra("petshop") as Petshop
        servico = intent.getSerializableExtra("servico") as ServicoResposta
        pet = intent.getSerializableExtra("pet") as PetResposta

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, BookingConfirmationActivity::class.java)
            intent.putExtra("date", FormatterObject.formatDate(calendar.timeInMillis))
            intent.putExtra("time", FormatterObject.formatTime(calendar.timeInMillis))
            intent.putExtra("datetime", FormatterObject.formatDateTime(calendar.timeInMillis))
            intent.putExtra("petshop", petshop)
            intent.putExtra("servico", servico)
            intent.putExtra("pet", pet)
            startActivity(intent)
        }

        binding.returnButton.setOnClickListener {
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            this.finish()
        }

        binding.dateButton.setOnClickListener {
            DatePickerDialog(
                this,
                this,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        binding.timeButton.setOnClickListener {
            TimePickerDialog(
                this,
                this,
                calendar.get(java.util.Calendar.HOUR_OF_DAY),
                calendar.get(java.util.Calendar.MINUTE),
                true
            ).show()
        }
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
        binding.dateButton.text = FormatterObject.formatDate(calendar.timeInMillis)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.apply {
            set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
            set(java.util.Calendar.MINUTE, minute)
        }
        binding.timeButton.text = FormatterObject.formatTime(calendar.timeInMillis)
    }
}