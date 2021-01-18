package it.univpm.progettoOOP.filter;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.model.City;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileReader;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * <p>
 * <b>Classe</b> che implementa l'interfaccia <b>CityFileReaderService</b> e permette di leggere il file e estrarne i dati di interesse 
 * <p>
 * 
 * @see it.univpm.progettoOOP.filter.CityFileReaderService
 * 
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 */
public class CityFileReader implements CityFileReaderService {

	private static String name = "city.list.json";	
	private double lat, lon;
	private String nameCity, country;

	/**
	 * costruttore
	 * 
	 * @param city indica la città di interesse
	 */
	public CityFileReader(City city) {
		this.nameCity = city.getName();
		this.country = city.getCountry();
	}

	@Override
	public JSONArray caricaArray() {
		JSONParser jsonParser = new JSONParser();
		JSONArray cityList = null;
		
		try (FileReader reader = new FileReader(name)){
			//Read JSON file
			Object obj = jsonParser.parse(reader);
			cityList = new JSONArray();
			return cityList = (JSONArray) obj;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return cityList;
	}

	@Override
	public void getCity(JSONArray ja) throws WrongCityException {
		//Get city object within list
		for(int i =0; i<ja.size(); i++) {
			JSONObject cityObject = (JSONObject) ja.get(i);
			if(cityObject.get("name").equals(this.nameCity)) {
				if(cityObject.get("country").equals(this.country)) {
					//Get coord object within list
					JSONObject coordObject = (JSONObject) cityObject.get("coord");
					//Get lon
					this.lon = (Double) coordObject.get("lon");    
					//Get lat
					this.lat = (Double) coordObject.get("lat");  
				}
			}
		} 
		if (lat == 0 && lon == 0) throw new WrongCityException();
	}
	
	@Override
	public double getLat() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCity(ja);
		return this.lat;
	}
	
	@Override
	public double getLon() throws WrongCityException {
		JSONArray ja = new JSONArray();
		ja = caricaArray();
		getCity(ja);
		return this.lon;
	}
}
