package com.plugged.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plugged.models.LoginResponse

@Database(
    entities = [LoginResponse::class],
    version = 1
)
abstract class PatientDb : RoomDatabase(){

    abstract fun getPatientDao(): PatientDao
}