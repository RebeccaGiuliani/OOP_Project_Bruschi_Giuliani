package it.univpm.progettoOOP.filter;

import org.json.simple.JSONArray;

public interface APICallService {

	public abstract JSONArray getData();
	public abstract long StartDateUnixConverter();
	public abstract long EndDateUnixConverter();
}
