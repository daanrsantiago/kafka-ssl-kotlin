package dev.danielsantiago.kafka.ssl.example

import dev.danielsantiago.kafka.ssl.example.consumer.OrderConsumer

fun main() {
    val orderConsumer = OrderConsumer()

    orderConsumer.listenToOrders {
        println(
            it.toString()
        )
    }
}