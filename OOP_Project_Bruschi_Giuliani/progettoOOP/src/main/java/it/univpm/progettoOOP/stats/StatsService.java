package it.univpm.progettoOOP.stats;

import java.util.Vector;

public interface StatsService {

	public abstract void monthStats();
	public abstract void seasonStats();
	public abstract double getVarianza(double media, int month, int year);
	public abstract double getVarianzaSeason (double media);
	public abstract double getValue(String date);
	public abstract Vector<String> getStats(int cont, String giorno);
	public abstract double getMax();
	public abstract double getMin();
	public abstract Vector<String> getGiorni(int cont, String giorno);

	
}
