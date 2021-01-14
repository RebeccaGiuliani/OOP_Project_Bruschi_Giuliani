package it.univpm.progettoOOP.controller;

import java.util.Collection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.stats.SeasonStats;
import it.univpm.progettoOOP.stats.Stats;

@RestController
public class StatsController {
	
	APICallController c = new APICallController();
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/stats/monthly/{city_name}/{country}", method = RequestMethod.GET)
	public Collection<Dati> getMonthlyStats(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		Stats stats = new Stats(new Period(1,1,2019,31,12,2019), new City(city_name, country));
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
	@RequestMapping(value = "/stats/spring/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Spring(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Spring(2019), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/summer/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Summer(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Summer(2019), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/autumn/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Autumn(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Autumn(2019), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
	
	@SuppressWarnings("unchecked")  
	@RequestMapping(value = "/stats/winter/{city_name}/{country}", method = RequestMethod.GET)
	public JSONObject SeasonStats_Winter(@PathVariable ("city_name") String city_name, @PathVariable ("country") String country){
		SeasonStats stats = new SeasonStats(new Winter(2019), new City(city_name, country));
			JSONObject jo = new JSONObject();
			jo.put("Media", stats.media());
			jo.put("Varianza", stats.getVarianza(stats.media()));
			jo.put("Massimo", stats.getMax());
			jo.put("Minimo", stats.getMin());
		 return jo;
	}
}
