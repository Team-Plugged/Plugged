package com.plugged.repository

import androidx.lifecycle.LiveData
import com.plugged.api.ApiHelper
import com.plugged.models.*
import retrofit2.Response
import javax.inject.Inject

class PluggedRepository@Inject constructor(private val apiHelper: ApiHelper)  {

    suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse> = apiHelper.register_patient(patient)
    suspend fun  login_patient(login: Login): Response<LoginResponse> = apiHelper.login_Patient(login)

    suspend fun insert(patient:LoginResponse)=apiHelper.insert(patient)
    fun getPatient()=apiHelper.getPatient()
    suspend fun deletePatient(patient: LoginResponse)=apiHelper.deletePatient(patient)

    suspend fun register_hospital(register_hospital: RegisterHospital)=apiHelper.register_hospital(register_hospital)

    suspend fun login_hospital(login_hospital: Login)=apiHelper.login_hospital(login_hospital)


}