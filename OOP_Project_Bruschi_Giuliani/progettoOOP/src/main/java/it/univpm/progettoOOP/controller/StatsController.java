package it.univpm.progettoOOP.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.model.Year;
import it.univpm.progettoOOP.stats.SeasonStats;
import it.univpm.progettoOOP.stats.Stats;

@RestController
public class StatsController {
	
	@RequestMapping(value = "/stats/monthly/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getMonthlyStats(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		
		Stats stats = new Stats(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.MonthlyDataStats();
		 return ja;
	}
	
	@RequestMapping(value = "/stats/monthly/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getMonthlyStats(@PathVariable("year") int year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		
		Stats stats = new Stats(new Year(year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.MonthlyDataStats();
		 return ja;
	}
	
	//da sistemare per periodi diversi da un anno
	@RequestMapping(value = "/stats/seasonally/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getSeasonallyStats(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		
		Stats stats = new Stats(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new City(city_name, country));
		JSONArray ja = stats.SeasonDataStats();
		return ja;
	}
	
	@RequestMapping(value = "/stats/seasonally/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONArray getSeasonallyStats(@PathVariable("year") int year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		
		Stats stats = new Stats(new Year(year), new City(city_name, country));
		JSONArray ja = (JSONArray) stats.SeasonDataStats();
		 return ja;
	}
	
	@RequestMapping(value = "/stats/spring/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Spring(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Spring(year), new City(city_name, country));
			JSONObject jo = stats.SeasonDataStats();
		 return jo;
	}
	
	@RequestMapping(value = "/stats/summer/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Summer(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Summer(year), new City(city_name, country));
			JSONObject jo = stats.SeasonDataStats();
		 return jo;
	}
	 
	@RequestMapping(value = "/stats/autumn/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Autumn(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Autumn(year), new City(city_name, country));
			JSONObject jo = stats.SeasonDataStats();
		 return jo;
	}
	 
	@RequestMapping(value = "/stats/winter/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Winter(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Winter(year), new City(city_name, country));
			JSONObject jo = stats.SeasonDataStats();
		 return jo;
	}
}
