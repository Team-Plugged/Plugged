package com.plugged.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.plugged.models.LoginResponse


@Dao
interface PatientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(patient: LoginResponse):Long

    @Query("SELECT * FROM patient")
    fun getPatient(): LiveData<LoginResponse>

    @Query("DELETE FROM patient")
    suspend fun deletePatient()



}