plugins {
    id("org.jetbrains.kotlin.jvm") version "1.8.22"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.8.22"
    id("com.google.devtools.ksp") version "1.8.22-1.0.11"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    id("io.micronaut.application") version "4.0.2"
    id("io.micronaut.test-resources") version "4.0.2"
}

version = "0.1"
group = "example.micronaut"

val kotlinVersion=project.properties.get("kotlinVersion")
val testcontainersVersion="1.17.2"
repositories {
    mavenCentral()
}

dependencies {
    ksp("io.micronaut.validation:micronaut-validation-processor")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.rabbitmq:micronaut-rabbitmq")
    implementation("io.micronaut.validation:micronaut-validation")
    implementation("jakarta.validation:jakarta.validation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("ch.qos.logback:logback-classic")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")
    runtimeOnly("org.yaml:snakeyaml")

    testImplementation("io.micronaut.test:micronaut-test-junit5")

    testImplementation(platform("org.testcontainers:testcontainers-bom:$testcontainersVersion"))
    testImplementation("org.testcontainers:rabbitmq:$testcontainersVersion")
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
}


application {
    mainClass.set("example.micronaut.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
    compileTestKotlin {
        compilerOptions {
            jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("example.micronaut.*")
    }
}



