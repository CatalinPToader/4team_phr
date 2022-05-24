package com.example.a4team_phr

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import java.net.CookieHandler
import java.net.CookieManager


class LoginActivity : AppCompatActivity() {
    private val cm : CookieManager = CookieManager()
    private val urlBase = "http://10.0.2.2:8000/"

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        CookieHandler.setDefault(cm)

        switchToLogin()
    }

    private fun switchToLogin() {
        setContentView(R.layout.login_screen)
        val loginButton : Button = findViewById(R.id.login_button)
        val email : EditText = findViewById(R.id.login_email)
        val pass : EditText = findViewById(R.id.login_password)
        loginButton.setOnClickListener(LoginButton(email, pass, cm, this))

        val signupButton : Button = findViewById(R.id.sign_up_button)
        signupButton.setOnClickListener { switchToSignUp() }
    }

    fun switchToMain() {
        val intent = Intent(this, MainPatientActivity::class.java)
        intent.putExtra("CookieManager", Gson().toJson(cm))
        startActivity(intent)
    }

    private fun switchToSignUp() {
        val intent = Intent(this, SignupActivity::class.java)
        startActivity(intent)
    }
}