package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Period;

@Service
public class Stats implements StatsService{
	
	private JSONArray ja = new JSONArray();
	private Vector<Integer> counter= new Vector<>();
	protected Vector<Double> varianceValues = new Vector<Double>();
	protected Vector<Double> mediaValues = new Vector<Double>();
	
	
	public Stats (Period p, City c) {
		APICall call = new APICall(p, new CityFileReader(c));
		this.ja = call.getData();
		this.counter = DayCounter();
	}
	

	public Vector<Double> media(){
		int cont = 0;
		int year = 0;
		int month = 0;
		double somma = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getYear() == year){
				if(d.getMonth() == month) {
					cont ++;
					somma += value; 
				}
				else {

					if (month != 0){
						mediaValues.add(somma/cont);
						varianceValues.add(getVarianza(somma/cont, month, year));
					}
					somma=value; cont=1;
					month = d.getMonth();
				}
			}else {  
				somma=value; cont=1;
				month = d.getMonth();
				year = d.getYear(); 
			}

		}//Chiusura FOR
		//this.m.add(media);
		//this.v.add(getVarianza(media, month, year));
		return this.mediaValues;
	}

	

	public Vector<Double> varianza() {
		return this.varianceValues;
	}
	
	public double getVarianza(double media, int month, int year) {
		double varianza0 = 0.0;
		double varianza = 0.0;
		int cont = 0;	

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

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
	
	public double getValue(String date) {
		double value = 0;
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			if(Object.get("date_iso").equals(date)) 
				try {
					if(Object.get("value") instanceof Long) {
						value = (Long) Object.get("value");
					}else {
						value = (Double) Object.get("value");
					}
				}catch (ClassCastException e) {
					System.out.println("catch");
				}
		}
		return value;
	}
	
	public Vector<Date> getDate(){
		Vector<Date> d= new Vector<>();
		for(int i=0; i<this.ja.size(); i++) {
			JSONObject Object =(JSONObject) this.ja.get(i);
			d.add(new Date((String) Object.get("date_iso")));
		}
		return d;
	}
	
	
	public Vector<Integer> DayCounter(){
		Vector<Integer> counter= new Vector<>();
		int cont = 1;
		int year = 0;
		int month = 0;
		
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));

			if (month != 0){
				if(d.getYear() == year){
				if(d.getMonth() == month) {
					cont++;
				}
				else {
						counter.add(cont);
						cont=1;
						month = d.getMonth();
					}
				}else year = d.getYear();
			}else { 
				year = d.getYear();
				month = d.getMonth();
			}
		}
		return counter;
	}
	
	public Vector<Double> getMax() {
		int month = 0;
		int year = 0;
		Vector<Double> max = new Vector<>();
		double max_value = 0.0;
		for(int i=0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month != 0) {
				if(d.getYear() == year) {
					if (d.getMonth() == month) {
						if(value>max_value) max_value = value;
					}else {
						max.add(max_value);
						max_value = value;
						month = d.getMonth();
					}
				}else {
					month = d.getMonth();
					year = d.getYear();
				}
			}else {
				month = d.getMonth();
				year = d.getYear();
			}
		}
		return max;
	}
	
	public Vector<Double> getMin() {
		int month = 0;
		int year = 0;
		Vector<Double> min = new Vector<>();
		double min_value = 15.0;
		for(int i=0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month != 0) {
				if(d.getYear() == year) {
					if (d.getMonth() == month) {
						if(value<min_value) min_value = value;
					}else {
						min.add(min_value);
						min_value = value;
						month = d.getMonth();
					}
				}else {
					month = d.getMonth();
					year = d.getYear();
				}
			}else {
				month = d.getMonth();
				year = d.getYear();
			}
		}
		return min;
	}

	public Vector<Dati> DataStats(){
		Vector<Dati> data = new Vector<>();
		for(int i=0; i<counter.size();i++){
			data.add(new Dati(getMax().get(i), getMin().get(i),mediaValues.get(i),varianceValues.get(i)));
		}
		return data;
	}

}
