package com.shl.local_msa_minikube_deploy

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MsaK8sDeployApplication

fun main(args: Array<String>) {
    runApplication<MsaK8sDeployApplication>(*args)
}
