package com.plugged.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.plugged.models.LoginResponse
import com.plugged.models.Token

@Database(
    entities = [LoginResponse::class,Token::class],
    version = 1
)
abstract class PatientDb : RoomDatabase(){

    abstract fun getPatientDao(): PatientDao
}