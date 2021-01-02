package it.univpm.progettoOOP.controller;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

@RestController
public class APICallController {

	//filtraggio dati per un periodo generico
	@RequestMapping(value = "/data/filter", method = RequestMethod.GET)   
	public APICall chiamata_api(
			@RequestParam (name = "start_day", defaultValue = "1") int start_day, @RequestParam (name = "start_month", defaultValue = "1") int start_month,
			@RequestParam (name = "start_year", defaultValue = "2019") int start_year, @RequestParam (name = "end_day", defaultValue = "31")int end_day,
			@RequestParam (name = "end_month", defaultValue = "12") int end_month, @RequestParam (name = "end_year", defaultValue = "2019") int end_year,
			@RequestParam (name = "lat", defaultValue = "43.5991") double lat, @RequestParam (name = "lon", defaultValue = "13.511") double lon){
		
		return new APICall(new Period(start_day,start_month,start_year,end_day,end_month,end_year), lat, lon);	
	}
	
	//filtraggio dati primavera variando anno e coordinate
	@RequestMapping(value = "/data/filter/spring/{year}", method = RequestMethod.GET)   
	public APICall apiCall_spring( @PathVariable("year") int year,
			@RequestParam (name = "lat", defaultValue = "43.5991") double lat, 
			@RequestParam (name = "lon", defaultValue = "13.511") double lon){
		
		return new APICall(new Spring(year), lat , lon);	
	}
	
	//filtraggio dati estate variando l'anno
	@RequestMapping(value = "/data/filter/summer/{year}", method = RequestMethod.GET)   
	public APICall apiCall_summer(@PathVariable("year") int year, 
			@RequestParam (name = "lat", defaultValue = "43.5991") double lat, 
			@RequestParam (name = "lon", defaultValue = "13.511") double lon){
		
		return new APICall(new Summer(year), lat, lon);	
	}
	
	//filtraggio dati autunno variando l'anno
	@RequestMapping(value = "/data/filter/autumn/{year}", method = RequestMethod.GET)   
	public APICall apiCall_autumn(@PathVariable("year") int year,
			@RequestParam (name = "lat", defaultValue = "43.5991") double lat, 
			@RequestParam (name = "lon", defaultValue = "13.511") double lon){
		
		return new APICall(new Autumn(year), lat, lon);	
	}
	
	//filtraggio dati inverno variando l'anno
	@RequestMapping(value = "/data/filter/winter/{year}", method = RequestMethod.GET)   
	public APICall apiCall_winter(@PathVariable("year") int year,
			@RequestParam (name = "lat", defaultValue = "43.5991") double lat, 
			@RequestParam (name = "lon", defaultValue = "13.511") double lon){
		
		return new APICall(new Winter(year), lat, lon);	
	}
}
