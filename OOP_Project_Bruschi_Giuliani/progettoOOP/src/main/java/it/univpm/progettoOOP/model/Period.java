package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;

public class Period {

	protected String start_date;
	protected String end_date;
	private int start_day;
	private int start_month;
	private int start_year;
	private int end_day;
	private int end_month;
	private int end_year;	

	public Period(int start_day, int start_month, int start_year, int end_day, int end_month, int end_year) throws WrongPeriodException {
		this.start_date = start_day+"-"+start_month+"-"+ start_year;
		this.end_date = end_day+"-"+end_month+"-"+end_year;
		setStart_day(start_day);
		setStart_month(start_month);
		setStart_year(start_year);
		setEnd_day(end_day);
		setEnd_month(end_month);
		setEnd_year(end_year);
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

	public int getStart_day() {
		return start_day;
	}

	public void setStart_day(int start_day) throws WrongPeriodException {
		if(start_day>31 || start_day == 0)throw new WrongPeriodException();
		else this.start_day = start_day;
	}

	public int getStart_month() {
		return start_month;
	}

	public void setStart_month(int start_month) throws WrongPeriodException {
		if(start_month>12 || start_month == 0) throw new WrongPeriodException();
		this.start_month = start_month;
	}

	public int getStart_year() {
		return start_year;
	}

	public void setStart_year(int start_year) throws WrongPeriodException {
		if(start_year<2017) throw new WrongPeriodException();
		else this.start_year = start_year;
	}

	public int getEnd_day() {
		return end_day;
	}

	public void setEnd_day(int end_day) throws WrongPeriodException {
		if(end_day == 0 || end_day >31) throw new WrongPeriodException();
		else this.end_day = end_day;
	}

	public int getEnd_month() {
		return end_month;
	}

	public void setEnd_month(int end_month) throws WrongPeriodException {
		if(end_month>12 || end_month == 0 ) throw new WrongPeriodException();
		else this.end_month = end_month;
	}

	public int getEnd_year() {
		return end_year;
	}

	public void setEnd_year(int end_year) throws WrongPeriodException {
		
		if(end_year<2017) throw new WrongPeriodException();
		else this.end_year = end_year;
		
	}
}
