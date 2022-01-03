package com.example.age_in_minutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvDate_Selected: TextView? = null
    private var tvageInMinutes: TextView? = null
    private var tvageinDays: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn: Button = findViewById(R.id.buttonDatePicker)
        tvDate_Selected = findViewById(R.id.tvSelected_Date)
        tvageInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvageinDays = findViewById(R.id.tvAgeInDays)

        btn.setOnClickListener{
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){

        val myCal = Calendar.getInstance()
        val year = myCal.get(Calendar.YEAR)
        val month = myCal.get(Calendar.MONTH)
        val day = myCal.get(Calendar.DAY_OF_MONTH)
        val dpd =
            DatePickerDialog(this,  { _, year, month, dayOfMonth ->
                Toast.makeText(this, "The Year was $year, month was ${month +1}, date way $dayOfMonth", Toast.LENGTH_LONG).show()

                var selectedDate = "$dayOfMonth/${month+1}/$year"

                tvDate_Selected?.text = selectedDate

                var sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = sdf.parse(selectedDate)
                theDate?.let {
                    var selectedDateInMinutes = theDate.time / 60000

                    var currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMinutes = currentDate.time / 60000

                    currentDate?.let {

                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        val differenceinDays = differenceInMinutes/1440

                        tvageInMinutes?.text = differenceInMinutes.toString()
                        tvageinDays?.text = differenceinDays.toString()

                    }
                }

            }, year, month, day
            )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000

        dpd.show()



    }




}