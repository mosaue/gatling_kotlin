Oppgaver:
===============================================

1. Fyll inn de resterende feltene i dataklassen `User` som ligger under `src/gatling/kotlin/models/Oppgave1.kt`
   1. Felter som skal være med er finner dere på 
2. Lag en sekvensgenerator som lager nye `Users`. Det skal genereres unike `ssn`, `emailAddress` og `password` for hver `User` som opprettes
   - Tips: 
   ```
     import org.apache.commons.lang3.RandomStringUtils
   
     val feeder = generateSequence {
     val email = RandomStringUtils.randomAlphanumeric(20) + "@foo.com"
     mapOf("email" to email)
     }.iterator()
   ```
3. Print ut ordet: `Hello, my email is: `. Med eposten fra generert bruker, 10 ganger.
   - Tips: 
   ```
   feeder.next()["user"]
   ```
   - Tips:
   ```
   bruk `$` for å referere til en variabel i en string
   ```
4. Skriv `emailAddress` og `password` til en fil, på 10 linjer
   - Tips:
   ```
   \n => newline
   ```
5. Bruk ObjectMapper til å printe ut all brukerinformasjon om hver bruker
   - Tips:
   ```
   objectMapper.writeValueAsString()
   ```
   
