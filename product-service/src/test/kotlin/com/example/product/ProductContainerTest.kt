package com.example.product

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.testcontainers.containers.GenericContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
@SpringBootTest
class ProductContainerTest {

    @Container
    val redis = GenericContainer("redis:7-alpine").apply {
        withExposedPorts(6379)
    }

    @Test
    fun `container runs`() {
        assert(redis.isRunning)
        val port = redis.getMappedPort(6379)
        println("Redis container running on port: $port")
    }
}
