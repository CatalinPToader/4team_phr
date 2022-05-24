package com.example.a4team_phr

import android.icu.text.SimpleDateFormat
import android.widget.TextView
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import java.util.*


class TimeDatePicker(private val timeTextView : TextView, private val dateTextView : TextView) : TimePickerDialog.OnTimeSetListener, DatePickerDialog.OnDateSetListener {

    override fun onTimeSet(view: TimePickerDialog?, hourOfDay: Int, minute: Int, second: Int) {
        val min = if (minute == 0 ) "00" else "$minute"
        val time = "Time: $hourOfDay:$min"
        timeTextView.text = time
    }

    override fun onDateSet(view: DatePickerDialog?, year: Int, monthOfYear: Int, dayOfMonth: Int) {
        val now = Calendar.getInstance()
        now.set(year, monthOfYear, dayOfMonth)
        val format = SimpleDateFormat("dd-MM-yy", Locale.US)
        val date = "Date: ${format.format(now.time)}"
        dateTextView.text = date
    }


}