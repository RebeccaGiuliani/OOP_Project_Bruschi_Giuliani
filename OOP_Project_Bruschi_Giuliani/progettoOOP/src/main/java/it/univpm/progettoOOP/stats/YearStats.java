package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Year;

public class YearStats extends Stats{

	private JSONArray ja = new JSONArray();

	public YearStats (Year y, City c) {
		super(y, c);
		this.ja = new APICall (y,new CityFileReader (c)).getData();
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

		}//Chiusura FOR
		//this.m.add(media);
		//this.v.add(getVarianza(media, month, year));
		return this.mediaValues;
	}

	public double getVarianza(double media, int month, int year) {
		double varianza0 = 0.0;
		double varianza = 0.0;
		int cont = 0;	

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month == 0); else {
				if(d.getMonth() == month) {
					cont ++;
					varianza0 += Math.pow(value-media, 2);
					varianza = varianza0/cont;
				}}
		}//chiusura FOR
		return varianza;
	}
	
	public Vector<Integer> DayCounter(){
		Vector<Integer> counter= new Vector<>();
		int cont = 1;
		int month = 0;
		
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));

			if (month != 0){
				if(d.getMonth() == month) {
					cont++;
				}
				else {
						counter.add(cont);
						cont=1;
						month = d.getMonth();
					}
			}else {
				month = d.getMonth();
			}
		}
		return counter;
	}
	
	public Vector<Double> getMax() {
		int month = 0;
		Vector<Double> max = new Vector<>();
		double max_value = 0.0;
		for(int i=0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month != 0) {
					if (d.getMonth() == month) {
						if(value>max_value) max_value = value;
					}else {
						max.add(max_value);
						max_value = value;
						month = d.getMonth();
					}
				}else {
				month = d.getMonth();
			}
		}
		return max;
	}
	
	public Vector<Double> getMin() {
		int month = 0;
		Vector<Double> min = new Vector<>();
		double min_value = 15.0;
		for(int i=0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month != 0) {
					if (d.getMonth() == month) {
						if(value<min_value) min_value = value;
					}else {
						min.add(min_value);
						min_value = value;
						month = d.getMonth();
					}
				}else {
				month = d.getMonth();
			}
		}
		return min;
	}
}
