package com.example.payment

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PaymentController {

    @GetMapping("/")
    fun home(): String {
        return "Payment Service is running! 💳"
    }

    @GetMapping("/payments")
    fun getPayments(): List<Payment> {
        return listOf(
            Payment(1, "PAY-001", 2500000, "SUCCESS"),
            Payment(2, "PAY-002", 1200000, "SUCCESS"),
            Payment(3, "PAY-003", 350000, "PENDING")
        )
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf(
            "status" to "UP",
            "service" to "payment-service"
        )
    }
}

data class Payment(
    val id: Int,
    val paymentNumber: String,
    val amount: Int,
    val status: String
)

