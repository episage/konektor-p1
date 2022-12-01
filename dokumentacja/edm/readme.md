## Jak tego uzywac?

Najlepiej pobrac sobie na dysk `git clone ..` i mozna czytac PDFy.

## Wymagania MacOS

```
brew install unar # latwe rozpakowywanie
```
```
brew install findutils # xargs ktory nie zjada spacji, trzeba dodac do PATH
```

## Odpakowanie ZIPów

Nazwy plików w ZIPach są zakodowane w CP852. Prawdopodobnie dlatego, ze urzednicy-informatycy w Centrum e-zdrowia pracuja na archaicznych systemach.
Żeby odpakować z poprawną nazwą z polskimi znakami:

```
find edm -type f -name "*.zip" | xargs -t -I{} sh -c "unar -o edm -e CP852 '{}'"
```
```
find edm/P1-DS-Dokumentacja_integracyjna_P1_EDM_20220915_W16 -type f -name "*.zip" | xargs -t -I{} sh -c "unar -o edm/P1-DS-Dokumentacja_integracyjna_P1_EDM_20220915_W16 -e CP852 '{}'"
```

Wersja 15.3 (W15_3) została pominieta w procesie rozpakowywania, poniewaz W16 jest nowsza.

## XLSX na PDF

Zostały zamienione w systemie Windows 11 za pomocą pakietu Office 2021.
PDFy laduja sie znacznie szybciej, umowliwiaja latwe wyszukiwanie i nie trzeba instalowac MS Word.

## (2)

`Projekt-testow-dla-integratorów-EDM-v15.3 (2)` musi byc przemianowany na bez `(2)` bo SoapUI rzuca blad:
```
sh: -c: line 0: syntax error near unexpected token '('
```

## Wersje

Dane w tym folderze pobrane dnia 2022.11.20.

## Adresy

```
WERSJE SYSTEMU P1 W ZAKRESIE EDM ZAINSTALOWANA NA ŚRODOWISKU: SGZ-EDM: 15.3.1, SZAR: 15.2.0, 
SOZ: 15.2, SWD: 18.0.1
 

ADRESY USŁUG SYSTEMU P1 W ZAKRESIE EDM
Podstawowe operacje rejestru P1:

Zapis indeksu EDM (operacja zgodna z IHE XDS.b ITI-42):

https://isus.ezdrowie.gov.pl/services/ObslugaEdmIti42WS

Wyszukanie indeksu EDM (operacja zgodna z IHE XDS.b IT-18):

https://isus.ezdrowie.gov.pl/services/ObslugaEdmIti18WS

Aktualizacja indeksu (operacja zgodna z IHE XDS.b ITI-57):

https://isus.ezdrowie.gov.pl/services/ObslugaEdmIti57WS

Przyjmowanie logów z zewnętrznych repozytoriów XDS.b 
(operacja zgodna z IHE XDS.b ITI-20):

isus.ezdrowie.gov.pl port: 6514 (wymaga nawiązania szyfrowanego połączenia TLS z dwustronnym uwierzytelnianiem)

Operacje wspierające:

Generowanie tokenu:

https://isus.ezdrowie.gov.pl/services/ObslugaGenerowanieTokenuSamlWS

Rejestrowanie danych dostępowych do Repozytorium XDS.b:

https://isus.ezdrowie.gov.pl/services/ObslugaRejestrowanieDanychDostepowychWS

Pobranie danych dostępowych do Repozytorium XDS.b:

https://isus.ezdrowie.gov.pl/services/ObslugaPobranieDanychDostepowychWS

Operacje repozytorium udostępnione wyłącznie na środowisku INT:

Repozytorum EDM śr. INT (SGD) - adres dla usługi:

https://isgd.ezdrowie.gov.pl/

Przekazanie i zaindeksowanie EDM (operacja zgodna z IHE XDS.b ITI-41):

https://isgd.ezdrowie.gov.pl/services/xds-iti41

Pobranie EDM (operacja zgodna z IHE XDS.b ITI-43):

https://isgd.ezdrowie.gov.pl/services/xds-iti43

Usługa SOZ umożliwiająca potwierdzenie uprawnień na udostępnienie dokumentacji medycznej:

https://isus.ezdrowie.gov.pl/services/ObslugaWeryfikacjiDostepuDoDanychWS
```
