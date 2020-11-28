package com.plugged.models

data class Reg_PatientResponse(
    val _id: String,
    val address: String,
    val contactInfo: String,
    val dateOfBirth: String,
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