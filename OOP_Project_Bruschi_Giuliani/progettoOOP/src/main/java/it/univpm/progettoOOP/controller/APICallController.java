package it.univpm.progettoOOP.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Year;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

/**
 *<p>
 *La classe <b>APICallController</b> mi permette di gestire le richieste di filtraggio dati,
 *tramite l'inserimento da parte dell'utente di una città e di un periodo di interesse.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 * 
 */

@RestController
public class APICallController {

	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città e di un periodo specifico inseriti dall'utente attraverso i parametri. 
	 * L'utente può anche scegliere di mantere il periodo di filtraggio di default (1.1.2019-31.12.2019) oppure uno/alcuni dei parametri di default.
	 * 
	 * @param start_day   indica il giorno di inizio
	 * @param start_month  indica il mese di inizio
	 * @param start_year  indica l'anno di inzio
	 * @param end_day   indica il giorno di fine
	 * @param end_month  indica il mese di fine
	 * @param end_year  indica l'anno di fine
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	@RequestMapping(value = "/data/filter/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall (
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country) throws WrongPeriodException, WrongCityException {

		return new APICall(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new CityFileReader(new City(city_name, city_country)));		 
	}
	
	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città e di un anno specifico inseriti dall'utente attraverso i parametri. 
	 * L'utente può anche scegliere di mantere l'anno di filtraggio di default (2019).
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/data/filter/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ) 
			throws WrongPeriodException, WrongCityException{

		return new APICall(new Year(year), new CityFileReader(new City(city_name, city_country)));	
	}

	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città inserita dall'utente e del periodo corrispondente 
	 * alla primavera di un anno specifico, modificabile dall'utente rispetto a quello di default(2019).
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/data/filter/spring/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_spring( @PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ) 
					throws WrongPeriodException, WrongCityException{

		return new APICall(new Spring(year), new CityFileReader(new City(city_name, city_country)));	
	}

	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città inserita dall'utente e del periodo corrispondente 
	 * all'estate di un anno specifico, modificabile dall'utente rispetto a quello di default(2019).
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	@RequestMapping(value = "/data/filter/summer/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_summer(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ) 
					throws WrongPeriodException, WrongCityException{

		return new APICall(new Summer(year), new CityFileReader(new City(city_name, city_country)));	
	}

	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città inserita dall'utente e del periodo corrispondente 
	 * all'autunno di un anno specifico, modificabile dall'utente rispetto a quello di default(2019). 
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/data/filter/autumn/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_autumn(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ) 
					throws WrongPeriodException, WrongCityException{

		return new APICall(new Autumn(year), new CityFileReader(new City(city_name, city_country)));	
	}

	/**
	 * Questo metodo mi permette di filtrare i dati sulla base di una città inserita dall'utente e del periodo corrispondente 
	 * all'inverno di un anno specifico, modificabile dall'utente rispetto a quello di default(2019). 
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param city_country  indica la sigla dello stato della città di interesse
	 * 
	 * @return <code>APICall</code>
	 * @see it.univpm.progettoOOP.filter.APICall
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/data/filter/winter/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_winter(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ) 
					throws WrongPeriodException, WrongCityException{

		return new APICall(new Winter(year), new CityFileReader(new City(city_name, city_country)));	
	}

	/**
	 * Questo metodo gestisce le eccezioni dovute all'inserimento di un periodo erroneo.
	 * 
	 * @see it.univpm.progettoOOP.exception.WrongPeriodException
	 * @see it.univpm.progettoOOP.exception.WrongPeriodException#getMex()
	 * 
	 * @param e indica l'eccezione
	 * 
	 * @return <code>String</code>
	 */
	
	@ExceptionHandler(WrongPeriodException.class)
	public static String ErrorPage(WrongPeriodException e) {
		return e.getMex();
	}
	
	/**
	 * Questo metodo gestisce le eccezioni dovute all'inserimento di una città o di uno stato erronei.
	 * 
	 * @see it.univpm.progettoOOP.exception.WrongCityException
	 * @see it.univpm.progettoOOP.exception.WrongCityException#getMex()
	 * 
	 * @param e indica l'eccezione
	 * 
	 * @return <code>String</code>
	 */
	
	@ExceptionHandler(WrongCityException.class)
	public static String ErrorPage(WrongCityException e) {
		return e.getMex();
	}
}
