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

# Rotte relative ai Filter
![filter](https://user-images.githubusercontent.com/75033190/104925405-1613ca80-599f-11eb-8283-c5e0d08fdd81.PNG)

Es. /data/filter/Ancona/IT

Se non inserisco informazioni riguardanti il periodo considera di default tutto l'anno 2019

![data_filter](https://user-images.githubusercontent.com/75033311/104925036-90901a80-599e-11eb-94ba-76c73c4e3a33.png)


# Rotte relative alle Stats
![stats](https://user-images.githubusercontent.com/75033190/104923049-cf70a100-599b-11eb-85dc-5251f1f3d5c7.PNG)

1. /stats/monthly/Ancona/IT

Di default restituisce le statistiche mensili dell'anno 2019 di Ancona

![stat_monthly](https://user-images.githubusercontent.com/75033311/104925408-1613ca80-599f-11eb-999f-6063631f3689.png)

2. /stats/spring/2019/Ancona/IT

Restituisce le statistiche della primavera 2019 di Ancona

![esempio stats](https://user-images.githubusercontent.com/75033190/104926577-a272bd00-59a0-11eb-82a1-45fe97b69477.PNG)

# Rotte relative ai Confronti
![confronto](https://user-images.githubusercontent.com/75033190/104923149-f5964100-599b-11eb-8de8-3ad46f12da77.PNG)

1. /confronto/stats/8/2018/Ancona/IT

Restituisce le statistiche del mese di agosto nel 2018 e nell'anno precedente

![conf](https://user-images.githubusercontent.com/75033311/104925967-d0a3cd00-599f-11eb-9dc2-b49b8c8d57db.png)

2. /confronto/stats/summer/2018/Ancona/IT

<b>Restituisce</b> le statistiche in estate nel 2018 e nell'anno precedente

![seas Conf](https://user-images.githubusercontent.com/75033311/104926923-12814300-59a1-11eb-9952-2fd8cae97e9f.png)

# Autori
Il programma è stato sviluppato in modo equo da:
@ RebeccaGiuliani & @ SimoneBruschi
