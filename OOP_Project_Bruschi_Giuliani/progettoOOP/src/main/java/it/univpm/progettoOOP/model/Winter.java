package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;

public class Winter extends Period{
	
	private static int start_day = 22;
	private static int start_month = 12;
	private static int end_day = 20;
	private static int end_month = 3;
	private int year;
	
	public Winter (int year) throws WrongPeriodException {   //per l'inverno prendiamo in considerazione l'anno d'inizio
		super (start_day, start_month, year, end_day, end_month, year+1);
		this.year = year;		
	}
	
	public String getStart() {
		return super.getStart_day()+"-"+super.getStart_month()+"-"+this.year;
	}

	public String getEnd() {
		return super.getEnd_day()+"-0"+super.getEnd_month()+"-"+(this.year+1);
	}
}
