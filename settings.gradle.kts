rootProject.name = "local_msa_minikube_deploy"

// module structure define (모듈 구조 정의) > product / order / payment
include("product-service")
include("order-service")
include("payment-service")
