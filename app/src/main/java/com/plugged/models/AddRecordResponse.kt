package com.plugged.models

data class AddRecordResponse(
    val _id: String,
    val allergies: String,
    val diagnosis: String,
    val hospital: String,
    val notes: String,
    val patientEmail: String,
    val prescription: String,
    val symptoms: String
)