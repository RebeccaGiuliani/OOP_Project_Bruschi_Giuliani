package it.univpm.progettoOOP.test;

import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;

class APICallTest {

	private APICall api_call, api_call2;
	private JSONArray ja;
	private JSONObject jo1, jo2;
	
	@BeforeEach
	void setUp() throws Exception {
		api_call = new APICall(new Period(1,1,2019,31,12,2019), new CityFileReader(new City("Ancona", "IT")));
		api_call2 = new APICall(new Period(1,1,2019,2,1,2019), new CityFileReader(new City("Ancona", "IT")));
		ja = new JSONArray();
		jo1 = new JSONObject();
		jo2 = new JSONObject();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	@DisplayName("Data d'inizio convertita correttamente")
	void StartDateUnixConverterTest() {
		
		assertEquals(1546344000, api_call.StartDateUnixConverter());
		
	}
	
	@Test
	@DisplayName("Data di fine convertita correttamente")
	void EndDateUnixConverterTest() {
		
		assertEquals(1577793600, api_call.EndDateUnixConverter());
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Dati ottenuti correttamente")
	void getDataTest() {
		
		jo1.put("date", 1546344000);
		jo1.put("date_iso", "2019-01-01T12:00:00Z");
		jo1.put("lon", 13.51008);
		jo1.put("value", 0.97);
		jo1.put("lat", 43.59816);
		
		jo2.put("date", 1546430400);
		jo2.put("date_iso", "2019-01-02T12:00:00Z");
		jo2.put("lon", 13.51008);
		jo2.put("value", 0.93);
		jo2.put("lat", 43.59816);
		
		ja.add(jo1);
		ja.add(jo2);
		
		assertEquals(ja.toString(), api_call2.getData().toString());
		
	}

}
