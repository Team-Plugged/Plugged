package com.plugged.api

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.plugged.models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiHelper {

    suspend fun register_patient(patient: RegPatient): Response<Reg_PatientResponse>
    suspend fun login_Patient(login: Login):Response<LoginResponse>

    suspend fun insert(patient:LoginResponse):Long
    fun getPatient():LiveData<LoginResponse>
    suspend fun deletePatient()

    suspend fun register_hospital(register_hospital: RegisterHospital): Response<RegisterHospitalResponse>

    suspend fun login_hospital(login_hospital: Login): Response<LoginHospitalResponse>

    suspend fun addRecord(token: String,addRecord: AddRecord): Response<AddRecordResponse>

    suspend fun uplodImage(image: MultipartBody.Part
    ): Response<UploadImageResponse>

    suspend fun searchRecord(query: SearchBody):Response<SearchResponse>


}