package it.univpm.progettoOOP.stats;

import java.util.Vector;

public interface StatsService {

	public abstract Vector<Double> monthStats();
	public abstract double getVarianza(double media, int month, int year);
	public abstract double getVarianzaSeason (double media);
	public abstract void seasonStats();
	public abstract double getValue(String date);
	public abstract double getMax();
	public abstract double getMin();
	public abstract Vector<String> getGiorno();
	public abstract Vector<String> getGiorni();

	
}
