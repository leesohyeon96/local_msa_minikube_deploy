package com.example.order

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class OrderController {

    @GetMapping("/")
    fun home(): String {
        return "Order Service is running! 📦"
    }

    @GetMapping("/orders")
    fun getOrders(): List<Order> {
        return listOf(
            Order(1, "ORD-001", "MacBook Pro", "PENDING"),
            Order(2, "ORD-002", "iPhone 15", "COMPLETED"),
            Order(3, "ORD-003", "AirPods Pro", "SHIPPED")
        )
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf(
            "status" to "UP",
            "service" to "order-service"
        )
    }
}

data class Order(
    val id: Int,
    val orderNumber: String,
    val productName: String,
    val status: String
)

