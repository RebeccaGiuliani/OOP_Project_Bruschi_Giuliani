package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;

public interface ConfrontoService {
	
	public abstract int end_day(int month);
	public abstract Vector<Double> gestioneDati(Stats s);
	public abstract JSONArray ConfrontoStats();
}
