package com.plugged.models

data class Reg_PatientResponse(
    val _id: String,
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