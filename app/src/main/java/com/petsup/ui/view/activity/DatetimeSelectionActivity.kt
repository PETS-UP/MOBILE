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
    private lateinit var pet: PetResposta
    private lateinit var servico: ServicoResposta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDatetimeSelectionBinding.inflate(layoutInflater)
        setContentView(binding.root)
        petshop = intent.getSerializableExtra("petshop") as Petshop
        pet = intent.getSerializableExtra("pet") as PetResposta
        servico = intent.getSerializableExtra("servico") as ServicoResposta

        binding.continueButton.setOnClickListener {
            val intent = Intent(this, BookingConfirmationActivity::class.java)
            intent.putExtra("date", FormatterObject.formatDate(calendar.timeInMillis))
            intent.putExtra("time", FormatterObject.formatTime(calendar.timeInMillis))
            intent.putExtra("datetime", FormatterObject.formatDateTime(calendar.timeInMillis))
            intent.putExtra("petshop", petshop)
            intent.putExtra("pet", pet)
            intent.putExtra("servico", servico)
            startActivity(intent)
            this.finish()
        }

        binding.returnButton.setOnClickListener {
            val intent = Intent(this, PetSelectionActivity::class.java)
            intent.putExtra("petshop", petshop)
            intent.putExtra("pet", pet)
            intent.putExtra("servico", servico)
            this.finish()
        }

        binding.arrowBack.setOnClickListener {
            val intent = Intent(this, PetSelectionActivity::class.java)
            intent.putExtra("petshop", petshop)
            intent.putExtra("pet", pet)
            intent.putExtra("servico", servico)
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
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        calendar.set(year, month, dayOfMonth)
    }

    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        calendar.apply {
            set(java.util.Calendar.HOUR_OF_DAY, hourOfDay)
            set(java.util.Calendar.MINUTE, minute)
        }
    }
}