# Spring: Difference between Spring MVC and Spring Boot

Autori:

- Michal Rendek
- Simona Richterová
- Kristián Sokol
- Barbora Ulrichová
- Edita Včelková


## Prílohy

[Dokumentácia](attachments/asos.pdf)

[Prezentácia](attachments/prezentacia.pdf)

## Otázka ku skúške 

V návrhovom vzore MVC figurujú 3 vrstvy. Model, view a controller. Čo zabezpečuje vrstva controller?

- [ ] Vykresľuje údaje modelu a tiež generuje HTML výstup.
- [x] Prepája komponenty, spracováva požiadavky používateľov a odovzdáva ich do view na vykreslenie.
- [ ] Zapuzdruje dáta aplikácie a komunikuje s databázovým systémom.
- [ ] Stará sa o reprezentáciu a manipuláciu s dátami v aplikácií. 

## Popis projektu

Na ukážku sme si pripravili jednoduchý softvér, ktorý nesie názov Insurance system. Úlohou tohto softvéru je ukladať údaje o ľuďoch, ktorý si uzavreli nejaké poistenie a taktiež aj ukladanie rôznych typov poistných zmlúv. Systém umožňuje ukladať nové dáta, modifikovať dáta, mazať dáta a zobrazovať všetky potrebné dáta.

Softvér sme vyhotovili v dvoch verziách. Pri jednej verzií sme vytvorili softvér s použitím Spring MVC. V druhej verzií sme pripravili ten istý softvér s použitím Spring Boot, ktorý poskytuje REST API pre všetky funkcionality.

## Class diagram

![Class diagram](attachments/class.png?raw=true "Class diagram")

## Use case diagram

![Use case diagram](attachments/use-case.png?raw=true "Use case diagram")

## Screenshot aplikácie

![Screenshot aplikácie](attachments/screenshot.png?raw=true "Screenshot aplikácie")
