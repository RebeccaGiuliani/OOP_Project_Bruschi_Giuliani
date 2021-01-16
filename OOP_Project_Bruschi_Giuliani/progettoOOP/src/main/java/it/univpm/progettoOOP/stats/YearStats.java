package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
import it.univpm.progettoOOP.model.Year;

public class YearStats extends Stats{

	private JSONArray ja = new JSONArray();

	public YearStats (Year year, City city) {
		super(year, city);
		this.ja = new APICall (year, new CityFileReader (city)).getData();
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
					varianceValues.add(varianza(somma/cont, month, year));
				}
				somma=value; cont=1;
				month = d.getMonth();
			}

		}//Chiusura FOR
		this.mediaValues.add(somma/cont);
		this.varianceValues.add(varianza(somma/cont, month, year));
		return this.mediaValues;
	}

	public double varianza(double media, int month, int year) {
		double varianza0 = 0.0;
		int cont = 0;	

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(month == 0); else {
				if(d.getMonth() == month) {
					cont ++;
					varianza0 += Math.pow(value-media, 2);
				}}
		}//chiusura FOR
		return varianza0/cont;
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
		max.add(max_value);
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
		min.add(min_value);
		return min;
	}
	
	public Vector<Double> mediaSeason() {
		int contSpring = 0;
		int contSummer = 0;
		int contAutumn = 0;
		int contWinter = 0;
		double sommaSpring = 0;
		double sommaSummer = 0;
		double sommaAutumn = 0;
		double sommaWinter = 0;
		Vector<Double> medieStagionali = new Vector<>();
		int year = 0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));
			year = d.getYear();

				if( (d.getMonth() == 3 && d.getDay() >= 21) || (d.getMonth() == 4 || d.getMonth() == 5) ||
						(d.getMonth() == 6 && d.getDay() <= 21)) {
					contSpring ++;
					sommaSpring += value;	
				}
				else {if((d.getMonth() == 6 && d.getDay() >= 22) || (d.getMonth() == 7|| d.getMonth() == 8) ||
						(d.getMonth() == 9 && d.getDay() <= 23)) {
					contSummer ++;
					sommaSummer += value;
				}
				else{if((d.getMonth() == 9 && d.getDay() >= 23) ||(d.getMonth()==10||d.getMonth() == 11)||
						(d.getMonth() == 12 && d.getDay() <= 21)) {
					contAutumn ++;
					sommaAutumn += value;
				}
				else {if((d.getMonth() == 12 && d.getDay() >= 22) || (d.getMonth() == 1||d.getMonth()==2)||
						(d.getMonth() == 3 && d.getDay() <= 20)) {
					contWinter ++;
					sommaWinter += value;
				}}}}	
			if(d.getMonth() == 3 && d.getDay() == 22) {
				medieStagionali.add(sommaWinter/contWinter);
				varianzeStagionali.add(varianzaSeason(sommaWinter/contWinter, new Winter(year)));
				maxStagionali.add(MaxSeason(new Winter(year)));
				minStagionali.add(MinSeason(new Winter(year)));
				sommaWinter = 0; contWinter = 0;
			}
		}//Chiusura FOR

		
		medieStagionali.add(sommaSpring/contSpring);
		varianzeStagionali.add(varianzaSeason(sommaSpring/contSpring, new Spring(year)));
		maxStagionali.add(MaxSeason(new Spring(year)));
		minStagionali.add(MinSeason(new Spring(year)));
		medieStagionali.add(sommaSummer/contSummer);
		varianzeStagionali.add(varianzaSeason(sommaSummer/contSummer, new Summer(year)));
		maxStagionali.add(MaxSeason(new Summer(year)));
		minStagionali.add(MinSeason(new Summer(year)));
		medieStagionali.add(sommaAutumn/contAutumn);
		varianzeStagionali.add(varianzaSeason(sommaAutumn/contAutumn, new Autumn(year)));
		maxStagionali.add(MaxSeason(new Autumn(year)));
		minStagionali.add(MinSeason(new Autumn(year)));
		medieStagionali.add(sommaWinter/contWinter);
		varianzeStagionali.add(varianzaSeason(sommaWinter/contWinter, new Winter(year)));
		maxStagionali.add(MaxSeason(new Winter(year)));
		minStagionali.add(MinSeason(new Winter(year)));
		return medieStagionali;
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
		counter.add(cont);
		return counter;
	}
	

	
}
