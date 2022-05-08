package com.example.a4team_phr

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import org.json.JSONArray
import org.json.JSONObject
import java.net.CookieHandler
import java.net.CookieManager
import java.net.URL


class MainActivity : AppCompatActivity() {
    private val cm : CookieManager = CookieManager()
    val urlBase = "http://10.0.2.2:8000/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        CookieHandler.setDefault(cm)

        setContentView(R.layout.activity_main)
        val button : Button = findViewById(R.id.login_button)
        val email : EditText = findViewById(R.id.login_email)
        val pass : EditText = findViewById(R.id.login_password)
        button.setOnClickListener(LoginButton(email, pass, cm, this))
    }

    public fun switchView() {
        setContentView(R.layout.main_screen)
        val username : TextView = findViewById(R.id.user_name)

        var email = ""
        var urlData = ""
        for (cookie in cm.cookieStore.cookies)
            if (cookie.name == "4team_phr_email")
                email = cookie.value
            else if (cookie.name == "4team_phr_login")
                urlData = cookie.value.substringBefore(':').plus("/")

        val allUsers = JSONArray(URL(urlBase.plus(urlData)).readText())

        var user : JSONObject = JSONObject()
        for (i in 0 until allUsers.length()) {
            val book = allUsers.getJSONObject(i)
            if (book.getString("Email") == email) {
                user = book
            }
        }
        if (user.length() != 0)
            username.text = user.getString("Nume").plus(" ").plus(user.getString("Prenume"))
    }
}