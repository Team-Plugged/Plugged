package com.plugged.api

import com.plugged.models.*
import okhttp3.MultipartBody
import retrofit2.Response
import retrofit2.http.*

interface PluggedApi {

    @Headers("Content-Type: application/json")
    @POST("patients")
    suspend fun register_patient(@Body patient: RegPatient): Response<Reg_PatientResponse>


    @Headers("Content-Type: application/json")
    @POST("patients/login")
    suspend fun login_patient(@Body login: Login): Response<LoginResponse>

    @Headers("Content-Type: application/json")
    @POST("hospitals")
    suspend fun register_hospital(@Body register_hospital: RegisterHospital): Response<RegisterHospitalResponse>

    @Headers("Content-Type: application/json")
    @POST("hospitals/login")
    suspend fun login_hospital(@Body login_hospital: Login): Response<LoginHospitalResponse>


    @Headers("Content-Type: application/json")
    @POST("healthrecords")
    suspend fun addRecord(
        @Header("Authorization") token: String,
        @Body record: AddRecord
    ): Response<AddRecordResponse>


    @Headers("Content-Type: multipart/form-data")
    @Multipart
    @POST("upload")
    suspend fun uplodImage(
        @Part image: MultipartBody.Part
    ): Response<UploadImageResponse>


    //    @GET("patients/record")
    @Headers("Content-Type: application/json")
    @HTTP(method = "GET", path = "patients/record", hasBody = true)
    suspend fun searchRecord(@Body query: SearchBody): Response<SearchResponse>


}