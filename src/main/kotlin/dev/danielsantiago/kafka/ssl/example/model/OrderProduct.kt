package dev.danielsantiago.kafka.ssl.example.model

data class OrderProduct(
    val product: Product,
    val amount: Int = 1
)
