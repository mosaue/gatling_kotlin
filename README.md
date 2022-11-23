## Scenario
Etter disse oppgavene er fullført skal vi ha ferdig et scenario som gjør følgende:
1. Autentiserer seg som admin-bruker
2. Legger til en ny bruker i banken
3. Autentiserer seg som denne nye brukeren
4. Oppretter en konto i banken

## Oppgaver, Del 1:
1. Åpne `CreateUserDel1` og fyll inn `baseUrl`
2. Legg til navn på scenario
3. Legg til en http-request, med POST-metode og de standard-headerne (`genericHeader`)
   1. Request-URL er: `/api/v1/auth?password=Demo123!&username=admin@demo.io`
4. Kjør scenarioet ved hjelp av `Engine`-klassen
5. Legg deretter på en `check` for å lagre autentiseringstoken
    - Tips:
   ```
   .check(jsonPath("$.authToken").saveAs("authToken")
   ```

## Oppgaver, Del 2:
1. Åpne `CreateUserDel2`
2. Legg inn `feeder` som vi allerede har lagd i tidligere oppgave
3. Kopier også inn `objectMapper` fra tidligere oppgave
4. Legg til Authorization-header i "Post new user"
5. Legg til en print av `returnBody` for debugging
   - Tips:
   ```
   session.getString("returnBody")
   ```
6. Lagre eposten og passordet til brukeren i en fil.
7. Kjør scenarioet et par ganger og sjekk at det blir lagt til en bruker i testData-filen for hver gang.

## Oppgaver, Del 3:
1. Legg til autoriseringskall for brukeren som er lagt til
   - Tips: 
   ```
   Se på autoriseringen for admin-brukeren. Det er ganske likt. Det eneste vi må endre er brukernavn og passord.
   Husk, vi har allerede lagret all brukerinformasjon i session.
   ```
2. Under `models` er det en ny dataklasse som heter Accounts. Fullfør innlegging av ny konto. 
   1. Endepunkt: `/api/v1/user/account`
3. Når du kjører testen vil den feile. En ny rolle må legges til på brukeren, før vi oppretter Account.
   Legg til ny rolle med følgende kall:
   ```
   .exec(
            http("Post new role").put("/api/v1/user/\${userId}/role?role=API").headers(genericHeader)
                .header("Authorization", "Bearer \${authToken}")
        )
   ```
