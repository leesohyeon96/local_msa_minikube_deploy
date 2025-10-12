// common build settings (공통 빌드 설정)

plugins {
    kotlin("jvm") version "1.9.25" apply false
    kotlin("plugin.spring") version "1.9.25" apply false
    id("org.springframework.boot") version "3.4.10" apply false
    // manage a spring project dependency version (스프링 프로젝트 의존성 버전을 관리 - 자동으로 맞춰줌)
    // * apply false : parent module doesn't use this option & configure a children module
    //                 ex. you can check it in children modules like 'payment gradle settings'
    id("io.spring.dependency-management") version "1.1.7" apply false
}

allprojects {
    group = "com.shl"
    version = "0.0.1-SNAPSHOT"
    
    repositories {
        mavenCentral()
    }
}

// configure children modules from parent module (하위모듈 공통설정 || 루트 제외)
subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm") // kotlin JVM plugin applied || enable kotlin compilation (코틀린 jvm 플러그인 적용 || 코틀린 컴파일 가능하게 함)
    apply(plugin = "org.jetbrains.kotlin.plugin.spring") // kotlin-spring integration plugin (코틀린-스프링 연동 플러그인)
    apply(plugin = "org.springframework.boot")
    apply(plugin = "io.spring.dependency-management")
    
    configure<JavaPluginExtension> {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(21)) // set to jdk 21
        }
    }

    // add dependencies required for each module (각 모듈에서 필요한 디펜던시 추가하면 됨)
    dependencies {
        // implementation (실제 코드에서 사용할 라이브러리)
        add("implementation", "com.fasterxml.jackson.module:jackson-module-kotlin") // kotlin-json conversion support (kotlin-json 변환 지원)
        add("implementation", "org.jetbrains.kotlin:kotlin-reflect") // kotlin reflection support (코틀린 리플랙션 기능 지원)
        add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk8") // kotlin standard for jdk8 (jdk8 기반 코틀린 표준 라이브러리 | 코틀린 코어)

        // test & test container
        add("testImplementation", "org.springframework.boot:spring-boot-starter-test") // support a spring boot test like JUnit, Mockito, AssertJ
        add("testImplementation", "org.springframework.boot:spring-boot-testcontainers") // support a spring-test container conversion
        add("testImplementation", "org.jetbrains.kotlin:kotlin-test-junit5") // kotlin + junit5
        add("testImplementation", "org.testcontainers:junit-jupiter:1.20.3") // testcontainers + junit5
        add("testImplementation", "org.testcontainers:testcontainers:1.20.3") // test library for container-based integration tests
        add("testRuntimeOnly", "org.junit.platform:junit-platform-launcher") // runtime library for executing tests on the Junit platform
    }
    
    configure<org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension> {
        compilerOptions {
            freeCompilerArgs.addAll("-Xjsr305=strict")
            // Treat JSR-305 annotations as strict for null-safety
            // JSR-305 애노테이션을 엄격하게 처리하여 Null 안전성 강화
        }
    }
    
    tasks.withType<Test> {
        useJUnitPlatform()
        // Use JUnit Platform (JUnit5) for running tests
        // 테스트 실행 시 JUnit 플랫폼(JUnit5) 사용
    }
}
