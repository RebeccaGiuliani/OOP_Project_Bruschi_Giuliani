package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;

public class Spring extends Period{

	private static int start_day = 21;
	private static int start_month = 3;
	private static int end_day = 20;
	private static int end_month = 6;
	private int year;
	
	public Spring (int year) throws WrongPeriodException {
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
