package com.plugged.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plugged.api.ApiHelper
import com.plugged.models.Login
import com.plugged.models.LoginResponse
import com.plugged.models.RegPatient
import com.plugged.models.Reg_PatientResponse
import com.plugged.repository.PluggedRepository
import com.plugged.ui.RegisterPatient
import com.plugged.utils.NetWorkHelper
import com.plugged.utils.Resource
import kotlinx.coroutines.launch
import java.io.IOException

class PluggedViewModel @ViewModelInject constructor(
    private val repository: PluggedRepository,
    private val networkHelper: NetWorkHelper
) : ViewModel() {

    val loginResponse: MutableLiveData<Resource<LoginResponse>> = MutableLiveData()
    var login_data:LoginResponse?=null
    val registerPatientResponse: MutableLiveData<Resource<Reg_PatientResponse>> = MutableLiveData()
    var registerPatient_data:Reg_PatientResponse?=null


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







}