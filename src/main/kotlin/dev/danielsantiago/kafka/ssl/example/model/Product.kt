package dev.danielsantiago.kafka.ssl.example.model

import java.time.ZoneId
import java.time.ZonedDateTime

data class Product(
    val id: Long,
    val name: String,
    val priceCents: Long,
    val createdAt: ZonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
) {

}
