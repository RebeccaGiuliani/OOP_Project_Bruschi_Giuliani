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
	private Stats stats;
	private double mediaValue, varValue;
	private APICall call;

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Media Corretta")
	public void mediatest() throws Exception {
		stats = new Stats(new Period(1, 1, 2019, 31, 1, 2019), new City("Ancona", "IT"));
		call = new APICall(new Period(1, 1, 2019, 31, 1, 2019), new CityFileReader(new City("Ancona", "IT")));
		ja = call.getData();

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
	public void varianzatest() throws IOException {
		stats = new Stats(new Period(1, 1, 2019, 31, 1, 2019), new City("Ancona", "IT"));
		call = new APICall(new Period(1, 1, 2019, 31, 1, 2019), new CityFileReader(new City("Ancona", "IT")));
		ja = call.getData();

		double varianza0 = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = stats.getValue((String) Object.get("date_iso"));

			varianza0 += Math.pow(value-stats.media().get(0), 2);

		}//Chiusura FOR
		varValue = (varianza0/ja.size());

		assertEquals(varValue ,stats.varianza(stats.media().get(0), 1, 2019));	
	}
}
