package it.univpm.progettoOOP.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.stats.Confronto;
import it.univpm.progettoOOP.stats.ConfrontoSeason;

/**
 *<p>
 *La classe <b>ConfrontoController</b> permette all'utente di mettere a confronto le statistiche di due medesimi periodi(uno specifico mese o una stagione) 
 *prese in due anni consecutivi, dopo l'inserimento di una città e del periodo di interesse.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 * 
 */

@RestController
public class ConfrontoController {

	/**
	 * Questo metodo permette, dopo l'inserimento di un mese, di un anno e di una città di interesse, di confrontare le statistiche del medesimo mese prese in quell'anno e nell'anno precedente.
	 * 
	 * @param month  indica il mese di interesse
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica il nome della città di interesse
	 * @param country  indica il nome dello stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/confronto/stats/{month}/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats(@PathVariable ("month") int month, @PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country)
			throws WrongPeriodException, WrongCityException{
		Confronto confronto = new Confronto(month, year, new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette, dopo l'inserimento di un anno e di una città di interesse, di confrontare le statistiche della Primavera di quell'anno e dell'anno precedente.
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica il nome della città di interesse
	 * @param country  indica il nome dello stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/confronto/stats/spring/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Spring(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Spring(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette, dopo l'inserimento di un anno e di una città di interesse, di confrontare le statistiche dell'Estate di quell'anno e dell'anno precedente.
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica il nome della città di interesse
	 * @param country  indica il nome dello stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/confronto/stats/summer/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Summer(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Summer(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	/**
	 *  Questo metodo permette, dopo l'inserimento di un anno e di una città di interesse, di confrontare le statistiche dell'Autunno di quell'anno e dell'anno precedente.
	 *  
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica il nome della città di interesse
	 * @param country  indica il nome dello stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/confronto/stats/autumn/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Autumn(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Autumn(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	/**
	 *  Questo metodo permette, dopo l'inserimento di un anno e di una città di interesse, di confrontare le statistiche dell'Inverno di quell'anno e dell'anno precedente.
	 * 
	 * @param year  indica l'anno di interesse
	 * @param city_name  indica il nome della città di interesse
	 * @param country  indica il nome dello stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/confronto/stats/winter/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Winter(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Winter(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
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
