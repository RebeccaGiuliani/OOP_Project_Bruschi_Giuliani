package it.univpm.progettoOOP.stats;

import java.util.Map;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

public class SeasonStats extends Stats{
	
	private int contatore  = 0;
	private double value;
	private JSONArray ja = new JSONArray();
	private Vector<Double> varianceValues = new Vector<Double>();
	private Vector<Double> mediaValues = new Vector<Double>();
	private Map<Integer, Dati> data;

	public SeasonStats(Spring s, City c) {
		super(new APICall(s, new CityFileReader(c)));
		this.ja = (new APICall(s, new CityFileReader(c))).getData();
	}

	public SeasonStats(Summer s, City c) {
		super(new APICall(s, new CityFileReader(c)));
		this.ja = (new APICall(s, new CityFileReader(c))).getData();

	}

	public SeasonStats(Autumn a, City c) {
		super(new APICall(a, new CityFileReader(c)));
		this.ja = (new APICall(a, new CityFileReader(c))).getData();
	}

	public SeasonStats(Winter w, City c) {
		super(new APICall(w, new CityFileReader(c)));
		this.ja = (new APICall(w, new CityFileReader(c))).getData();
	}

	public double seasonStats() {
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
		if(mediaSpring != 0.0) return mediaSpring;
		else if(mediaSummer != 0.0) return mediaSummer;
		else if(mediaAutumn != 0.0) return mediaAutumn;
		else return mediaWinter;
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
	
	/*public Map<Integer, Dati> DataStats(){
		for(int i=0; i<this.mediaValues.size(); i++) {
			Dati d = new Dati(getMax(i),getMin(i), mediaValues.get(i), varianceValues.get(i));
			this.data.put(i, d);
		}
		return this.data;
	}*/

}
