package com.example.product

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController {

    @GetMapping("/")
    fun home(): String {
        return "Product Service is running! 🚀"
    }

    @GetMapping("/products")
    fun getProducts(): List<Product> {
        return listOf(
            Product(1, "MacBook Pro", 2500000),
            Product(2, "iPhone 15", 1200000),
            Product(3, "AirPods Pro", 350000)
        )
    }

    @GetMapping("/health")
    fun health(): Map<String, String> {
        return mapOf(
            "status" to "UP",
            "service" to "product-service"
        )
    }
}

data class Product(
    val id: Int,
    val name: String,
    val price: Int
)

