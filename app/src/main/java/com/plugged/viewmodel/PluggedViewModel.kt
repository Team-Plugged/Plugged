package com.plugged.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plugged.Auth.RegistrationFragment
import com.plugged.api.ApiHelper
import com.plugged.models.*
import com.plugged.repository.PluggedRepository
import com.plugged.utils.NetWorkHelper
import com.plugged.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class PluggedViewModel @ViewModelInject constructor(
    private val repository: PluggedRepository,
    private val networkHelper: NetWorkHelper,
    apiHelper: ApiHelper
) : ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var login_data:LoginResponse?=null
    var register_hospital__data:RegisterHospitalResponse?=null
    var login_hospital__data:LoginHospitalResponse?=null
    val registerPatientResponse: MutableLiveData<Resource<Reg_PatientResponse>> = MutableLiveData()
    var registerPatient_data:Reg_PatientResponse?=null
    val patientProfile:MutableLiveData<LoginResponse> = MutableLiveData()
    var patient_data: LiveData<LoginResponse> = MutableLiveData()

    var registerHospital:MutableLiveData<Resource<RegisterHospitalResponse>> = MutableLiveData()
    var loginHospital:MutableLiveData<Resource<LoginHospitalResponse>> = MutableLiveData()


    private suspend fun register_hospital(register_hospital: RegisterHospital){
        registerHospital.postValue(Resource.Loading())
        try{
            if (networkHelper.isNetworkConnected())
            {
                val response = repository.register_hospital(register_hospital)
                if (response.isSuccessful)
                {
                    response.body()?.let {result->
                        register_hospital__data = result

                        registerHospital.postValue(Resource.Success(register_hospital__data ?:result))

                    }
                }
                else{
                    registerHospital.postValue( Resource.Error(response.message()))
                }
            }

            else{
                loginResponse.postValue( Resource.Error("No Internet Connection"))

            }
        }

        catch (t: Throwable) {
            when (t) {
                is IOException -> loginResponse.postValue(Resource.Error("Network Error"))
                else -> loginResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private suspend fun login_hospital(login_hospital: Login){
        loginHospital.postValue(Resource.Loading())
        try{
            if (networkHelper.isNetworkConnected())
            {
                val response = repository.login_hospital(login_hospital)
                if (response.isSuccessful)
                {
                    response.body()?.let {result->
                        login_hospital__data = result

                        loginHospital.postValue(Resource.Success(login_hospital__data ?:result))

                    }
                }
                else{
                    loginHospital.postValue( Resource.Error(response.message()))
                }
            }

            else{
                loginResponse.postValue( Resource.Error("No Internet Connection"))

            }
        }

        catch (t: Throwable) {
            when (t) {
                is IOException -> loginResponse.postValue(Resource.Error("Network Error"))
                else -> loginResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }



    fun LoginHospital(login_hospital: Login) = viewModelScope.launch {
        login_hospital(login_hospital)
    }
    fun RegisterHospital(register_hospital: RegisterHospital)=viewModelScope.launch {
        register_hospital(register_hospital)
    }

    //LOgin Patient
   private suspend fun login_patient(login: Login){
        loginResponse.postValue(Resource.Loading())
        try{
            if (networkHelper.isNetworkConnected())
            {
                val response = repository.login_patient(login)
                if (response.isSuccessful)
                {
                    response.body()?.let {result->
                        login_data = result

                        loginResponse.postValue(Resource.Success(login_data ?:result))

                    }
                }
                else{
                        loginResponse.postValue( Resource.Error(response.message()))
                    }
            }

            else{
                loginResponse.postValue( Resource.Error("No Internet Connection"))

            }
        }

        catch (t: Throwable) {
            when (t) {
                is IOException -> loginResponse.postValue(Resource.Error("Network Error"))
                else -> loginResponse.postValue(Resource.Error("Conversion Error"))
            }
        }
    }


    private suspend fun register_patient(patient: RegPatient){
        registerPatientResponse.postValue(Resource.Loading())
        try{
            if (networkHelper.isNetworkConnected())
            {
                val response = repository.register_patient(patient)
                if (response.isSuccessful)
                {
                    response.body()?.let {result->
                        registerPatient_data = result

                        registerPatientResponse.postValue(Resource.Success(registerPatient_data ?:result))

                    }
                }

                else{
                    registerPatientResponse.postValue( Resource.Error(response.message()))
                }
            }

            else{
                registerPatientResponse.postValue( Resource.Error("No Internet Connection"))

            }
        }

        catch (t: Throwable) {
            when (t) {
                is IOException -> registerPatientResponse.postValue(Resource.Error("Network Error"))
                else -> registerPatientResponse.postValue(Resource.Error("Unexpected Error"))
            }
        }
    }

    fun LoginPatient(login: Login)=viewModelScope.launch {

        login_patient(login)
    }

    fun RegisterPatient(patient: RegPatient) = viewModelScope.launch {
        register_patient(patient)

    }



    fun savePatient(patient: LoginResponse) = viewModelScope.launch {
        repository.insert(patient)
    }

    fun deletePatient() = viewModelScope.launch {

        repository.deletePatient()
    }

    fun getPatient() = repository.getPatient()







}