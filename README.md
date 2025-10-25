# MSA K8s Deploy Template

A comprehensive MSA template project using Kotlin + Spring Boot + Testcontainers + Docker + Kubernetes.

## 🏗️ Project Structure

```
local_msa_minikube_deploy/
├── product-service/          # Product Service
├── order-service/           # Order Service  
├── payment-service/         # Payment Service
├── k8s/                     # Kubernetes Manifests
│   ├── product-deployment.yaml
│   ├── order-deployment.yaml
│   ├── payment-deployment.yaml
│   ├── service.yaml
│   └── ingress.yaml
└── README.md
```

## 🚀 Tech Stack

- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.4.10
- **Java**: OpenJDK 21
- **Build Tool**: Gradle 8.x
- **Testing**: Testcontainers (Redis)
- **Container**: Docker
- **Orchestration**: Kubernetes

## 📋 Service Features

### Product Service
- Product information management
- Port: 8080

### Order Service  
- Order processing
- Port: 8080

### Payment Service
- Payment processing
- Port: 8080

## 🛠️ Development Setup

### 1. Project Build
```bash
./gradlew build
```

### 2. Individual Service Execution
```bash
# Product Service
./gradlew :product-service:bootRun

# Order Service  
./gradlew :order-service:bootRun

# Payment Service
./gradlew :payment-service:bootRun
```

### 3. Test Execution
```bash
# All tests
./gradlew test

# Individual service tests
./gradlew :product-service:test
./gradlew :order-service:test
./gradlew :payment-service:test
```

## 🐳 Docker Build & Run

### 1. Docker Image Build
```bash
# Product Service
cd product-service
docker build -t your-dockerhub-username/product:latest .

# Order Service
cd order-service  
docker build -t your-dockerhub-username/order:latest .

# Payment Service
cd payment-service
docker build -t your-dockerhub-username/payment:latest .
```

### 2. Docker Compose Run (Optional)
```bash
docker-compose up -d
```

## ☸️ Kubernetes Deployment

### 1. Cluster Setup
```bash
# Start Minikube
minikube start

# Or Kind cluster
kind create cluster --name msa-cluster
```

### 2. Apply Manifests
```bash
# Deploy all services
kubectl apply -f k8s/

# Deploy individual services
kubectl apply -f k8s/product-deployment.yaml
kubectl apply -f k8s/order-deployment.yaml
kubectl apply -f k8s/payment-deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/ingress.yaml
```

### 3. Service Verification
```bash
# Check Pod status
kubectl get pods

# Check services
kubectl get services

# Check ingress
kubectl get ingress
```

### 4. Local Access (Minikube)
```bash
# Get Minikube IP
minikube ip

# Enable ingress
minikube addons enable ingress

# Add to /etc/hosts
echo "$(minikube ip) msa.local" | sudo tee -a /etc/hosts
```

## 🧪 Testcontainers Testing

Each service includes Testcontainers tests using Redis containers.

```bash
# Run Testcontainers tests
./gradlew :product-service:test --tests "*ContainerTest"
```

## 🔧 CI/CD Ready

This template is ready for the following CI/CD pipelines:

- **GitHub Actions**
- **GitLab CI**  
- **Jenkins**
- **Azure DevOps**

### GitHub Actions Example
```yaml
name: Build and Deploy
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
    - name: Build
      run: ./gradlew build
    - name: Test
      run: ./gradlew test
```

## 📝 Key Features

- ✅ **Multi-Module Gradle** structure
- ✅ **Kotlin + Spring Boot** latest version
- ✅ **Testcontainers** integration testing
- ✅ **Docker** containerization
- ✅ **Kubernetes** orchestration
- ✅ **CI/CD** ready
- ✅ **MSA** architecture

## 🎯 Use Cases

This template can be used for:

- MSA architecture learning
- Kubernetes deployment practice
- DevOps pipeline construction
- Portfolio projects
- CI/CD practice

---

# MSA K8s Deploy 템플릿

Kotlin + Spring Boot + Testcontainers + Docker + Kubernetes를 활용한 MSA 템플릿 프로젝트입니다.

## 🏗️ 프로젝트 구조

