package dev.danielsantiago.kafka.ssl.example.config.serializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import dev.danielsantiago.kafka.ssl.example.model.Order
import org.apache.kafka.common.serialization.Serializer

class OrderSerializer: Serializer<Order> {

    private val objectMapper = ObjectMapper()
        .registerModule(JavaTimeModule())

    override fun serialize(topic: String?, order: Order?): ByteArray? {
        try {
            if (order == null) return null
            return objectMapper.writeValueAsBytes(order)
        } catch (e: Exception) {
            println(e.message)
            e.printStackTrace()
            println("Error while serializing order")
            return null
        }
    }
}