package it.univpm.progettoOOP.test;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import  static org.junit.jupiter.api.Assertions.*; 

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.stats.Stats;

public class StatsTest {
	
	private JSONArray ja;
	private City city;
	private Period period;
	private Stats stats;
	private double mediaValue, varValue;
	private APICall call;
//	private double m =  1.1825806451612901;

	@BeforeEach
	void setUp() throws Exception {
		city = new City("Ancona", "IT");
		period = new Period(1, 1, 2019, 31, 1, 2019);
		call = new APICall(period, new CityFileReader(city));
		ja = call.getData();
		stats = new Stats(period, city);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
    @DisplayName("Media Corretta")
    void mediatest() throws IOException {

		double somma = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = stats.getValue((String) Object.get("date_iso"));

			somma += value; 
			
		}//Chiusura FOR
		mediaValue = (somma/ja.size());
		
		assertEquals(mediaValue ,stats.media().get(0));	
    }
	
	
	@Test
    @DisplayName("Varianza Corretta")
    void varianzatest() throws IOException {

		double varianza0 = 0;
		
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = stats.getValue((String) Object.get("date_iso"));

			varianza0 += Math.pow(value-stats.media().get(0), 2);
			
		}//Chiusura FOR
		varValue = (varianza0/ja.size());
		
		assertEquals(varValue ,stats.varianza(stats.media().get(0), period.getStart_month(), period.getStart_year()));	
    }
}
