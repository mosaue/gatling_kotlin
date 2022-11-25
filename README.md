
## Gatling docs:
https://gatling.io/docs/gatling/reference/current/core/ 


# Gatling Kotlin Kurs - Oppgaveark

####  git
- Klone repository: `git clone https://github.com/mosaue/gatling_kotlin.git`
- sjekke ut ny branch: `git checkout -b branchName origin/branchName`
- Liste ut alle brancher: `git branch -a`

### Kontekst
Logg inn i nettbanken `http://baseUrl:8080/bank` og "se deg litt rundt"
brukernavn/passord: `-jsmith@demo.io/Demo123!` 

### Oppgave 0:
Vi skal nå importere prosjektet til IntelliJ
1. Klon repoet til et sted på maskinen som er lett å finne igjen.
2. Åpne IntelliJ
3. Velg Open, eventuelt steg 4
4. File -> New -> Project From Existing Sources 
5. Velg FILEN `build.gradle.kts` som ligger i mappen `gatling_kotlin`
6. Velg `Open as Project`
7. Det kan ta noe tid å importere prosjektet

### Oppgave 1:
Sjekk ut branch `oppgave/1`

1. Fyll inn de resterende feltene i dataklassen `User` som ligger under `src/gatling/kotlin/models/Oppgave1.kt`
	- Felter som skal være med finner dere på `http://baseUrl:8080/bank/swagger-ui.html#/`. Se under Models, newUser
3. Lag en sekvensgenerator som lager nye `User`-objekter. Det skal genereres unike `ssn`, `emailAddress` og `password` for hver `User` som opprettes, med følgende krav:
- Format `emailAddress`: `*@*.com` `*`-Hva som helst, eks: `abcd@foo.com`
- Format `ssn`: `3 tall, bindestrek, 2 tall, bindestrek, 4 tall`, eks: `111-11-1111`
- `password` må inneholde stor bokstav, liten bokstav, tall og bindestrek. Det må være minst 16 tegn langt.
- Format på telefonnummer må være: `(111) 111-1111` (Disse trenger ikke å være unike)
#### Tips:
```
Tilfeldige tall: Random().nextInt(fra,til) 

Tilfeldig bokstaver: 
RandomStringUtils.randomAlphanumeric(antall bokstaver)

Gjøre en String om til kun sore bokstaver: String.uppercase()

Gjøre en String om til kun små bokstaver: String.lowercase()

bruk `${din kode her}` for å bruke kode direkte i en String

Strings kan kombineres med +, eks "a" + "B" gir "aB"
```
3. Print ut ordet: `Hello, my email is: `. Med eposten fra generert bruker, 10 ganger.
#### Tips:
```
feeder.next()["user"]!!
bruk `$variabelnavn` for å referere til en variabel i en string
```
4. Skriv `emailAddress` og `password` til en fil, på 10 linjer
#### Tips:
```
\n => newline
```
5. Bruk ObjectMapper til å printe ut all brukerinformasjon om hver bruker
#### Tips:
```
objectMapper.writeValueAsString()
```
### Oppgave 2
Sjekk ut branch `oppgave/2-fasit-1`
####  Scenario

Etter disse oppgavene er fullført skal vi ha ferdig et scenario som gjør følgende:

1. Autentiserer seg som admin-bruker
2. Legger til en ny bruker i banken
3. Autentiserer seg som denne nye brukeren
4. Oppretter en konto i banken

Disse oppgavene ligger under mappen `simulations`

####  Del 1:

1. Åpne `CreateUserDel1` og fyll inn `baseUrl`
2. Legg til navn på scenario
3. Legg til en http-request, med POST-metode og de standard-headerne (`genericHeader`)
1. Request-URL er: `/api/v1/auth?password=Demo123!&username=admin@demo.io`
4. Kjør scenarioet ved hjelp av `Engine`-klassen
5. Legg deretter på en `check` for å lagre autentiseringstoken
#### Tips:
```
.check(jsonPath("$.authToken").saveAs("authToken")
```
####  Del 2:

1. Åpne `CreateUserDel2`
2. Legg inn `feeder` som vi allerede har lagd i tidligere oppgave
3. Kopier også inn `objectMapper` fra tidligere oppgave
4. Legg til Authorization-header i "Post new user"
5. Legg til en print av `returnBody` for debugging
#### Tips:
```
session.getString("returnBody")
```
6. Lagre eposten og passordet til brukeren i en fil.
7. Kjør scenarioet et par ganger og sjekk at det blir lagt til en bruker i testData-filen for hver gang.

####  Del 3:

