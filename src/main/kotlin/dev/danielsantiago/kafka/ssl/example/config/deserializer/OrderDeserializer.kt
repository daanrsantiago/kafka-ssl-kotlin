package dev.danielsantiago.kafka.ssl.example.config.deserializer

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import dev.danielsantiago.kafka.ssl.example.model.Order
import org.apache.kafka.common.serialization.Deserializer
import kotlin.text.Charsets.UTF_8

class OrderDeserializer: Deserializer<Order> {

    private val objectMapper = ObjectMapper()
        .registerKotlinModule()
        .registerModule(JavaTimeModule())

    override fun deserialize(topic: String?, data: ByteArray?): Order? {
        try {
            if(data == null) return null
            return objectMapper.readValue(String(data, UTF_8), Order::class.java)
        } catch (e: Exception) {
            println(e.message)
            println("Error while deserializing order")
            return null
        }
    }
}