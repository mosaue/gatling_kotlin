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

val spec = "http://192.168.1.93:8080/bank/v2/api-docs"
val generatedSources = "$buildDir/generated/bank"

sourceSets {
    getByName("main") {
        java {
            srcDir("$generatedSources/src/main/kotlin")
        }
    }
}

tasks{
    openApiGenerate{
        generatorName.set("kotlin")
        inputSpec.set(spec)
        outputDir.set(generatedSources)

        skipValidateSpec.set(true)

        modelPackage.set("com.example.gatling.api")
        modelPackage.set("com.example.gatling.model")

        systemProperties.set(
            mapOf(
                "models" to "", // Only generate models (not the api and supporting files)
                "modelDocs" to "false"
            )
        )

        configOptions.set(
            mapOf(
                "dateLibrary" to "java8",
                "enumPropertyNaming" to "PascalCase",
                "serializationLibrary" to "jackson"
            )
        )

        typeMappings.set(
            mapOf(
                "ByteArray" to "kotlin.String",
                "java.time.OffsetDateTime" to "kotlin.String"
            )
        )
    }

    compileKotlin {
        dependsOn(openApiGenerate)
    }
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"

}