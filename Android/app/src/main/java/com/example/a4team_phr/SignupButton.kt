package com.example.a4team_phr

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.CookieManager
import java.net.HttpURLConnection
import java.net.URL

class SignupButton(private val email: EditText, private val pass: EditText,
                   private val fname: EditText, private val lname: EditText,
                   private val phone: EditText, private val CNP: EditText,
                   main_activity: MainActivity) : View.OnClickListener {
    private val url = URL("http://10.0.2.2:8000/signup/")
    private val activity = main_activity

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onClick(p0: View) {
        val loginDetails = JSONObject()
        loginDetails.accumulate("Email", email.text)
        loginDetails.accumulate("Password", pass.text)
        loginDetails.accumulate("Nume", lname.text)
        loginDetails.accumulate("Prenume", fname.text)
        loginDetails.accumulate("Telefon", phone.text)
        loginDetails.accumulate("CNP", CNP.text)
        loginDetails.accumulate("ID_Medic", null)
        loginDetails.accumulate("Pending_Delete", false)

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"

            try {
                val wr = OutputStreamWriter(outputStream)
                wr.write(loginDetails.toString())
                wr.flush()

                println("URL : $url")
                println("Response Code : $responseCode")

                BufferedReader(InputStreamReader(inputStream)).use {
                    val response = StringBuffer()

                    var inputLine = it.readLine()
                    while (inputLine != null) {
                        response.append(inputLine)
                        inputLine = it.readLine()
                    }
                    println("Response: $response")
                    if (response.toString() == "\"Registered Successfully\"") {
                        val toast = Toast.makeText(
                            activity.applicationContext,
                            response.substring(1, response.length - 1),
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                        activity.switchToLogin()
                    } else {
                        val toast = Toast.makeText(
                            activity.applicationContext,
                            response.substring(1, response.length - 1),
                            Toast.LENGTH_LONG
                        )
                        toast.show()
                    }
                }

            } catch (e: Exception) {
                val text = "Connection to server refused"
                val toast = Toast.makeText(
                    activity.applicationContext,
                    text,
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }
}