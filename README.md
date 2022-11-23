Oppgaver:
===============================================

1. Fyll inn de resterende feltene i dataklassen `User` som ligger under `src/gatling/kotlin/models/Oppgave1.kt`
   1. Felter som skal være med er finner dere på `baseUrl/bank/swagger-ui.html#/`. Se under Models, newUser
2. Lag en sekvensgenerator som lager nye `User`-objekter. Det skal genereres unike `ssn`, `emailAddress` og `password` for hver `User` som opprettes, med følgende krav:
   - Format `emailAddress`: `*@*.com` `*`-Hva som helst, eks: `abcd@foo.com`
   - Format `ssn`: `3 tall, bindestrek, 2 tall, bindestrek, 4 tall`, eks: `111-11-1111`
   - `password` må inneholde stor bokstav, liten bokstav, tall og bindestrek. Det må være minst 16 tegn langt.
   - Format på telefonnummer må være: `(111) 111-1111` (Disse trenger ikke å være unike)
   - Tips: 
   ```
   Random().nextInt(fra,til)
   RandomStringUtils.randomAlphanumeric(antall bokstaver)  
   String.uppercase()
   String.lowercase() 
   ```
3. Print ut ordet: `Hello, my email is: `. Med eposten fra generert bruker, 10 ganger.
   - Tips: 
   ```
   feeder.next()["user"]!!
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
   
