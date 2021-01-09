package it.univpm.progettoOOP.stats;

public interface StatsService {

	public abstract void monthStats();
	public abstract void seasonStats();
	public abstract double getVarianza(double media, String month, String year);
	public abstract double getVarianzaSeason (double media);
	public abstract double getValue(String date);
	public abstract void getStats();
	public abstract void getGiorniMax();
	public abstract void getGiorniMin();
	
}
