package it.univpm.progettoOOP.model;

public class Date {
	protected String date;
	protected String year;
	protected String month;
	protected String day;

	public Date(String date) {
		
		String[] param0 = date.split("T");
		String date0 = param0[0];
		//String date1 = param0[1];
		
		this.date = date0;
		String[] param = date0.split("-");
		this.year = param[0];
		this.month = param[1];
		this.day = param[2];
		//System.out.println("data:"+this.date);
	}

	public String getDay(){
		//System.out.println("getDay: "+this.day);
		return this.day;
	} 

	public String getMonth(){
		//System.out.println("getMonth: "+this.month);
		return this.month;
	}

	public String getYear(){
		//System.out.println("getYear: "+this.year);
		return this.year;
	}
	
	public String getDate() {
		//System.out.println("getDate: "+this.date);
		return this.date;
	}
}
