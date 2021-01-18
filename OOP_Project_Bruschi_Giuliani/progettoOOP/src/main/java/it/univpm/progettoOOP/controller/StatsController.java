package it.univpm.progettoOOP.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.model.Year;
import it.univpm.progettoOOP.stats.SeasonStats;
import it.univpm.progettoOOP.stats.Stats;

/**
 *<p>
 *La classe <b>StatsController</b> permette all'utente di visualizzare le statistiche (mensili/stagionali) di un periodo generico inserito dall'utente 
 *e di visualizzare le statistiche generiche di una specifica stagione, dopo l'inserimento dell'anno di interesse.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 * 
 */

@RestController
public class StatsController {

	/**
	 * Questo metodo permette di visualizzare le statistiche mensili relativamente ad un periodo specifico e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere il periodo di default (1.1.2019-31.12.2019).
	 * 
	 * @param start_day indica il giorno di inizio del periodo di interesse
	 * @param start_month  indica il mese di inizio del periodo di interesse
	 * @param start_year  indica l'anno di inizio del periodo di interesse
	 * @param end_day  indica il giorno di fine del periodo di interesse
	 * @param end_month  indica il mese di fine del periodo di interesse
	 * @param end_year  indica l'anno di fine del periodo di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	@RequestMapping(value = "/stats/monthly/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getMonthlyStats(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country) throws WrongPeriodException, WrongCityException {

		Stats stats = new Stats(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.MonthlyDataStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche mensili relativamente ad un anno speficifico e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/monthly/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getMonthlyStats(@PathVariable("year") int year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
					throws WrongPeriodException, WrongCityException {

		Stats stats = new Stats(new Year(year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.MonthlyDataStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche stagionali relativamente ad un periodo specifico e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere il periodo di default (1.1.2019-31.12.2019).
	 * 
	 * @param start_day indica il giorno di inizio del periodo di interesse
	 * @param start_month  indica il mese di inizio del periodo di interesse
	 * @param start_year  indica l'anno di inizio del periodo di interesse
	 * @param end_day  indica il giorno di fine del periodo di interesse
	 * @param end_month  indica il mese di fine del periodo di interesse
	 * @param end_year  indica l'anno di fine del periodo di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/seasonally/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getSeasonallyStats(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country) throws WrongPeriodException, WrongCityException {

		Stats stats = new Stats(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new City(city_name, country));
		JSONArray ja = stats.SeasonDataStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche stagionali relativamente ad un anno speficifico e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONArray</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/seasonally/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getSeasonallyStats(@PathVariable("year") int year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
					throws WrongPeriodException, WrongCityException{

		Stats stats = new Stats(new Year(year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.SeasonDataStats();
		return ja;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche della primavera relativamente ad un anno e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/spring/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Spring(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		SeasonStats stats = new SeasonStats(new Spring(year), new City(city_name, country));
		JSONObject jo = stats.SeasonDataStats();
		return jo;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche dell'estate relativamente ad un anno e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/summer/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Summer(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		SeasonStats stats = new SeasonStats(new Summer(year), new City(city_name, country));
		JSONObject jo = stats.SeasonDataStats();
		return jo;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche dell'autunno relativamente ad un anno e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/autumn/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Autumn(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		SeasonStats stats = new SeasonStats(new Autumn(year), new City(city_name, country));
		JSONObject jo = stats.SeasonDataStats();
		return jo;
	}
	
	/**
	 * Questo metodo permette di visualizzare le statistiche dell'inverno relativamente ad un anno e ad una città inseriti dall'utente.
	 * Quest'ultimo può scegliere di mantenere l'anno di default (2019).
	 * 
	 * @param year indica l'anno di interesse
	 * @param city_name  indica la città di interesse
	 * @param country  indica lo stato della città di interesse
	 * 
	 * @return <code>JSONObject</code>
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato 
	 * @throws WrongCityException città inserita sbagliata
	 */

	@RequestMapping(value = "/stats/winter/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Winter(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		SeasonStats stats = new SeasonStats(new Winter(year), new City(city_name, country));
		JSONObject jo = stats.SeasonDataStats();
		return jo;
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
