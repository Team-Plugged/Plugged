package com.plugged.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.plugged.models.LoginResponse
import com.plugged.models.Token


@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patient: LoginResponse):Long

    @Query("SELECT * FROM patient")
    fun getPatient(): LiveData<LoginResponse>

    @Query("DELETE FROM patient")
    suspend fun deletePatient()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertToken(token: Token):Long

    @Query("SELECT * FROM token")
    fun getToken(): LiveData<Token>

    @Query("DELETE FROM token")
    suspend fun deleteToken()



}