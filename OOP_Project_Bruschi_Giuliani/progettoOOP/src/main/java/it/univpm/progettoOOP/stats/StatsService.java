package it.univpm.progettoOOP.stats;

public interface StatsService {

	public abstract void monthStats();
	public abstract void seasonStats();
	public abstract double getMax();
	public abstract double getMin();
	public abstract double getVarianza(double media, String month);
	public abstract double getValue(double date);
	public abstract void getStats();
	public abstract void getGiorniMax();
	public abstract void getGiorniMin();
	public abstract double compare();
	
}
