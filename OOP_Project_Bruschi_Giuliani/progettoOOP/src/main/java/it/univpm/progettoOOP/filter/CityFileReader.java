package it.univpm.progettoOOP.filter;

import it.univpm.progettoOOP.model.City;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CityFileReader implements CityFileReaderService {

	private static String name = "city.list.json";	
	private double lat, lon;
	private String nameCity, country;

	public CityFileReader(City city) {
		this.nameCity = city.getName();
		this.country = city.getCountry();
		
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		
		getCity(ja, nameCity, country);
		System.out.println(lat);
		System.out.println(lon);
	}

	public JSONArray caricaArray() {
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(name)){
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			JSONArray cityList = new JSONArray();
			return cityList = (JSONArray) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void getCity(JSONArray ja, String name, String country) {
		//Get city object within list
		for(int i =0; i<ja.size(); i++) {
			JSONObject cityObject = (JSONObject) ja.get(i);
			if(cityObject.get("name").equals(name)) {
				if(cityObject.get("country").equals(country)) {
					//Get coord object within list
					JSONObject coordObject = (JSONObject) cityObject.get("coord");
					//Get lon
					this.lon = (Double) coordObject.get("lon");    
					//Get lat
					this.lat = (Double) coordObject.get("lat");  
				}
			}
		}
	}
	public double getLat() {
		return this.lat;
	}
	public double getLon() {
		return this.lon;
	}
}
