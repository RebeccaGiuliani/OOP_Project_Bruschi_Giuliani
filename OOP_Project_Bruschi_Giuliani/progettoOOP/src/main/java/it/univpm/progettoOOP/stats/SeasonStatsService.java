package it.univpm.progettoOOP.stats;

import it.univpm.progettoOOP.model.Dati;

public interface SeasonStatsService {
	
	public abstract double media();
	public abstract double getVarianza(double media);
	public abstract double getValue(String date);
	public abstract double getMax();
	public abstract double getMin();
	public abstract Dati SeasonDataStats();

}
