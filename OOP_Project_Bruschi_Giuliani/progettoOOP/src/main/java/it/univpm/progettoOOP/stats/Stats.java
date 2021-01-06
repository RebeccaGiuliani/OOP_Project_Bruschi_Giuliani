package it.univpm.progettoOOP.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Period;


public class Stats {
	private double max, min;
	private Period period;
	private double value;
	private CityFileReader p;
	private String giornoMax, giornoMin;
	int contMin, contMax;

	public Stats(City city, Period period) {
		this.p = new CityFileReader(city);
		APICall call = new APICall(period, p);
		call.getData();	
		this.period = period;
	}

	public void monthStats() {
		int cont = 0;
		String month = "00";
		String Month = "00";
		double media = 0;
		double varianza;
		double somma = 0;
		double max = 0;
		double min = 30;
		int contMin = 1;
		int contMax = 1;

		APICall j = new APICall(this.period, this.p);
		JSONArray ja = new JSONArray();
		ja = j.getData();

		for(int i = 0; i<ja.size(); i++) {
			JSONObject Object = (JSONObject) ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if (max < value) { 
				max = value;
				this.max = max;
				this.giornoMax = d.getDate(); contMax = 1;}
			else {if(max == value) { 
				contMax ++; 
				this.giornoMax = giornoMax +", "+d.getDate();
			}}this.contMax = contMax;

			if (min > value) { 
				min = value;
				this.min = min;
				this.giornoMin = d.getDate(); contMin = 1;}	
			else {if(min == value) { 
				contMin ++; 
				this.giornoMin = giornoMin +", "+d.getDate();
			}}this.contMin = contMin;


			if(d.getMonth().equals(month)) {
				cont ++;
				somma += value; 
				media = somma/cont;

			}
			else {if(month.equals("01")) Month = "gennaio";
			else {if(month.equals("02")) Month = "febbraio";
			else {if(month.equals("03")) Month = "marzo";
			else {if(month.equals("04")) Month = "aprile";
			else {if(month.equals("05")) Month = "maggio";
			else {if(month.equals("06")) Month = "giugno";
			else {if(month.equals("07")) Month = "liglio";
			else {if(month.equals("08")) Month = "agosto";
			else {if(month.equals("09")) Month = "settembre";
			else {if(month.equals("10")) Month = "ottobre";
			else {if(month.equals("11")) Month = "novembre";
			else {if(month.equals("12")) Month = "dicembre";}}}}}}}}}}}

			if (month.equals("00"));
			else {System.out.println("La media nel mese di "+ Month +" è: " + media);
			varianza = getVarianza(media, month);
			System.out.println("La varianza nel mese di "+ Month +" è: " + varianza);}

			media=value; somma=value; cont=1;
			month = d.getMonth();
			}
		}//Chiusura FOR
		if(month.equals("01")) Month = "gennaio";
		else {if(month.equals("02")) Month = "febbraio";
		else {if(month.equals("03")) Month = "marzo";
		else {if(month.equals("04")) Month = "aprile";
		else {if(month.equals("05")) Month = "maggio";
		else {if(month.equals("06")) Month = "giugno";
		else {if(month.equals("07")) Month = "liglio";
		else {if(month.equals("08")) Month = "agosto";
		else {if(month.equals("09")) Month = "settembre";
		else {if(month.equals("10")) Month = "ottobre";
		else {if(month.equals("11")) Month = "novembre";
		else {if(month.equals("12")) Month = "dicembre";}}}}}}}}}}}

		System.out.println("La media nel mese di "+Month+ " è: " + media);
		varianza = getVarianza(media, month);
		System.out.println("La varianza nel mese di "+ Month +" è: " + varianza);
	}

	public void seasonStats() {}

	public double getVarianza(double media, String month) {
		double varianza0 = 0.0;
		double varianza = 0.0;
		int cont = 0;	

		APICall j = new APICall(this.period, this.p);
		JSONArray ja = new JSONArray();
		ja = j.getData();

		for(int i = 0; i<ja.size(); i++) {
			JSONObject Object = (JSONObject) ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(month.equals("00")); else {
				if(d.getMonth().equals(month)) {
					cont ++;
					varianza0 += Math.pow(value-media, 2);
					varianza = varianza0/cont;
				}}
		}//chiusura FOR
		return varianza;
	}

	public double getValue(String date) {
		APICall j = new APICall(this.period, this.p);
		JSONArray ja = new JSONArray();
		ja = j.getData();
		for(int i = 0; i<ja.size(); i++) {
			JSONObject Object = (JSONObject) ja.get(i);
			if(Object.get("date_iso").equals(date))
				this.value = (Double) Object.get("value");
		}
		return this.value;
	}


	public void getStats() {
		System.out.println(giornoMax +"   "+ max);
		System.out.println(giornoMin +"   "+ min);

		if (this.contMax > 1) getGiorniMax();
		else {
			Date d = new Date(this.giornoMax);
			System.out.println("MAX:");
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
		if (this.contMin > 1) getGiorniMin();
		else {
			Date d = new Date(this.giornoMin);
			System.out.println("MIN:");
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
	}

	public void getGiorniMax() {
		String[] g = this.giornoMax.split(", ");
		System.out.println("MAX:");
		for(int i=0; i<this.contMax; i++) {
			String dataMax = g[i];
			Date d = new Date(dataMax);
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
	}

	public void getGiorniMin() {
		String[] g = this.giornoMin.split(", ");
		System.out.println("MIN:");
		for(int i=0; i<this.contMin; i++) {
			String dataMin = g[i];
			Date d = new Date(dataMin);
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
	}
}
