package it.univpm.progettoOOP.model;

public class Year extends Period{
	
	private static int start_day = 1;
	private static int start_month = 1;
	private static int end_day = 31;
	private static int end_month = 12;
	private int year;

	public Year(int year) {
		super (start_day, start_month, year, end_day, end_month, year);
		this.year = year;
	}
	
	public String getStart() {
		return super.getStart_day()+"-0"+super.getStart_month()+"-"+this.year;
	}

	public String getEnd() {
		return super.getEnd_day()+"-"+super.getEnd_month()+"-"+this.year;
	}
}
