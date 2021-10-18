package com.example.datetimedialogs

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.icu.util.Calendar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import java.text.SimpleDateFormat
import java.util.*

lateinit var tvDate:TextView
var dt=""
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvDate=findViewById(R.id.textView)
    }
    val cal=Calendar.getInstance()
    val datePicker=DatePickerDialog.OnDateSetListener { datepicker, yy,  mm,dd ->
        cal.set(Calendar.YEAR, yy)
        cal.set(Calendar.MONTH, mm)
        cal.set(Calendar.DAY_OF_MONTH, dd)
        timepicker(cal)
    }





    fun pickDate(view: android.view.View) {
        val dateDialog=DatePickerDialog(this,datePicker,
            cal.get(Calendar.YEAR),
            cal.get(Calendar.MONTH),
            cal.get(Calendar.DAY_OF_MONTH))
        dateDialog.show()
    }











    private fun timepicker(cal: Calendar) {

       val timeDialog=TimePickerDialog(this,TimePickerDialog.OnTimeSetListener{timePicker, hh, min ->
        cal.set(Calendar.HOUR,hh)
           cal.set(Calendar.MINUTE,min)
           /*val stf=SimpleDateFormat("hh:mm")
           dt=dt+" "+stf.format(cal.time)
           tvDate.setText(dt)*/
           updateLabel(cal)
       },cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),false).show()
    }
    private fun updateLabel(cal: Calendar) {
        val sdf=SimpleDateFormat()
        dt=sdf.format(cal.time)
        tvDate.setText(dt)
    }
}