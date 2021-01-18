# OOP_Project_Bruschi_Giuliani
# Introduzione
Il programma permette all'utente di visualizzare lo storico e le statistiche dei valori UV relativamente ad una determinata città e un determinato periodo. 
Richiede in ingresso la città d'interesse e il relativo stato (es. Ancona, IT) e il periodo che può essere generico oppure una stagione o un anno specifico.
Una volta ottenuti i dati tramite una chiamata API l'utente può richiedere il calcolo delle statistiche del periodo inserito (media, varianza, max e min) decidendo di filtrarli mensilmente o in maniera stagionale. Inoltre l'utente può anche richiedere di confrontare le statistiche di uno specifico mese o di una specifica stagione prese in due anni consecutivi.



# Diagrammi UML
UseCase Diagram
![Utente](https://user-images.githubusercontent.com/75033311/104932809-62afd380-59a8-11eb-8d9d-7126ff6a427f.jpg)

![Utente](https://user-images.githubusercontent.com/75033311/103019597-671ed300-4547-11eb-9552-b974b4baa5c6.jpg)

Class Diagram
![ClassDiagram](https://user-images.githubusercontent.com/75033190/103371837-be392080-4ad0-11eb-9d22-f75168f13fea.jpg)

Sequence Diagram
![Sequence](https://user-images.githubusercontent.com/75033311/103019791-a9481480-4547-11eb-9bf6-42e6c377d318.jpg)

# Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere effettuate all'indirizzo
localhost:8080.

Nel caso in cui l'utente voglia analizzare un periodo generico, diverso da quello di default, dovrà inserire come parametri il giorno, il mese e l'anno d'inizio e di fine dello stesso.

<i>Esempio</i>

![Cattura](https://user-images.githubusercontent.com/75033190/104933833-ace58480-59a9-11eb-83b5-58b163ab965c.PNG)

# Rotte relative ai Filter
![filter](https://user-images.githubusercontent.com/75033190/104932105-90484d00-59a7-11eb-8b92-cede2bcdaf11.PNG)

<b>Es.</b> /data/filter/Ancona/IT

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

Restituisce le statistiche in estate nel 2018 e nell'anno precedente

![seas Conf](https://user-images.githubusercontent.com/75033311/104926923-12814300-59a1-11eb-9952-2fd8cae97e9f.png)

# Gestione Eccezioni
Abbiamo gestito le eccezioni creando due nuove classi: <b>WrongPeriodException</b> e <b>WrongCityException</b>.

La prima eccezione viene generate quando l'utente inserisce dei parametri riguardanti il tempo in maniera errata (se il <i>giorno</i> è =0 o >31, se il mese è =0 o >12, o se l'anno è < 2017).

<b><i>ATTENZIONE!</i></b> Non sono registrati dati per periodi precedenti al 22.6.2017, quindi qualsiasi periodo precedente sarà considerato non valido e quindi il programma genererà l'eccezione <b>WrongPeriodException</b> e stamperà a video <i>"ERR: Il periodo inserito non è valido!"</i>.

</b> <i>Esempio:</i>

In questo caso nella data di fine il giorno inserito è 32, chiaramente è un valore non accettabile

![err](https://user-images.githubusercontent.com/75033311/104929217-e3200580-59a3-11eb-9a48-5ce94f548934.png)

La seconda eccezione viene generata quando l'utente inserisce una città non presente nell file di riferimento(tale file è presente nella repository) o scrive in maniera errata il nome della città o la sigla dello stato. 

<b><i>ATTENZIONE!</i></b> I nomi delle città devono essere scritti in inglese. Ad esempio, se si inserisce "Roma" il programma genererà l'eccezione <b>WrongCityException</b> e stamperà a video <i>"ERR: La città o lo stato inseriti non sono validi!"</i>.

<i>Esempi:</i> 
1. In questo caso Ancona è stata scritta in modo errato 

![eccezioni](https://user-images.githubusercontent.com/75033190/104930514-89203f80-59a5-11eb-8ce2-97f9618216b1.PNG)

2. In questo caso la sigla dello stato italiano è stata scritta in modo errato 

![eccezioni2](https://user-images.githubusercontent.com/75033190/104930553-94736b00-59a5-11eb-9dea-b5f98eeb791c.PNG)

# Test
Abbiamo implementato quattro unità di test: 
1. <b>APICallTest</b> per testare la classe <i>APICall</i>, che gestisce le chiamate API e restituisce il JSONArray con i dati della città e del periodo introdotti
2. <b>CityFileReaderTest</b> per testare la classe <i>CityFileReader</i>, che gestisce il file delle città e restituisce latitudine e longitudine della cottà inserita 
3. <b>StatsTest</b> per testare la classe <i>StatsTest</i>, che definisce le statistiche mensili e stagionali predendo i dati dal JSONArray restituito dalla APICall
4. <b>ConfrontoTest</b> per testare la classe <i>Confronto</i>, che restituisce un JSONArray composto da due JSONObject le statistiche prese in due anni consecutivi di un medesimo periodo

# Javadoc
Qui sotto è riportato l'indirizzo html per visualizzare la Javadoc del programma
file:///C:/Users/simob/git/OOP_Project_Bruschi_Giuliani/OOP_Project_Bruschi_Giuliani/progettoOOP/doc/index.html
file:///C:/Users/Utente/Documents/GitHub/OOP_Project_Bruschi_Giuliani/OOP_Project_Bruschi_Giuliani/progettoOOP/doc/index.html

# Autori
Il programma è stato sviluppato in modo equo da:

@ RebeccaGiuliani & @ SimoneBruschi
