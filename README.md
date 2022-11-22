Oppgave  
===============================================
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
14. Hva tenker dere i gruppen om alle requestene som blir sendt her? Hvis dere skulle ytelsestestet denne applikasjonen,
ville dere beholdt alle sammen?
