package it.univpm.progettoOOP.filter;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.exception.WrongCityException;

public interface CityFileReaderService {
	
	public abstract JSONArray caricaArray();
	public abstract void getCity(JSONArray ja) throws WrongCityException;
	public abstract double getLat() throws WrongCityException;
	public abstract double getLon() throws WrongCityException;
	
}
