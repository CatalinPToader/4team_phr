package com.example.a4team_phr

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class PatientAdapter(private var patientList: ArrayList<Patient>) : RecyclerView.Adapter<PatientAdapter.ViewHolder>(){

    val initialPatientList = ArrayList<Patient>().apply {
        addAll(patientList)
    }

    private val patientFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList: ArrayList<Patient> = ArrayList()
            if (constraint == null || constraint.isEmpty()) {
                initialPatientList.let { filteredList.addAll(it) }
            } else {
                val query = constraint.toString().trim().lowercase(Locale.getDefault())
                initialPatientList.forEach {
                    if (it.name.lowercase(Locale.ROOT).contains(query) || it.CNP.lowercase(Locale.ROOT).contains(query)) {
                        filteredList.add(it)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            if (results?.values is ArrayList<*>) {
                patientList.clear()
                patientList.addAll(results.values as ArrayList<Patient>)
                notifyDataSetChanged()
            }
        }
    }

    fun getFilter(): Filter {
        return patientFilter
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view : View = LayoutInflater.from(parent.context).inflate(R.layout.patient_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val patient : Patient = patientList[position]
        holder.nameTV.text = patient.name
        holder.cnpTV.text = patient.CNP
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nameTV : TextView = itemView.findViewById(R.id.pacient_name)
        val cnpTV : TextView = itemView.findViewById(R.id.pacient_cnp)
    }
}