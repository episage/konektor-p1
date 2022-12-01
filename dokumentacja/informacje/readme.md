## Data aktualizacji
2022.12.01

```
Konfiguracja środowiskowa INT

Reguła ZM REG.WER.7164 Ograniczenie ilości wersji dla zasobu Patient - na śr. INT jest ustawiona w trybie ostrzeżenia (na śr. PRD w trybie błędu).
Reguły 4436 i 4448 (max. termin realizacji DDR i kDRR wg. typu leku) na śr. INT są włączone w trybie ostrzeżenia, na śr. PRD są wyłączone.
Generowanie kodów PIN: tylko same 0000 (więcej informacji w komunikacie z 10.10.2019)
Asystenci medyczni - obsługa komunikacji: należy skorzystać z certyfikatu dla asystenta znajdującego się w paczce ZIP testów SOAP UI dla e-recepty, w podfolderze "Keys" 
oraz zapoznać się z informacjami w pliku Asystent_readme.txt
Na śr. INT jest włączony two-way SSL, na śr. PRD two-way SSL jest czasowo wyłączony.
Komunikacja asynchroniczna:

Śr. PRD – wyłączona | Śr. INT – włączona z konfiguracją nw. parametrów:

[IdZadania] = 4 pozycje. (minimalna liczba recept w pakiecie wymuszająca przetwarzanie asynchroniczne).
[TZ] = 120 sekund. (timeout asynchronicznego zapisu pakietu recept).
[TO] = 600 sekund. (maksymalny czas oczekiwania na odbiór zapisanego asynchronicznie pakietu recept).

Dla integratora

Informacje oraz Aktualności dla integratorów o systemie e-zdrowie dostępne są na portalu informacyjnym ezdrowie.gov.pl w sekcji "Dla dostawców".

Dostępne wersje PIK

PIK HL7 CDA 1.3.1.2
PIK HL7 CDA 1.3.1.3
PIK HL7 CDA 1.3.2

Aktualny REJESTR OID:
P1-Drzewo_OID_0.5.70_20220718.xlsx

Change log do wydań PIK znajduje się na WWW PIK w zakładce "Informacje o projekcie". Szczegółowy wykaz zmian publikowany jest w pliku dokumentacji integracyjnej dla e-recepty:PIK P1-DS-Dokumentacja_integracyjna_P1-eRecepta_20200522\P1-DS-Z8-HL7_CDA_PL_1.3.1.zip\P1-DS-Z8-HL7_CDA_PL_1.3.1\plcda-1.3.1-u2-20200427

FAQ dla dostawców oprogramowania

Wytyczne i rekomendacje CeZ w zakresie wymiany EDM związane ze sposobami przechowywania oraz udostępniania dokumentacji medycznej pacjentów

Minimalne wymagania techniczne i funkcjonalne dla systemów Usługodawców v2.0

Rekomendacje CSIOZ w zakresie bezpieczeństwa oraz rozwiązań technologicznych podczas przetwarzania dokumentacji medycznej w postaci elektronicznej.

Załączniki do rekomendacji
Walidacja bloku narracyjnego dokumentu e-recepty


Dla podmiotów leczniczych i aptecznych

Poradniki dla podmiotów wykonujących działalność leczniczą oraz podmiotów aptecznych udostępniane są na serwisie YouTube na kanale CSIOZ.

Więcej informacji znajduje się na portalu e-zdrowie.

FAQ e-recepta: pracownicy medyczni i aptekarze

FAQ e-skierowanie pracownicy medyczni

Akademia CeZ: ZM i EDM - najczęściej zadawane pytania

Akademia CeZ: FAQ biznesowy ze szkolenia EDM dla KIDL

Wytyczne i rekomendacje CeZ w zakresie wymiany EDM związane ze sposobami przechowywania oraz udostępniania dokumentacji medycznej pacjentów

Wystawienie i realizacja e-recept w razie braku dostępu do systemu e-zdrowie - instrukcja

Proces certyfikacji Podmiotu leczniczego/aptecznego w systemie P1


Integracyjne IKP (iIKP)

Informacje i zasady korzystania z iIKP

```