package it.univpm.progettoOOP.stats;

import java.util.Vector;

import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Dati;

public interface MonthStatsService {

	public abstract Vector<Double> media();
	public abstract Vector<Double> varianza();
	public abstract double getValue(String date);
	public abstract Vector<Date> getDate();
	public abstract Vector<Integer> DayCounter();
	//public abstract Vector<Double> ValueCollection(int index);
	public abstract Vector<Double> getMax();
	public abstract Vector<Double> getMin();
	public abstract Vector<Dati> DataStats();

	
}
