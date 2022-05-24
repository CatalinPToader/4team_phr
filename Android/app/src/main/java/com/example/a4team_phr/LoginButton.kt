package com.example.a4team_phr

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Build
import android.util.Log
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

class LoginButton(email: EditText, pass: EditText, private val cm: CookieManager, main_activity: LoginActivity) : View.OnClickListener {
    private val url = URL("http://10.0.2.2:8000/login/")
    private val e = email
    private val p = pass
    private val activity = main_activity

    @RequiresApi(Build.VERSION_CODES.R)
    class WrongDataCallback(private val email: EditText, private val pass: EditText) : Toast.Callback() {
        override fun onToastShown() {
            email.backgroundTintList = ColorStateList.valueOf(Color.RED)
            pass.backgroundTintList = ColorStateList.valueOf(Color.RED)
        }

        override fun onToastHidden() {
            email.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
            pass.backgroundTintList = ColorStateList.valueOf(Color.BLACK)
        }
    }

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onClick(p0: View) {
        val loginDetails = JSONObject()
        loginDetails.accumulate("Email", e.text)
        loginDetails.accumulate("Password", p.text)

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
                    if (response.toString() == "\"Login Success\"") {
                        activity.switchToMain()
                    } else {
                        val toast = Toast.makeText(
                            activity.applicationContext,
                            response.substring(1, response.length - 1),
                            Toast.LENGTH_LONG
                        )
                        toast.addCallback(WrongDataCallback(e, p))
                        toast.show()
                    }
                }

                for (cookie in cm.cookieStore.cookies)
                    println("Name: ${cookie.name}, Value: ${cookie.value}")
            } catch (e: Exception) {
                Log.d("LogError", e.toString())
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