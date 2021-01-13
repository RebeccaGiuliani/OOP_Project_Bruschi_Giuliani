package it.univpm.progettoOOP.controller;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.stats.MonthStats;

@RestController
public class StatsController {
	
	
	@Autowired
	MonthStats stats = new MonthStats(new Period(1, 1, 2019, 28, 2, 2019), new City( "Ancona", "IT"));
	
	/*@RequestMapping(value = "/data/stats/max&min", method = RequestMethod.GET) 
	public String stats2() {
		Stats s = new Stats(call);
		return "Il valore massimo è "+s.getMax()+", il valore minimo è "+ s.getMin();
	}
	
	@RequestMapping(value = "/data/stats", method = RequestMethod.GET) 
	public Stats stats() {
		APICallController c = new APICallController();
		APICall call = c.chiamata_api (c.getPeriod().getStart_day(), c.getPeriod().getStart_month(), c.getPeriod().getStart_year(),
				c.getPeriod().getEnd_day(), c.getPeriod().getEnd_month(),
				c.getPeriod().getEnd_year(), c.getCity().getName(), c.getCity().getCountry());
		
		return new Stats(call);
	}*/
	
	/*@RequestMapping(value = "/stats/monthly", method = RequestMethod.GET)
	public JSONArray getMonthlyStats(){
		JSONArray ja = new JSONArray();
		
		return ja;
	}*/
	
	@RequestMapping(value = "/stats/monthly")
	public ResponseEntity<Object> getStats(){
		return new ResponseEntity<>(stats.DataStats(), HttpStatus.OK);
	}
}
