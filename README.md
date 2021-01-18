# OOP_Project_Bruschi_Giuliani
# Introduzione
Il programma permette all'utente di visualizzare lo storico e le statistiche dei valori UV relativamente ad una determinata città e un determinato periodo. 
Richiede in ingresso la città d'interesse e il relativo stato (es. Ancona, IT) e il periodo che può essere generico oppure una stagione o un anno specifico.
Una volta ottenuti i dati tramite una chiamata API l'utente può richiedere il calcolo delle statistiche del periodo inserito (media, varianza, max e min) decidendo di filtrarli mensilmente o in maniera stagionale. Inoltre l'utente può anche richiedere di confrontare le statistiche di uno specifico mese o di una specifica stagione prese in due anni consecutivi.



# Diagrammi UML
UseCase Diagram
![Utente](https://user-images.githubusercontent.com/75033311/103019597-671ed300-4547-11eb-9552-b974b4baa5c6.jpg)

Class Diagram
![ClassDiagram](https://user-images.githubusercontent.com/75033190/103371837-be392080-4ad0-11eb-9d22-f75168f13fea.jpg)

Sequence Diagram
![Sequence](https://user-images.githubusercontent.com/75033311/103019791-a9481480-4547-11eb-9bf6-42e6c377d318.jpg)

# Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere effettuate all'indirizzo
localhost:8080

Rotte relative ai Filter
![filter](https://user-images.githubusercontent.com/75033190/104923399-4c037f80-599c-11eb-8aea-cdd67155b4fe.PNG)

Rotte relative alle Stats
![stats](https://user-images.githubusercontent.com/75033190/104923049-cf70a100-599b-11eb-85dc-5251f1f3d5c7.PNG)

Rotte relative ai Confronti
![confronto](https://user-images.githubusercontent.com/75033190/104923149-f5964100-599b-11eb-8de8-3ad46f12da77.PNG)
