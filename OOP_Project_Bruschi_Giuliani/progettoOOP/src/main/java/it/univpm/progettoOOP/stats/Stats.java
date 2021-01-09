package it.univpm.progettoOOP.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Period;


public class Stats implements StatsService {
	private double max, min;
	private double value;
	private CityFileReader p;
	private String giornoMax, giornoMin;
	int contMin, contMax;
	JSONArray ja = new JSONArray();

	public Stats(City city, Period period) {
		this.p = new CityFileReader(city);
		APICall call = new APICall(period, p);
		this.ja = call.getData();	
	}

	public void monthStats() {
		int cont = 0;
		int year = 0;
		int month = 0;
		String Month = "00";
		double media = 0;
		double varianza;
		double somma = 0;
		double max = 0;
		double min = 30;
		int contMin = 1;
		int contMax = 1;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year){

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


				if(d.getMonth() == month) {
					cont ++;
					somma += value; 
					media = somma/cont;

				}
				else {if(month == 1) Month = "gennaio";
				else {if(month == 2) Month = "febbraio";
				else {if(month == 3) Month = "marzo";
				else {if(month == 4) Month = "aprile";
				else {if(month == 5) Month = "maggio";
				else {if(month == 6) Month = "giugno";
				else {if(month == 7) Month = "luglio";
				else {if(month == 8) Month = "agosto";
				else {if(month == 9) Month = "settembre";
				else {if(month == 10) Month = "ottobre";
				else {if(month == 11) Month = "novembre";
				else {if(month == 12) Month = "dicembre";}}}}}}}}}}}

				if (month == 0);
				else {System.out.println("La media nel mese di "+ Month +" è: " + media);
				varianza = getVarianza(media, month, year);
				System.out.println("La varianza nel mese di "+ Month +" è: " + varianza);}
				
				if(month == 1) Month = "gennaio";
				else {if(month == 2) Month = "febbraio";
				else {if(month == 3) Month = "marzo";
				else {if(month == 4) Month = "aprile";
				else {if(month == 5) Month = "maggio";
				else {if(month == 6) Month = "giugno";
				else {if(month == 7) Month = "luglio";
				else {if(month == 8) Month = "agosto";
				else {if(month == 9) Month = "settembre";
				else {if(month == 10) Month = "ottobre";
				else {if(month == 11) Month = "novembre";
				else {if(month == 12) Month = "dicembre";}}}}}}}}}}}

				media=value; somma=value; cont=1;
				month = d.getMonth();
				}
			}else { year = d.getYear(); 
			System.out.println(year);
			media=value; somma=value; cont=1;
			month = d.getMonth();
			}
		}//Chiusura FOR
		
		System.out.println("La media nel mese di "+Month+ " è: " + media);
		varianza = getVarianza(media, month, year);
		System.out.println("La varianza nel mese di "+ Month +" è: " + varianza);
	}

	public void seasonStats() {
		int contSpring = 0;
		int contSummer = 0;
		int contAutumn = 0;
		int contWinter = 0;
		double mediaSpring = 0;
		double mediaSummer = 0;
		double mediaAutumn = 0;
		double mediaWinter = 0;
		double sommaSpring = 0;
		double sommaSummer = 0;
		double sommaAutumn = 0;
		double sommaWinter = 0;
		double varianza;


		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if (d.getMonth() == 0);
			else{if(d.getMonth() >= 3 & d.getDay() >= 21 ||  
					d.getMonth() <= 6 & d.getDay() <= 21) {
				contSpring ++;
				sommaSpring += value;
				mediaSpring = sommaSpring/contSpring;	
			}
			else {if(d.getMonth() >= 6 & d.getDay() >= 22 ||
					d.getMonth() <= 9 & d.getDay() <= 22) {
				contSummer ++;
				sommaSummer += value;
				mediaSummer = sommaSummer/contSummer;
			}
			else{if(d.getMonth() >= 9 & d.getDay() >= 23 ||
					d.getMonth() <= 12 & d.getDay() <= 21) {
				contAutumn ++;
				sommaAutumn += value;
				mediaAutumn = sommaAutumn/contAutumn;	
			}
			else {if(d.getMonth() >= 12 & d.getDay() >= 22 || 
					d.getMonth() <= 3 & d.getDay() <= 20) {
				contWinter ++;
				sommaWinter += value;
				mediaWinter = sommaWinter/contWinter;
			}
			}}}}
		}//Chiusura FOR
		if(mediaSpring != 0.0) {
			System.out.println("La media in primavera è: " + mediaSpring);
			varianza = getVarianzaSeason(mediaSpring);
			System.out.println("La varianza in primavera è: " + varianza);}
		if(mediaSummer != 0.0) {
			System.out.println("La media in estate è: " + mediaSummer);
			varianza = getVarianzaSeason(mediaSummer);
			System.out.println("La varianza in estate è: " + varianza);}
		if(mediaAutumn != 0.0) {
			System.out.println("La media in autunno è: " + mediaAutumn);
			varianza = getVarianzaSeason(mediaAutumn);
			System.out.println("La varianza in autunno è: " + varianza);}
		if(mediaWinter != 0.0) {
			System.out.println("La media in inverno è: " + mediaWinter);
			varianza = getVarianzaSeason(mediaWinter);
			System.out.println("La varianza in inverno è: " + varianza);}
	}

	public double getVarianza(double media, int month, int year) {
		double varianza0 = 0.0;
		double varianza = 0.0;
		int cont = 0;	

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year) {
				if(month == 0); else {
					if(d.getMonth() == month) {
						cont ++;
						varianza0 += Math.pow(value-media, 2);
						varianza = varianza0/cont;
					}}}
		}//chiusura FOR
		return varianza;
	}

	public double getVarianzaSeason (double media) {
		double varianza0 = 0.0;
		double varianza = 0.0;
		int cont = 0;	
		
		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if (d.getMonth() == 0);
			else{if(d.getMonth() >= 3 & d.getDay() >= 21 || 
					d.getMonth() <= 6 & d.getDay() <= 21) {
				cont ++; //System.out.println("cont varianza:"+cont);
				varianza0 += Math.pow(value-media, 2); //System.out.println("varianza0: "+varianza0);
				varianza = varianza0/cont;	
			}
			else {if(d.getMonth() >= 6 & d.getDay() >= 22 || 
					d.getMonth() <= 9 & d.getDay() <= 22) {
				cont ++; //System.out.println("cont varianza:"+cont);
				varianza0 += Math.pow(value-media, 2); //System.out.println("varianza0: "+varianza0);
				varianza = varianza0/cont;	
			}
			else{if(d.getMonth() >= 9 & d.getDay() >= 23 || 
					d.getMonth() <= 12 & d.getDay() <= 21) {
				cont ++; //System.out.println("cont varianza:"+cont);
				varianza0 += Math.pow(value-media, 2); //System.out.println("varianza0: "+varianza0);
				varianza = varianza0/cont;				
			}
			else {if(d.getMonth() >= 12 & d.getDay() >= 22 || 
					d.getMonth() <= 3 & d.getDay() <= 20) {
				cont ++; //System.out.println("cont varianza:"+cont);
				varianza0 += Math.pow(value-media, 2); //System.out.println("varianza0: "+varianza0);
				varianza = varianza0/cont;	
			}
			}}}}
		}//chiusura FOR
		return varianza;
	}
	
	public double getValue(String date) {

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
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
