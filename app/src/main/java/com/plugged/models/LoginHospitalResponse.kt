package com.plugged.models

data class LoginHospitalResponse(
    val _id: String,
    val address: String,
    val email: String,
    val hospital: String,
    val isAdmin: Boolean,
    val isVerified: Boolean,
    val token: String
)