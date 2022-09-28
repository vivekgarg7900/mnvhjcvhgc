plugins {
    id("org.jetbrains.kotlin.jvm") version "1.6.20"
    id("org.jetbrains.kotlin.kapt") version "1.6.20"
    id("org.jetbrains.kotlin.plugin.allopen") version "1.6.10"
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.micronaut.application") version "3.3.2"
}

version = "0.1"
group = "com.cogoport"

val kotlinVersion=project.properties.get("kotlinVersion")
repositories {
    mavenCentral()
}

dependencies {
    kapt("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut.kotlin:micronaut-kotlin-runtime")
    implementation("io.micronaut.sql:micronaut-jdbc-hikari")
    implementation("jakarta.annotation:jakarta.annotation-api")
    implementation("org.jetbrains.kotlin:kotlin-reflect:${kotlinVersion}")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${kotlinVersion}")
    runtimeOnly("io.r2dbc:r2dbc-h2")
    runtimeOnly("org.slf4j:slf4j-simple")
    implementation("io.micronaut:micronaut-validation")

    implementation("io.r2dbc:r2dbc-postgresql:0.8.12.RELEASE")
    runtimeOnly("org.postgresql:postgresql:42.3.6")

    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin")

}


application {
    mainClass.set("com.cogoport.ApplicationKt")
}
java {
    sourceCompatibility = JavaVersion.toVersion("17")
}

tasks {
    compileKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
    compileTestKotlin {
        kotlinOptions {
            jvmTarget = "17"
        }
    }
}
graalvmNative.toolchainDetection.set(false)
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.cogoport.*")
    }
}


