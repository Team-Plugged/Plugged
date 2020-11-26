package com.plugged.models

data class AddRecord(
    val allergies: String,
    val diagnosis: String,
    val notes: String,
    val patientEmail: String,
    val prescription: String,
    val symptoms: String
)