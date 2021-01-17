package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Period;

public interface StatsService {

	public abstract Vector<Double> media();
	public abstract double varianza(double media, int month, int year);
	public abstract Vector<Double> getVarianza();
	public abstract Vector<Double> getMax();
	public abstract Vector<Double> getMin();
	public abstract Vector<Double> mediaSeason() throws WrongPeriodException;
	public abstract double varianzaSeason (double media, Period period);
	public abstract Vector<Double> getVarianzaSeason();
	public abstract double MaxSeason(Period period);
	public abstract Vector<Double> getMaxSeason();
	public abstract double MinSeason(Period period);
	public abstract Vector<Double> getMinSeason();
	public abstract double getValue(String date);
	public abstract Vector<Integer> DayCounter();
	public abstract JSONArray MonthlyDataStats();
	public abstract JSONArray SeasonDataStats() throws WrongPeriodException;
	
}
