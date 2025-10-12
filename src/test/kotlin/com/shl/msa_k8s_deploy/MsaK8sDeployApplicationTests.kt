package com.shl.msa_k8s_deploy

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import

@Import(TestcontainersConfiguration::class)
@SpringBootTest
class MsaK8sDeployApplicationTests {

    @Test
    fun contextLoads() {
    }

}
