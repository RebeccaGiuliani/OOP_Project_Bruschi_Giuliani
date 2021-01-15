package it.univpm.progettoOOP.stats;

import org.json.simple.JSONObject;

public interface SeasonStatsService {
	
	public abstract double media();
	public abstract double getVarianza(double media);
	public abstract double getValue(String date);
	public abstract double getMax();
	public abstract double getMin();
	public abstract JSONObject SeasonDataStats();

}
