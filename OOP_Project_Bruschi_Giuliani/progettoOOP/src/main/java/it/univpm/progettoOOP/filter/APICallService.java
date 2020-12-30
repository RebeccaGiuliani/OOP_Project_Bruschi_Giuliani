package it.univpm.progettoOOP.filter;

import org.json.simple.JSONObject;

public interface APICallService {

	public abstract JSONObject getData();
	public abstract long StartDateUnixConverter();
	public abstract long EndDateUnixConverter();
}
