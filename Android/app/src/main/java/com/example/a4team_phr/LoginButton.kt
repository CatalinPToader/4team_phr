package com.example.a4team_phr

import android.view.View
import android.widget.EditText
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.OutputStreamWriter
import java.net.CookieManager
import java.net.HttpURLConnection
import java.net.URL

class LoginButton(email: EditText, pass: EditText, private val cm: CookieManager, main_activity: MainActivity) : View.OnClickListener {
    private val url = URL("http://10.0.2.2:8000/login/")
    private val e = email
    private val p = pass
    private val activity = main_activity

    override fun onClick(p0: View) {
        val loginDetails = JSONObject()
        loginDetails.accumulate("Email", e.text)
        loginDetails.accumulate("Password", p.text)

        with(url.openConnection() as HttpURLConnection) {
            requestMethod = "POST"

            val wr = OutputStreamWriter(outputStream);
            wr.write(loginDetails.toString());
            wr.flush();

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
                    activity.switchView()
                }
            }

            for (cookie in cm.cookieStore.cookies)
                println("Name: ${cookie.name}, Value: ${cookie.value}")
        }
    }
}