package com.plugged.models

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(
    tableName = "patient"
)
data class LoginResponse(
    val _id: String,
    val address: String,
    val contactInfo: String,
    val dateOfBirth: String,
    val email: String,
    val firstname: String,
    val gender: String,
    val genotype: String,
    @PrimaryKey(autoGenerate = true)
    val height: Int,
    val image: String,
    val isAdmin: Boolean,
    val lastname: String,
    val token: String,
    val weight: Int
)