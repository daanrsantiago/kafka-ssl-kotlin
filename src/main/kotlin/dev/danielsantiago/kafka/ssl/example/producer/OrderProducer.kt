package dev.danielsantiago.kafka.ssl.example.producer

import dev.danielsantiago.kafka.ssl.example.model.Order
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.ProducerRecord
import java.util.Properties

class OrderProducer {

    private val props = Properties()
    val producer: KafkaProducer<String, Order>
    private val ORDER_TOPIC = "orders"

    init {
        props["bootstrap.servers"] = "localhost:9092"
        props["security.protocol"] = "SSL"
        props["ssl.truststore.location"] = ".\\kafka-creds\\client.truststore.jks"
        props["ssl.truststore.password"] = "123456"
        props["key.serializer"] = "org.apache.kafka.common.serialization.StringSerializer"
        props["value.serializer"] = "dev.danielsantiago.kafka.ssl.example.config.serializer.OrderSerializer"
        producer = KafkaProducer(props)
    }

    fun publishOrder(order: Order) {
        println("Sending order ${order.orderId}")
        producer.send(ProducerRecord(ORDER_TOPIC,order.orderId.toString(), order)) { metadata, ex ->
            println("Sent order ${order.orderId} to partition ${metadata.partition()}")
        }
    }
}