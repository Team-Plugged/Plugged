package com.plugged.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plugged.db.PatientDao
import com.plugged.db.PatientDb
import com.plugged.models.*
import okhttp3.MultipartBody
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

    override suspend fun deletePatient() {
        return db.deletePatient()
    }

    override suspend fun register_hospital(register_hospital: RegisterHospital): Response<RegisterHospitalResponse> {
        return api.register_hospital(register_hospital)
    }

    override suspend fun login_hospital(login_hospital: Login): Response<LoginHospitalResponse> {
       return  api.login_hospital(login_hospital)
    }

    override suspend fun addRecord(token: String,addRecord: AddRecord): Response<AddRecordResponse> {
        return  api.addRecord(token,addRecord)
    }

    override suspend fun uplodImage(image: MultipartBody.Part): Response<UploadImageResponse> {
        return  api.uplodImage(image)
    }

    override suspend fun searchRecord(query: SearchBody): Response<SearchResponse> {
        return api.searchRecord(query)
    }
}