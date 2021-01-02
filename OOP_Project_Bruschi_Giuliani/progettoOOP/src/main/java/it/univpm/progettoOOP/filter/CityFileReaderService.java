package it.univpm.progettoOOP.filter;

import org.json.simple.JSONArray;

public interface CityFileReaderService {
	public abstract void getCity(JSONArray ja, String nameCity, String country);
	public abstract JSONArray caricaArray();
	public abstract double getLat();
	public abstract double getLon();
}
