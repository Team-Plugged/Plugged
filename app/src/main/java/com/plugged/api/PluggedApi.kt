package com.plugged.api

import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface PluggedApi {

    @Headers("Content-Type: application/json")
    @POST("patients")
    suspend fun register_patient(@Body patient:RegPatient): Response<Reg_PatientResponse>


    @Headers("Content-Type: application/json")
    @POST("patients/login")
    suspend fun login_patient(@Body login: Login):Response<LoginResponse>

}