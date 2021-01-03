package it.univpm.progettoOOP.stats;

public interface StatsService {

	public abstract void monthStats();
	public abstract void seasonStats();
	public abstract double getMax();
	public abstract double getMin();
	public abstract double getVariance();
	public abstract double compare();
	
}
