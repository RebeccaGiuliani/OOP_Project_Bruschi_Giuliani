package it.univpm.progettoOOP.model;

public class Period {

	protected String start_date;
	protected String end_date;

	public Period(int start_day, int start_month, int start_year, int end_day, int end_month, int end_year) {
		this.start_date = start_day+"/"+start_month+"/"+ start_year;
		this.end_date = end_day+"/"+end_month+"/"+end_year;
	}

	public String getStart() {
		return this.start_date;
	}

	public void setStart(String start_date) {
		this.start_date = start_date;
	}
	
	public String getEnd() {
		return this.end_date;
	}
	
	public void setEnd(String end_date) {
		this.end_date = end_date;
	}
}
