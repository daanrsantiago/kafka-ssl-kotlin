package dev.danielsantiago.kafka.ssl.example

import dev.danielsantiago.kafka.ssl.example.model.Costumer
import dev.danielsantiago.kafka.ssl.example.model.Order
import dev.danielsantiago.kafka.ssl.example.model.OrderProduct
import dev.danielsantiago.kafka.ssl.example.model.Product
import dev.danielsantiago.kafka.ssl.example.producer.OrderProducer
import java.util.*

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    val orderProducer = OrderProducer()

    val date = GregorianCalendar(1996, 2, 22).time

    for (i in 1..5) {
        orderProducer.publishOrder(
            Order(
                orderId = i.toLong(),
                costumer = Costumer(
                    id = 1L,
                    name = "John Doe",
                    birthDate = date,
                    phoneNumber = "+5534912345678",
                    email = "johndoe@email.com",
                    address = "Av john doe, 1000"
                ),
                products = listOf(
                    OrderProduct(
                        product = Product(
                            id =  10L,
                            name = "chair",
                            priceCents = 5500L,
                        ),
                        amount = 3
                    )
                ),
                totalPriceCents = 16500L
            )
        )
    }

    orderProducer.producer.close()
}