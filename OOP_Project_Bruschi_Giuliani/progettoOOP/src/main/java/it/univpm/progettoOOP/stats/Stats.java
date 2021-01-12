package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.model.Date;

public class Stats implements StatsService {
	private double value;
	private int cont;
	private String giorno = "";
	Vector<Double> v = new Vector<Double>();

	JSONArray ja = new JSONArray();

	public Stats(APICall call) {
		this.ja = call.getData();	
	}

	public Vector<Double> monthStats() {
		Vector<Double> m = new Vector<Double>();
		Vector<Double> v = new Vector<Double>();
		int cont = 0;
		int year = 0;
		int month = 0;
		double media = 0;
		double somma = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year){
				if(d.getMonth() == month) {
					cont ++;
					somma += value; 
					media = somma/cont;
				}
				else {

				if (month != 0){
					m.add(media);
					v.add(getVarianza(media, month, year));
				}

				media=value; somma=value; cont=1;
				month = d.getMonth();
				}
			}else { year = d.getYear(); 
			media=value; somma=value; cont=1;
			month = d.getMonth();
			}
		}//Chiusura FOR
		m.add(media);
		v.add(getVarianza(media, month, year));
		this.v = v;
		return m;
	}

	public Vector<Double> varianza() {
		return this.v;
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

	public double getMax() {
		String giornoMax = "";
		double max = 0;
		int contMax = 1;
		int year = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year){ 
				if (max < value) { 
					max = value;
					giornoMax = d.getDate(); contMax = 1;}
				else {if(max == value) { 
					contMax ++; 
					giornoMax = giornoMax +", "+d.getDate();
				}}
			}else { year = d.getYear(); 
			System.out.println(year);}}
		this.giorno = giornoMax; this.cont = contMax;
		getGiorno(/*contMax, giornoMax*/);	
		return max;
	}

	public double getMin() {
		String giornoMin = "";
		double min = 30;
		int contMin = 1;
		int year = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year){
				if (min > value) { 
					min = value;
					giornoMin = d.getDate(); contMin = 1;}	
				else {if(min == value) { 
					contMin ++; 
					giornoMin = giornoMin +", "+d.getDate();
				}}
			}else { year = d.getYear(); 
			System.out.println(year);}}
		this.giorno = giornoMin; this.cont = contMin;
		getGiorno();
		return min;
	}

	public Vector<String> getGiorno() {
		Vector<String> v = new Vector<String>();
		if (this.cont > 1) getGiorni();
		else {
			Date d = new Date(giorno);
			v.add(giorno);
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
		return v;
	}
	
	public Vector<String> getGiorni() {
		Vector<String> v = new Vector<String>();
		String[] g = giorno.split(", ");
		for(int i=0; i<this.cont; i++) {
			String data = g[i];
			Date d = new Date(data);
			v.add(data);
			System.out.println("DATA: "+d.getDate());
			System.out.println("DAY: "+d.getDay());
			System.out.println("MONTH: "+d.getMonth());
			System.out.println("YEAR: "+d.getYear());
		}
		return v;
	}

}
