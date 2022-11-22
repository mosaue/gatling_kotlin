Oppgave  
===============================================
Vi skal nå utføre et lite knep, for å slippe å manuelt kopiere modellen fra Swagger-dokumentasjonen.

1. Åpne `build.gradle.kts`
2. Kopier inn følgende:
```
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
```
3. Sett inn riktig baseUrl
4. Åpne terminal-vinduet i IntelliJ
5. Skriv inn: `gradlew build` og kjør kommandoen med `Command + Enter`
6. Se om dere finner noen nyttige klasser under `build/generated/bank`
7. I `CreateUser`, erstatt `User` og `Account` med passende nye klasser
8. I scenarioet må vi lage `NewAccount` med parametre. Den gamle `Account` hadde default verdier. Se hvordan disspe parametrene er satt og legg det inn i scenarioet.
9. Dere kan nå slette hele `models`-folderen
10. Kjør testen på nytt. Den skal fortsatt fungere

Vi har nå spart oss for mye manuelt arbeid ved å slippe å lage klasser fra Swagger-dokumentasjonen.
