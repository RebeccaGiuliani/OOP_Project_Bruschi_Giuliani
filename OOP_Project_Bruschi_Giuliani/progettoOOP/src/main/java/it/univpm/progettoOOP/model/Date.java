package it.univpm.progettoOOP.model;

public class Date {
	protected String date;
	protected String year;
	protected String month;
	protected String day;

	public Date(String date) {
		
		String[] param0 = date.split("T");
		String date0 = param0[0];
		
		this.date = date0;
		String[] param = date0.split("-");
		this.year = param[0];
		this.month = param[1];
		this.day = param[2];
	}

	public String getDay(){
		return this.day;
	} 

	public String getMonth(){
		return this.month;
	}

	public String getYear(){
		return this.year;
	}
	
	public String getDate() {
		return this.date;
	}
}
