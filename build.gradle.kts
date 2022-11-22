import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.7.10"
    id("io.gatling.gradle") version "3.8.4"
    id("org.openapi.generator") version "4.3.1"
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    gatlingImplementation("org.apache.commons:commons-lang3:3.12.0")
    gatlingImplementation("com.fasterxml.jackson.core:jackson-databind:2.13.4")
    gatlingImplementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.4")
    implementation("com.fasterxml.jackson.core:jackson-annotations:2.13.4")
    testImplementation(kotlin("test"))
}

gatling {
    version = "3.8.4"

}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"

}
