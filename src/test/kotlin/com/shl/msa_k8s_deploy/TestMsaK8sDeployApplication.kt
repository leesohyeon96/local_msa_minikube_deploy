package com.shl.msa_k8s_deploy

import org.springframework.boot.fromApplication
import org.springframework.boot.with


fun main(args: Array<String>) {
    fromApplication<MsaK8sDeployApplication>().with(TestcontainersConfiguration::class).run(*args)
}
