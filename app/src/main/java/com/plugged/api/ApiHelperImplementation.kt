package com.plugged.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plugged.db.PatientDao
import com.plugged.db.PatientDb
import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import retrofit2.Response
import javax.inject.Inject

class ApiHelperImplementation @Inject constructor(private val api: PluggedApi, val db: PatientDao):ApiHelper {
    override suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse> {
        return api.register_patient(patient)
    }

    override suspend fun login_Patient(login: Login): Response<LoginResponse> {

        return api.login_patient(login)
    }

    override suspend fun insert(patient: LoginResponse): Long {
        return db.insert(patient)
    }

    override fun getPatient(): LiveData<LoginResponse> {
        return db.getPatient()
    }

    override suspend fun deletePatient(patient: LoginResponse) {
        return db.deletePatient(patient)
    }
}