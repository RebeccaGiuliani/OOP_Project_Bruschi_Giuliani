package it.univpm.progettoOOP.controller;

import java.util.Collection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.model.Year;
import it.univpm.progettoOOP.stats.SeasonStats;
import it.univpm.progettoOOP.stats.Stats;

@RestController
public class StatsController {
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stats/monthly/{city_name}/{country}", method = RequestMethod.GET)
	public Collection<Dati> getMonthlyStats(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		Stats stats = new Stats(new Period(start_day,start_month,start_year,end_day,end_month,end_year), new City(city_name, country));
		JSONArray ja = new JSONArray();
		Vector<Integer> counter = stats.DayCounter();
		for(int i=0; i<counter.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media().get(i));
			jo.put("Varianza", stats.varianza().get(i));
			jo.put("Max", stats.getMax().get(i));
			jo.put("Min", stats.getMin().get(i));
			ja.add(jo);
		}
		 return ja;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stats/monthly/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public Collection<Dati> getMonthlyStats(@PathVariable("year") int year,
			@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		Stats stats = new Stats(new Year(year), new City(city_name, country));
		JSONArray ja = new JSONArray();
		Vector<Integer> counter = stats.DayCounter();
		for(int i=0; i<counter.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media().get(i));
			jo.put("Varianza", stats.varianza().get(i));
			jo.put("Max", stats.getMax().get(i));
			jo.put("Min", stats.getMin().get(i));
			ja.add(jo);
		}
		 return ja;
	}
	
	@SuppressWarnings("unchecked")  //non funziona ancora, mi dà eccezione
	@RequestMapping(value = "/stats/seasonally/{city_name}/{country}", method = RequestMethod.GET)
	public Collection<Dati> getSeasonStats(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		Stats stats = new Stats(new Period(1,1,2019,31,12,2019), new City(city_name, country));
		JSONArray ja = new JSONArray();
		Vector<Integer> counter = stats.DayCounter();
		for(int i=0; i<counter.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("Media stagionale", stats.mediaSeason().get(i));
			jo.put("Varianza stagionale", stats.varianzaSeason().get(i));
			jo.put("Massimo stagionale", stats.getMaxSeason().get(i));
			jo.put("Minimo stagionale", stats.getMinSeason().get(i));
			ja.add(jo);
		}
		 return ja;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/spring/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Spring(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Spring(year), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/summer/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Summer(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Summer(year), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/autumn/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Autumn(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Autumn(year), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/winter/{year}/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Winter(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Winter(year), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
}
