import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.2.3.RELEASE"
    id("io.spring.dependency-management") version "1.0.8.RELEASE"
    kotlin("jvm") version "1.3.61"
    kotlin("plugin.spring") version "1.3.61"
}

group = "com.swisslog"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework:spring-web:5.2.3.RELEASE")
    implementation("org.springframework.boot:spring-boot-starter-web:2.1.9.RELEASE")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    implementation("io.springfox:springfox-swagger2:2.9.2")
    implementation("io.springfox:springfox-swagger-ui:2.9.2")
    implementation("javax.persistence:javax.persistence-api:2.2")
    implementation("javax.transaction:javax.transaction-api:1.3")
    implementation("javax.servlet:javax.servlet-api:4.0.1")
    implementation("org.liquibase:liquibase-core:3.6.3")
    implementation("org.springframework.data:spring-data-jpa:2.1.9.RELEASE")
    implementation("com.microsoft.sqlserver:mssql-jdbc:6.4.0.jre8")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")

    implementation("org.springframework:spring-webmvc:5.2.3.RELEASE")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.10.1")
    implementation("com.fasterxml.jackson.core:jackson-core:2.10.1")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.10.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jdk8:2.10.1")
    implementation("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:2.10.1")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.10.1")
    implementation("com.fasterxml.jackson.module:jackson-module-parameter-names:2.10.1")
    implementation("org.springframework.kafka:spring-kafka:2.3.4.RELEASE")
}


tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "1.8"
    }
}
