package com.example.a4team_phr

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.CookieHandler
import java.net.CookieManager
import java.net.URL

class MainPatientActivity : AppCompatActivity() {
    private val urlBase = "http://kare.go.ro:8000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_screen_pacient)
        val cm = CookieHandler.getDefault() as CookieManager
        val username : TextView = findViewById(R.id.user_name)

        var email = ""
        var urlData = ""
        for (cookie in cm.cookieStore.cookies) {
            Log.d("Cookie", cookie.name)
            if (cookie.name == "4team_phr_email")
                email = cookie.value
            else if (cookie.name == "4team_phr_login")
                urlData = cookie.value.substringBefore(':') + '/'
        }

        Log.d("URL", urlBase + urlData)

        val allUsers = JSONArray(URL(urlBase + urlData).readText())

        var user = JSONObject()
        for (i in 0 until allUsers.length()) {
            val book = allUsers.getJSONObject(i)
            if (book.getString("Email") == email) {
                user = book
            }
        }
        if (user.length() != 0)
            username.text = user.getString("Nume").plus(" ").plus(user.getString("Prenume"))

        val apptButton : ImageView = findViewById(R.id.apptImageButton)
        apptButton.setOnClickListener {
            val intent = Intent(this, AppointmentActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        (CookieHandler.getDefault() as CookieManager).cookieStore.removeAll()
    }
}