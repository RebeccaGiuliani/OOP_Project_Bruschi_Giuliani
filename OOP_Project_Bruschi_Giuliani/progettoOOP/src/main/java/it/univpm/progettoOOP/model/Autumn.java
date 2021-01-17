package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;

public class Autumn extends Period{

	private static int start_day = 23;
	private static int start_month = 9;
	private static int end_day = 21;
	private static int end_month = 12;
	private int year;
	
	public Autumn (int year) throws WrongPeriodException {
		super (start_day, start_month, year, end_day, end_month, year);
		this.year = year;
	}
	
	public String getStart() {
		return super.getStart_day()+"-0"+super.getStart_month()+"-"+this.year;
	}

	public String getEnd() {
		return super.getEnd_day()+"-0"+super.getEnd_month()+"-"+this.year;
	}
}
