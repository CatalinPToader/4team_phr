package com.example.a4team_phr

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import org.json.JSONArray
import java.net.URL


class MainActivity : AppCompatActivity(), View.OnClickListener {
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder()
            .permitAll().build()
        StrictMode.setThreadPolicy(policy)
        setContentView(R.layout.activity_main)
        val button : Button = findViewById<Button>(R.id.button)
        button.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.button->{
                val cardView = findViewById<CardView>(R.id.card_view)
                cardView.visibility = View.VISIBLE
                val json = JSONArray(URL("http://10.0.2.2:8000/medic/").readText())
                val textView: TextView = findViewById<TextView>(R.id.textView2)
                textView.text = json[0].toString()
            }
        }
    }
}