# OOP_Project_Bruschi_Giuliani
# Introduzione
Il programma permette all'utente di visualizzare lo storico e le statistiche dei valori UV relativamente ad una determinata città  e un determinato periodo. 
Richiede in ingresso la città  d'interesse e il relativo stato (es. Ancona, IT) e il periodo che può essere generico oppure una stagione o un anno specifico.
Una volta ottenuti i dati tramite una chiamata API l'utente può richiedere il calcolo delle statistiche del periodo inserito (media, varianza, max e min) decidendo di filtrarli mensilmente o in maniera stagionale. Inoltre l'utente può anche richiedere di confrontare le statistiche di uno specifico mese o di una specifica stagione prese in due anni consecutivi.



# Diagrammi UML
<b>UseCase Diagram</b>
![Utente](https://user-images.githubusercontent.com/75033311/104932809-62afd380-59a8-11eb-8d9d-7126ff6a427f.jpg)

<b>Sequence Diagram</b>
![Sequence](https://user-images.githubusercontent.com/75033311/103019791-a9481480-4547-11eb-9bf6-42e6c377d318.jpg)

Nei diagrammi riportati qui sopra (dei casi d'uso e delle sequenze) l'attore <i>Utente</i> rappresenta appunto un utente che si interfaccia al programma per ricercare i dati storici dei raggi UV in un periodo e una città di interesse, ha inoltre la possibilità di leggere le statistiche relative ai dati scegliendo la modalità di filtraggio (mensile o stagionale).
<br>L'attore non umano <i>Amministratore</i> si occupa della gestione del programma ovvero, date delle richieste effettuate dall'utente, opererà di conseguenza. Richiesto lo storico dei dati UV, farà una chiamata all'API di OpenWeather con i parametri inseriti dall'utente. L'amministratore si occuperà inoltre anche del calcolo delle statistiche e della generazione di un confronto qualora fosse richiesta.

<b>Class Diagram</b>
![ClassDiagram](https://user-images.githubusercontent.com/75033190/103371837-be392080-4ad0-11eb-9d22-f75168f13fea.jpg)

Il diagramma potrebbe non essere del tutto fedele al progetto finale in quanto nel corso dello sviluppo del programma l'idea iniziele è stata rielaborata.
<br>Ad esempio, nel diagramma delle classi non è presente il package <i><b>exception</b></i> che contiene le classi <i>WrongCityException</i> e <i>WrongPeriodException</i>, adibite alla gestione delle eccezioni dovute all'inserimento errato delle richieste (periodo e/o città).

Il package <i><b>model</b></i> contiene le classi che definiscono gli oggetti utilizzati. 
Diversamente da come riportato nel diagramma, la classe <i>UV</i> non è presente e sono state aggiunte le classi <i>Date</i> e <i>Year</i>.

Il package <i><b>filter</b></i> contiene le classi <i>CityFileReader</i> e <i>APICall</i> che implementano le relative interfacce.
Le classi si occupano rispettivamente di leggere il file contenente le città (<i>city.list.json</i>) in modo da poter trovare la latitudine e la longitudine della città e di fare una chiamata API per ottenere i valori UV, filtrando i dati con il periodo e i parametri trovati dalla classe precedente.

Il package <i><b>stats</b></i> contiene le classi <i>Stats</i>, <i>SeasonStats</i> e <i>Confronto</i> con le relative interfacce ed alcune sottoclassi che le estendono.
<br>La prima si occupa di calcolare le statistiche (media, varianza, massimo e minimo) filtrando il periodo per stagioni o mensilmente.
<br>La seconda ha lo stesso scopo della precedente ma gestisce i valori delle singole stagioni (non accetta un periodo generico in ingresso).
<br>La terza permette di stampare a video e quindi confrontare le statistiche di un mese/una stagione prese in due anni consecutivi.

Il package <i><b>controller</b></i> contiene le classi <i>APICallController</i>, <i>StatsController</i> e <i>ConfrontoController</i> che si occupano di gestire le rispettive richieste GET e POST per le classi sopracitate.

Il package <i><b>test</b></i> ha il compito di effettuare dei test sul corretto funzionamento delle classi principali.


# Rotte
Le richieste che l'utente può effettuare tramite Postman devono essere effettuate all'indirizzo
localhost:8080.

Nel caso in cui l'utente voglia analizzare un periodo generico, diverso da quello di default, dovrà  inserire come parametri il giorno, il mese e l'anno d'inizio e di fine dello stesso.

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

<b><i>ATTENZIONE!</i></b> Non sono registrati dati per periodi precedenti al 22.6.2017, quindi qualsiasi periodo che terminerà prima di tale data sarà  considerato non valido e quindi il programma genererà  l'eccezione <b>WrongPeriodException</b> e stamperà  a video <i>"ERR: Il periodo inserito non è valido!"</i>.

</b> <i>Esempio:</i>

In questo caso nella data di fine il giorno inserito è 32, chiaramente è un valore non accettabile

![err](https://user-images.githubusercontent.com/75033311/104929217-e3200580-59a3-11eb-9a48-5ce94f548934.png)

La seconda eccezione viene generata quando l'utente inserisce una città  non presente nell file di riferimento(tale file è presente nella repository) o scrive in maniera errata il nome della città  o la sigla dello stato. 

<b><i>ATTENZIONE!</i></b> I nomi delle città  devono essere scritti in inglese. Ad esempio, se si inserisce "Roma" il programma genererà  l'eccezione <b>WrongCityException</b> e stamperà  a video <i>"ERR: La città  o lo stato inseriti non sono validi!"</i>.

<i>Esempi:</i> 
1. In questo caso Ancona è stata scritta in modo errato 

![eccezioni](https://user-images.githubusercontent.com/75033190/104930514-89203f80-59a5-11eb-8ce2-97f9618216b1.PNG)

2. In questo caso la sigla dello stato italiano è stata scritta in modo errato 

![eccezioni2](https://user-images.githubusercontent.com/75033190/104930553-94736b00-59a5-11eb-9dea-b5f98eeb791c.PNG)

# Test
Abbiamo implementato sei unità di test: 
1. <b>APICallTest</b> per testare la classe <i>APICall</i>, che gestisce le chiamate API e restituisce il JSONArray con i dati della città  e del periodo introdotti
2. <b>CityFileReaderTest</b> per testare la classe <i>CityFileReader</i>, che gestisce il file delle città  e restituisce latitudine e longitudine della città  inserita 
3. <b>StatsTest</b> per testare la classe <i>Stats</i>, che definisce le statistiche mensili e stagionali predendo i dati dal JSONArray restituito dalla APICall
4. <b>ConfrontoTest</b> per testare la classe <i>Confronto</i>, che restituisce un JSONArray composto da due JSONObject le statistiche prese in due anni consecutivi di un medesimo periodo
5.  <b>WrongPeriodExceptionTest</b> per testare la classe <i>WrongPeriodException</i>, che gestisce le eccezioni dovute ad un inserimento errato o non valido del periodo
6.  <b>WrongCityExceptionTest</b> per testare la classe <i>WrongCityException</i>, che gestisce le eccezioni dovute ad un inserimento errato o non disponibile di una città e/o del suo stato

# Autori
Il programma è stato sviluppato in modo equo da:

@ RebeccaGiuliani & @ SimoneBruschi
