# 🚀 로컬 실행 가이드 (Getting Started)

이 문서는 프로젝트를 로컬에서 실행하는 **실전 가이드**입니다.

## 📋 사전 준비

### 필수 설치
- ✅ JDK 21
- ✅ Docker Desktop (Testcontainers용)

### 선택 설치
- Minikube (Kubernetes 실습용)
- kubectl (Kubernetes CLI)

## 🎯 난이도별 실행 방법

---

## Level 1: 기본 빌드 및 실행 ⭐

### 1-1. 빌드만 해보기
```bash
# 테스트 제외하고 빌드
./gradlew clean build -x test

# 성공하면 각 서비스의 build/libs/ 에 JAR 파일 생성됨
ls product-service/build/libs/
ls order-service/build/libs/
ls payment-service/build/libs/
```

### 1-2. 서비스 실행 (포트 충돌 해결)
각 서비스를 **다른 터미널**에서 실행하세요:

```bash
# Terminal 1: Product Service
./gradlew :product-service:bootRun --args='--server.port=8081'

# Terminal 2: Order Service
./gradlew :order-service:bootRun --args='--server.port=8082'

# Terminal 3: Payment Service
./gradlew :payment-service:bootRun --args='--server.port=8083'
```

### 1-3. 확인
```bash
# Product Service
curl http://localhost:8081

# Order Service
curl http://localhost:8082

# Payment Service
curl http://localhost:8083
```

**에러가 나도 정상!** 아직 API 엔드포인트를 만들지 않았으니까요.
Whitelabel Error Page가 보이면 **서비스가 정상 작동**하는 겁니다! ✅

---

## Level 2: 테스트 실행 ⭐⭐

### 2-1. Docker Desktop 실행 확인
```bash
# Docker가 실행중인지 확인
docker ps

# 안되면 Docker Desktop을 실행하세요!
```

### 2-2. 테스트 실행
```bash
# 전체 테스트
./gradlew test

# 개별 서비스 테스트
./gradlew :product-service:test
./gradlew :order-service:test
./gradlew :payment-service:test

# Testcontainers만 실행
./gradlew :product-service:test --tests "*ContainerTest"
```

**주의:** 첫 실행 시 Redis 이미지를 다운로드하므로 시간이 걸립니다!

---

## Level 3: Docker 컨테이너로 실행 ⭐⭐⭐

### 3-1. JAR 파일 빌드
```bash
./gradlew :product-service:bootJar
./gradlew :order-service:bootJar
./gradlew :payment-service:bootJar
```

### 3-2. Docker 이미지 빌드
```bash
# Product Service
cd product-service
docker build -t product:1.0 .
cd ..

# Order Service
cd order-service
docker build -t order:1.0 .
cd ..

# Payment Service
cd payment-service
docker build -t payment:1.0 .
cd ..
```

### 3-3. 이미지 확인
```bash
docker images | grep -E "product|order|payment"
```

### 3-4. 컨테이너 실행
```bash
# Product Service
docker run -d --name product -p 8081:8080 product:1.0

# Order Service
docker run -d --name order -p 8082:8080 order:1.0

# Payment Service
docker run -d --name payment -p 8083:8080 payment:1.0
```

### 3-5. 확인
```bash
# 컨테이너 상태 확인
docker ps

# 로그 확인
docker logs product
docker logs order
docker logs payment

# 접속 테스트
curl http://localhost:8081
curl http://localhost:8082
curl http://localhost:8083
```

### 3-6. 정리
```bash
# 컨테이너 중지 및 삭제
docker stop product order payment
docker rm product order payment

# 이미지 삭제 (필요시)
docker rmi product:1.0 order:1.0 payment:1.0
```

---

## Level 4: Kubernetes 배포 ⭐⭐⭐⭐

### 4-1. Minikube 시작
```bash
minikube start

# 상태 확인
minikube status
kubectl cluster-info
```

### 4-2. Minikube 내부에 이미지 빌드
```bash
# Minikube의 Docker 환경 사용
eval $(minikube docker-env)

# 이미지 빌드 (Minikube 내부에서)
cd product-service && docker build -t product:latest . && cd ..
cd order-service && docker build -t order:latest . && cd ..
cd payment-service && docker build -t payment:latest . && cd ..

# 확인
docker images
```

### 4-3. Deployment YAML 수정
K8s 매니페스트에서 이미지 이름을 수정해야 합니다:

**k8s/product-deployment.yaml:**
```yaml
# 수정 전
# image: your-dockerhub-username/product:latest

# 수정 후
image: product:latest
imagePullPolicy: Never  # 추가!
```

### 4-4. 배포
```bash
# 개별 배포
kubectl apply -f k8s/product-deployment.yaml
kubectl apply -f k8s/service.yaml

# 또는 전체 배포
kubectl apply -f k8s/
```

### 4-5. 확인
```bash
# Pod 상태 확인
kubectl get pods

# Service 확인
kubectl get services

# 상세 정보
kubectl describe pod <pod-name>

# 로그 확인
kubectl logs <pod-name>
```

### 4-6. 서비스 접근
```bash
# Service 포트포워딩
kubectl port-forward service/product-service 8081:80

# 다른 터미널에서 테스트
curl http://localhost:8081
```

### 4-7. 정리
```bash
# 리소스 삭제
kubectl delete -f k8s/

# Minikube 중지
minikube stop

# Minikube 삭제 (필요시)
minikube delete
```

---

## 🐛 문제 해결

### 포트 충돌 에러
```
Port 8080 is already in use
```
**해결:** 다른 포트 사용 `--args='--server.port=8081'`

### Docker 연결 에러
```
Cannot connect to the Docker daemon
```
**해결:** Docker Desktop 실행

### Testcontainers 에러
```
Could not find a valid Docker environment
```
**해결:** Docker Desktop이 실행중인지 확인

### Minikube 이미지 못 찾음
```
ImagePullBackOff
```
**해결:** 
1. `eval $(minikube docker-env)` 실행
2. `imagePullPolicy: Never` 추가

---

## 📚 다음 단계

1. **REST API 추가** - Controller 만들기
2. **서비스 간 통신** - RestTemplate/WebClient
3. **실제 DB 연결** - PostgreSQL, MySQL
4. **CI/CD 파이프라인** - GitHub Actions

---

**Happy Learning! 🎓**
