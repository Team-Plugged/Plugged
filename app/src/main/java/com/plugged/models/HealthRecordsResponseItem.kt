package com.plugged.models

data class HealthRecordsResponseItem(
    val __v: Int,
    val _id: String,
    val allergies: String,
    val createdAt: String,
    val diagnosis: String,
    val hospital: Hospital,
    val notes: String,
    val patientEmail: String,
    val prescription: String,
    val symptoms: String,
    val updatedAt: String
)