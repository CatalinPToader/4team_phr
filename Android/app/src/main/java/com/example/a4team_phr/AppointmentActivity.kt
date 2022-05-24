package com.example.a4team_phr

import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog
import com.wdullaer.materialdatetimepicker.time.TimePickerDialog
import org.json.JSONArray
import org.json.JSONObject
import java.net.CookieManager
import java.net.URL
import java.util.*


class AppointmentActivity : AppCompatActivity() {
    private val urlBase = "http://10.0.2.2:8000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //val cm  = Gson().fromJson<CookieManager>(intent.getStringExtra("CookieManager"), CookieManager::class.java)
        setContentView(R.layout.appointment)

        val timebutton : TextView = findViewById(R.id.timeTextView)
        val datebutton : TextView = findViewById(R.id.dateTextView)

        val now : Calendar = Calendar.getInstance()
        val timetext = "Time: ${now[Calendar.HOUR]}:${now[Calendar.MINUTE]}"
        timebutton.text = timetext

        val format = SimpleDateFormat("dd-MM-yy", Locale.US)
        val datetext = "Date: ${format.format(now.time)}"
        datebutton.text = datetext

        val tdp = TimeDatePicker(timebutton, datebutton)

        val tpd = TimePickerDialog.newInstance(tdp, now[Calendar.HOUR], now[Calendar.MINUTE], true)
        tpd.setMaxTime(18, 0, 0)
        tpd.setMinTime(9,0,0)
        tpd.setTimeInterval(1, 30)

        val dpd = DatePickerDialog.newInstance(tdp, now[Calendar.YEAR], now[Calendar.MONTH], now[Calendar.DAY_OF_MONTH])
        dpd.setDateRangeLimiter(CustomDateRangeLimiter())
        timebutton.setOnClickListener { tpd.show(supportFragmentManager, "SMTH") }
        datebutton.setOnClickListener { dpd.show(supportFragmentManager, "SMTH2")}

        val urlData = "medic/"
        val allMedics = JSONArray(URL(urlBase.plus(urlData)).readText())
        val specList : MutableSet<String> = mutableSetOf()

        var user = JSONObject()
        for (i in 0 until allMedics.length()) {
            val medic = allMedics.getJSONObject(i)
            specList.add(medic.getString("Specialitate"))
        }

        val spinner : Spinner = findViewById(R.id.specList)

        ArrayAdapter(
            this,
            R.layout.spinner_element,
            specList.toList()
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(R.layout.spinner_element)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            }

        val appointButton : Button = findViewById(R.id.appoint_button)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}