package it.univpm.progettoOOP.model;

public class Autumn extends Period{

	private static int start_day = 23;
	private static int start_month = 9;
	private static int end_day = 22;
	private static int end_month = 12;
	private int year;
	
	public Autumn (int year) {
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
