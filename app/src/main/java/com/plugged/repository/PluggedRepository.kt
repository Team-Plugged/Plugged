package com.plugged.repository

import com.plugged.api.ApiHelper
import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import retrofit2.Response
import javax.inject.Inject

class PluggedRepository@Inject constructor(private val apiHelper: ApiHelper)  {

    suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse> = apiHelper.register_patient(patient)
    suspend fun  login_patient(login: Login): Response<LoginResponse> = apiHelper.login_Patient(login)


}