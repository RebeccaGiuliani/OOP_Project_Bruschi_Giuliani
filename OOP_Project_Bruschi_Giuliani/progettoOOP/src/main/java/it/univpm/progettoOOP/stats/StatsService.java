package it.univpm.progettoOOP.stats;

public interface StatsService {

	public abstract void monthStats();
	public abstract void seasonStats();
	public abstract double getVarianza(double media, int month, int year);
	public abstract double getVarianzaSeason (double media);
	public abstract double getValue(String date);
	public abstract void getStats(int cont, String giorno);
	public abstract double getMax();
	public abstract double getMin();
	public abstract void getGiorni(int cont, String giorno);

	
}