1. Åpne `CreateUserDel3`
2. Legg til autoriseringskall for brukeren som er lagt til
#### Tips:
```
Se på autoriseringen for admin-brukeren. Det er ganske likt. 
Det eneste vi må endre er brukernavn og passord.
Husk, vi har allerede lagret all brukerinformasjon i session.
```
2. Under `models` er det en ny dataklasse som heter Accounts. Fullfør innlegging av ny konto.
	- Endepunkt: `/api/v1/user/account`
3. Når du kjører testen vil den feile. Er det mulig å finne ut hvorfor ved hjelp av loggen?
4. En ny rolle må legges til på brukeren, før vi oppretter Account.  
   Legg til ny rolle med følgende kall:  
  ```
  .exec(  
		 http("Post new role").put("/api/v1/user/\${userId}/rolerole=API")
		 .headers(genericHeader) 
		 .header("Authorization", "Bearer \${authToken}") 
		 )
  ```
 4. `userId` må vi hente fra JSON-responsen i "Post new user"-kallet

### Oppgave 3
Sjekk ut branch `oppgave/3-fasit-2`

Vi skal nå utføre et lite knep, for å slippe å manuelt kopiere modellen fra Swagger-dokumentasjonen.

1. Åpne `build.gradle.kts`
2. Kopier inn følgende:
```
Se README.md
```
3. Sett inn riktig baseUrl
4. Åpne terminal-vinduet i IntelliJ
5. Skriv inn: `gradlew build` og kjør kommandoen med `Command + Enter`
6. Se om dere finner noen nyttige klasser under `build/generated/bank`
7. I `CreateUser`, erstatt `User` og `Account` med passende nye klasser
8. I scenarioet må vi lage `NewAccount` med parametre. Den gamle `Account` hadde default verdier. Se hvordan disspe parametrene er satt og legg det inn i scenarioet.
9. Dere kan nå slette hele `models`-folderen
10. Kjør testen på nytt. Den skal fortsatt fungere

Vi har nå spart oss for mye manuelt arbeid ved å slippe å manuelt lage klasser fra Swagger-dokumentasjonen.

### Oppgave 4
Sjekk ut branch `oppgave/4-fasit-3`

Vi skal nå teste den samme applikasjonen. Men vi skal bruke web-applikasjonen i stedet for APIet.

1. På Mac; åpne Systeminnstillinger -> wifi -> detaljer -> Proxy
2. Sett Tjener til localhost og Port til 8000
3. Start recorder
4. Sett inn listening port til 8000
5. Sett Package til simulations
6. Sett Class Name til Login
7. Trykk Start
8. Gå inn på `http://baseUrl:8080/bank`
9. Bruk brukernavn/passord: jsmith@demo.io/Demo123!
10. Gå tilbake til recorderen
11. Trykk Stop & Save
12. Avslutt recorderen
13. Se i simulations-folderen. Vi har en ny Simulation.
14. Hva tenker dere i gruppen om alle requestene som blir sendt her? Hvis dere skulle ytelsestestet denne applikasjonen, ville dere beholdt alle sammen?

### Oppgave 5
Sjekk ut branch `oppgave/5`

1. Nå som vi har ryddet litt opp i testen vår så kan vi i teorien bruke den til å kjøre ytelsestest.
MEN, er det noe vi burde endre?

Diskuter litt i gruppen. Når dere er enige, åpne README.md i docs-folderen, eller se siste side i dette dokumentet.

### Oppgave 6
Sjekk ut branch `oppgave/6-fasit-5`

Lag lastprofiler for
- Load Test
- Volume Test
- Stress Test

Vi kjører kun 5 minutter lange tester.

Forventet last i produksjon er:
- Jevnt sigende, fra 1 til 30 brukere, per minutt, første 3 minutter
- 40 brukere per minutt det 4. minuttet
- Jevnt synkende antall brukere fra 40 til 0, per minutt, siste minuttet

### Oppgave

Velg enten API eller web. Hvis du blir ferdig med den ene kan du ta den andre også.

####  Utvid eksisterende scenario:

1. Opprett checking account
2. Ta ut penger fra denne kontoen
3. Opprett savings account
4. Sett penger inn på denne kontoen
5. Overfør penger fra den nye savings til den nye checking

#####  For API:

accountTypeCode: SCK - Standard Checking, ICK - Interest Checking, SAV - Savings

ownerTypeCode: IND - Individual, JNT - Joint

transactionTypeCode: WTH - Withdrawal, DPT - Deposit

### Oppgave 5, Del 2

Vi ønsker ikke å bruke samme bruker til hver eneste test. Vi vil bruke de vi har lagret tidligere!

1. Se på testData-filen og les om filbaserte feeders her: https://gatling.io/docs/gatling/reference/current/core/session/feeder/#file-based-feeders
2. Implementer feederen.
3. Mat feeder-dataen inn i scenarioet og bruk unike kontoer for login.
#### Tips:
For å bruke sesjonsvariabler direkte i en String kan vi bruke `\$` foran navnet på variablen.
