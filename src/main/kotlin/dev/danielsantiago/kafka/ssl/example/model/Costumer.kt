package dev.danielsantiago.kafka.ssl.example.model

import java.util.Date

data class Costumer(
    val id: Long,
    val name: String,
    val birthDate: Date,
    val phoneNumber: String,
    val email: String,
    val address: String
)