package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Period;

public interface StatsService {

	public abstract Vector<Double> media();
	public abstract Vector<Double> varianza();
	public abstract double getValue(String date);
	public abstract Vector<Date> getDate();
	public abstract Vector<Integer> DayCounter();
	public abstract Vector<Double> getMax();
	public abstract Vector<Double> getMin();
	public abstract JSONArray MonthlyDataStats();
	public abstract Vector<Double> mediaSeason();
	public abstract Vector<Double> varianzaSeason();
	public abstract double getVarianzaSeason (double media, Period period);
	public abstract double MaxSeason(Period period);
	public abstract Vector<Double> getMaxSeason();
	public abstract double MinSeason(Period period);
	public abstract Vector<Double> getMinSeason();
	public abstract JSONArray SeasonDataStats();
	
}
