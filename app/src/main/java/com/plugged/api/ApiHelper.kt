package com.plugged.api

import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import retrofit2.Response

interface ApiHelper {

    suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse>
    suspend fun login_Patient(login: Login):Response<LoginResponse>
}