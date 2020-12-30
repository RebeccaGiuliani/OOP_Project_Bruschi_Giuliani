package it.univpm.progettoOOP.filter;

import org.json.simple.JSONObject;

public interface FilterService {

	public abstract String getCity();
	public abstract String getCountry();
	public abstract double getLat();
	public abstract double getLon();
	public abstract JSONObject getData();
}