```
local_msa_minikube_deploy/
├── product-service/          # 상품 서비스
├── order-service/           # 주문 서비스  
├── payment-service/         # 결제 서비스
├── k8s/                     # Kubernetes 매니페스트
│   ├── product-deployment.yaml
│   ├── order-deployment.yaml
│   ├── payment-deployment.yaml
│   ├── service.yaml
│   └── ingress.yaml
└── README.md
```

## 🚀 기술 스택

- **Language**: Kotlin 1.9.25
- **Framework**: Spring Boot 3.4.10
- **Java**: OpenJDK 21
- **Build Tool**: Gradle 8.x
- **Testing**: Testcontainers (Redis)
- **Container**: Docker
- **Orchestration**: Kubernetes

## 📋 서비스별 기능

### Product Service
- 상품 정보 관리
- 포트: 8080

### Order Service  
- 주문 처리
- 포트: 8080

### Payment Service
- 결제 처리
- 포트: 8080

## 🛠️ 개발 환경 설정

### 1. 프로젝트 빌드
```bash
./gradlew build
```

### 2. 개별 서비스 실행
```bash
# Product Service
./gradlew :product-service:bootRun

# Order Service  
./gradlew :order-service:bootRun

# Payment Service
./gradlew :payment-service:bootRun
```

### 3. 테스트 실행
```bash
# 전체 테스트
./gradlew test

# 개별 서비스 테스트
./gradlew :product-service:test
./gradlew :order-service:test
./gradlew :payment-service:test
```

## 🐳 Docker 빌드 및 실행

### 1. Docker 이미지 빌드
```bash
# Product Service
cd product-service
docker build -t your-dockerhub-username/product:latest .

# Order Service
cd order-service  
docker build -t your-dockerhub-username/order:latest .

# Payment Service
cd payment-service
docker build -t your-dockerhub-username/payment:latest .
```

### 2. Docker Compose 실행 (선택사항)
```bash
docker-compose up -d
```

## ☸️ Kubernetes 배포

### 1. 클러스터 준비
```bash
# Minikube 시작
minikube start

# 또는 Kind 클러스터
kind create cluster --name msa-cluster
```

### 2. 매니페스트 적용
```bash
# 모든 서비스 배포
kubectl apply -f k8s/

# 개별 서비스 배포
kubectl apply -f k8s/product-deployment.yaml
kubectl apply -f k8s/order-deployment.yaml
kubectl apply -f k8s/payment-deployment.yaml
kubectl apply -f k8s/service.yaml
kubectl apply -f k8s/ingress.yaml
```

### 3. 서비스 확인
```bash
# Pod 상태 확인
kubectl get pods

# 서비스 확인
kubectl get services

# Ingress 확인
kubectl get ingress
```

### 4. 로컬 접근 (Minikube)
```bash
# Minikube IP 확인
minikube ip

# Ingress 활성화
minikube addons enable ingress

# /etc/hosts에 추가
echo "$(minikube ip) msa.local" | sudo tee -a /etc/hosts
```

## 🧪 Testcontainers 테스트

각 서비스는 Redis 컨테이너를 사용한 Testcontainers 테스트를 포함합니다.

```bash
# Testcontainers 테스트 실행
./gradlew :product-service:test --tests "*ContainerTest"
```

## 🔧 CI/CD 준비

이 템플릿은 다음 CI/CD 파이프라인에 바로 사용 가능합니다:

- **GitHub Actions**
- **GitLab CI**  
- **Jenkins**
- **Azure DevOps**

### GitHub Actions 예시
```yaml
name: Build and Deploy
on: [push]
jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v3
    - name: Set up JDK 21
      uses: actions/setup-java@v3
      with:
        java-version: '21'
        distribution: 'temurin'
    - name: Build
      run: ./gradlew build
    - name: Test
      run: ./gradlew test
```

## 📝 주요 특징

- ✅ **Multi-Module Gradle** 구조
- ✅ **Kotlin + Spring Boot** 최신 버전
- ✅ **Testcontainers** 통합 테스트
- ✅ **Docker** 컨테이너화
- ✅ **Kubernetes** 오케스트레이션
- ✅ **CI/CD** 준비 완료
- ✅ **MSA** 아키텍처

## 🎯 사용 목적

이 템플릿은 다음 목적으로 활용할 수 있습니다:

- MSA 아키텍처 학습
- Kubernetes 배포 실습
- DevOps 파이프라인 구축
- 포트폴리오 프로젝트
- CI/CD 실습

---
