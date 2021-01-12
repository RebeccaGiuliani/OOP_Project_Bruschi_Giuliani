package it.univpm.progettoOOP.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.stats.Stats;

@RestController
public class StatsController {
	
	APICallController c = new APICallController();
	
	APICall call = c.chiamata_api(1, 1, 2019, 10, 1, 2019, "Ancona", "IT");
	
	@RequestMapping(value = "/data/stats/max&min", method = RequestMethod.GET) 
	public String stats2() {
		Stats s = new Stats(call);
		return "Il valore massimo è "+s.getMax()+", il valore minimo è "+ s.getMin();
	}
	
	@RequestMapping(value = "/data/stats", method = RequestMethod.GET) 
	public Stats stats() {
		APICallController c = new APICallController();
		APICall call = c.chiamata_api(1,1,2019,10,1,2019, "Ancona", "IT");
		
		return new Stats(call);
	}
}
