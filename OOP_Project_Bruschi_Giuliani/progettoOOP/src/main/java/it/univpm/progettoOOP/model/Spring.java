package it.univpm.progettoOOP.model;

public class Spring extends Period{

	private static int start_day = 21;
	private static int start_month = 3;
	private static int end_day = 21;
	private static int end_month = 6;
	private int year;
	
	public Spring (int year) {
		super (start_day, start_month, year, end_day, end_month, year);
		this.year = year;
	}
	
	public String getStart() {
		return super.getStart()+"/"+this.year;
	}

	public String getEnd() {
		return super.getEnd()+"/"+this.year;
	}
}
