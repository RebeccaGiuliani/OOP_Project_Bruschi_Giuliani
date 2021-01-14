package it.univpm.progettoOOP.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Year;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

@RestController
public class APICallController {
	
	private Period p;
	private City c;
	
	public Period getPeriod() {
		return this.p;
	}
	
	public City getCity() {
		return this.c;
	}
	
	//filtraggio dati per un periodo generico
	@RequestMapping(value = "/data/filter/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall chiamata_api(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){
		this.p = new Period(start_day,start_month,start_year,end_day,end_month,end_year);
		this.c = new City(city_name, city_country);
		return new APICall(this.p, new CityFileReader(this.c));	
	}

	//filtraggio dati per un anno
	@RequestMapping(value = "/data/filter/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall chiamata_api(@PathVariable ("year") int year, @PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){
		this.p = new Year(year);
		this.c = new City(city_name, city_country);
		return new APICall(this.p, new CityFileReader(this.c));	
	}

	//filtraggio dati primavera variando anno e coordinate
	@RequestMapping(value = "/data/filter/spring/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_spring( @PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){

		this.p = new Spring(year);
		this.c = new City(city_name, city_country);

		return new APICall(this.p, new CityFileReader(this.c));	
	}

	//filtraggio dati estate variando l'anno
	@RequestMapping(value = "/data/filter/summer/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_summer(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){
		
		this.p = new Summer(year);
		this.c = new City(city_name, city_country);
		
		return new APICall(this.p, new CityFileReader(this.c));	
	}
	
	//filtraggio dati autunno variando l'anno
	@RequestMapping(value = "/data/filter/autumn/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_autumn(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){
		this.p = new Autumn(year);
		this.c = new City (city_name, city_country);
		return new APICall(this.p, new CityFileReader(this.c));	
	}
	
	//filtraggio dati inverno variando l'anno
	@RequestMapping(value = "/data/filter/winter/{year}/{city_name}/{city_country}", method = RequestMethod.POST)   
	public APICall apiCall_winter(@PathVariable("year") int year, 
			@PathVariable ("city_name") String city_name, @PathVariable ("city_country") String city_country ){
		
		this.p = new Winter(year);
		this.c = new City(city_name, city_country);
		
		return new APICall(this.p, new CityFileReader(this.c));	
	}
}
