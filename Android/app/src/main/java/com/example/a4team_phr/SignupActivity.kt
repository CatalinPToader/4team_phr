package com.example.a4team_phr

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SignupActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup_screen)
        val signupButton : Button = findViewById(R.id.sign_up_button)
        val email : EditText = findViewById(R.id.signup_email)
        val pass : EditText = findViewById(R.id.signup_password)
        val firstName : EditText = findViewById(R.id.first_name)
        val lastName : EditText = findViewById(R.id.last_name)
        val cnp : EditText = findViewById(R.id.cnp)
        val phone : EditText = findViewById(R.id.phone)

        signupButton.setOnClickListener(SignupButton(email, pass, firstName, lastName, phone, cnp, this))
    }

    override fun onBackPressed() {
        super.onBackPressed()
        this.finish()
    }
}