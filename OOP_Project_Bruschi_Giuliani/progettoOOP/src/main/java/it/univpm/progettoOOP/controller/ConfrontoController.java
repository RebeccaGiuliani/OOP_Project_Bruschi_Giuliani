package it.univpm.progettoOOP.controller;

import org.json.simple.JSONArray;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.stats.Confronto;
import it.univpm.progettoOOP.stats.ConfrontoSeason;

public class ConfrontoController {

	@RequestMapping(value = "/confronto/stats/{month}/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats(@PathVariable ("month") int month, @PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country)
			throws WrongPeriodException, WrongCityException{
		Confronto confronto = new Confronto(month, year, new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	@RequestMapping(value = "/confronto/stats/spring/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Spring(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Spring(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	@RequestMapping(value = "/confronto/stats/summer/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Summer(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Summer(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	@RequestMapping(value = "/confronto/stats/autumn/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Autumn(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Autumn(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	@RequestMapping(value = "/confronto/stats/winter/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray ConfrontoStats_Winter(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country) 
			throws WrongPeriodException, WrongCityException{
		ConfrontoSeason confronto = new ConfrontoSeason(new Winter(year), new City(city_name, country));
		JSONArray ja = confronto.ConfrontoStats();
		return ja;
	}
	
	@ExceptionHandler(WrongPeriodException.class)
	public static String ErrorPage(WrongPeriodException e) {
		return e.getMex();
	}
	
	@ExceptionHandler(WrongCityException.class)
	public static String ErrorPage(WrongCityException e) {
		return e.getMex();
	}
}
