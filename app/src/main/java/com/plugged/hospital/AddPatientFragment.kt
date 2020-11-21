package com.plugged.hospital

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.plugged.R
import kotlinx.android.synthetic.main.fragment_add_patient.*
import kotlinx.android.synthetic.main.fragment_register_patient.view.*
import kotlinx.android.synthetic.main.fragment_register_patient.view.gender_spinner

private const val REQUEST_CODE_IMAGE_PICK = 100

class AddPatientFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_patient, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spinner_gender = gender_spinner
        val spinner_blood_group = blood_group
        val spinner_genotype = genotype

        // Spinner For Gender
        var gender = "Male"
        val gender_type = resources.getStringArray(R.array.gender)
        if (spinner_gender != null){
            val adapter =
                activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, gender_type) }
            if (adapter != null) {
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            }
            spinner_gender.adapter = adapter

            spinner_gender.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    gender = parent.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    gender = "Male"
                }
            }

        }

        // Spinner for BloodGroup

        var bloodGroup = ""
        val blood_type = resources.getStringArray(R.array.blood_group)
        if (spinner_blood_group != null){
            val adapter =
                activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, blood_type) }
            if (adapter != null) {
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            }
            spinner_blood_group.adapter = adapter

            spinner_blood_group.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    bloodGroup = parent.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    bloodGroup = ""
                }
            }

        }

        // Spinner Genotype

        var genoType = ""
        val geno_type = resources.getStringArray(R.array.genotype)
        if (spinner_genotype != null){
            val adapter =
                activity?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, geno_type) }
            if (adapter != null) {
                adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            }
            spinner_genotype.adapter = adapter

            spinner_genotype.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View,
                    position: Int,
                    id: Long
                ) {
                    genoType = parent.getItemAtPosition(position).toString()

                }

                override fun onNothingSelected(parent: AdapterView<*>) {
                    genoType = ""
                }
            }

        }

        image.setOnClickListener {

            Intent(Intent.ACTION_GET_CONTENT).also {
                it.type = "image/*"
                startActivityForResult(
                    it,
                    REQUEST_CODE_IMAGE_PICK
                )
            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_IMAGE_PICK) {
            data?.data?.let {

            }
        }
    }


}