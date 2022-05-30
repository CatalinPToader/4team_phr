package com.example.a4team_phr

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.a4team_phr.databinding.PatientListBinding
import org.json.JSONArray
import java.net.CookieHandler
import java.net.CookieManager
import java.net.URL

class PatientListActivity : AppCompatActivity() {

    private var binding: PatientListBinding? = null
    private var adapter: PatientAdapter? = null
    private lateinit var cm : CookieManager

    private val urlBase = "http://kare.go.ro:8000/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PatientListBinding.inflate(layoutInflater)
        setContentView(binding?.root)
        cm = CookieHandler.getDefault() as CookieManager
        setUpRecyclerView()
        setUpSearchView()
    }

    private fun setUpSearchView() {
        binding?.searchBar?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter?.getFilter()?.filter(query)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter?.getFilter()?.filter(newText)
                return true
            }

        })
    }

    private fun setUpRecyclerView() {
        //attach layout manager
        binding?.patientList?.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        //add item decoration for divider
        val itemDecorator = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        ContextCompat.getDrawable(this, R.drawable.line_divider)?.let { itemDecorator.setDrawable(it) }
        binding?.patientList?.addItemDecoration(itemDecorator)

        val patientJsonList = JSONArray(URL(urlBase + "pacient/").readText())

        val patientList = ArrayList<Patient>()
        for (i in 0 until patientJsonList.length()) {
            val patientJson = patientJsonList.getJSONObject(i)
            patientList.add(Patient(patientJson.getString("Nume") + " " + patientJson.getString("Prenume"), patientJson.getString("CNP")))
        }
        //attach adapter to list
        adapter = PatientAdapter(patientList)
        binding?.patientList?.adapter = adapter
    }
}