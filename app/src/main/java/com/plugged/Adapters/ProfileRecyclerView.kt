package com.plugged.Adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.plugged.R
import com.plugged.models.HealthRecordsResponseItem
import kotlinx.android.synthetic.main.patient_record.view.*
import kotlinx.android.synthetic.main.patient_record.view.edit_prescription

class RecordsRecyclerView(private val ItemsList: List<HealthRecordsResponseItem>) :
    RecyclerView.Adapter<RecordsRecyclerView.RecyclerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {

        val items = LayoutInflater.from(parent.context)
            .inflate(
                R.layout.patient_record, parent
                , false
            )

        return RecyclerViewHolder(items)

    }

    override fun getItemCount(): Int {
        return ItemsList.size
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {

        holder.bind(ItemsList[position])
    }

    class RecyclerViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private val date: TextView = itemview.date
        private val prescription: TextView = itemview.edit_prescription
        private val diagnosis: TextView = itemview.diagnosis
        private val note: TextView = itemview.edit_notes
        private val allergies: TextView = itemview.edit_allergies
        private val symptoms: TextView = itemview.edit_symptoms





        fun bind(record: HealthRecordsResponseItem) {
            date.text = record.createdAt
            prescription.text=record.prescription
            diagnosis.text=record.diagnosis
            note.text=record.notes
            allergies.text=record.allergies
            symptoms.text=record.symptoms





        }
    }
}

