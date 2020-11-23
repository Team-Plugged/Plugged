package com.plugged.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "patient"
)
data class LoginResponse(
    val _id: String,
    @PrimaryKey(autoGenerate = true)
    val age: Int,
    val email: String,
    val firstname: String,
    val gender: String,
    val genotype: String,
    val height: Int,
    val image: String,
    val isAdmin: Boolean,
    val lastname: String,
    val token: String,
    val weight: Int
)