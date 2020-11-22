package com.plugged.api

import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(private val api: PluggedApi):ApiHelper {
    override suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse> {
        return api.register_patient(patient)
    }

    override suspend fun login_Patient(login: Login): Response<LoginResponse> {

        return api.login_patient(login)
    }
}