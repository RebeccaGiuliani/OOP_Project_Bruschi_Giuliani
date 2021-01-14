package it.univpm.progettoOOP.stats;

import java.util.Collection;
import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

public class Stats implements StatsService{
	
	private JSONArray ja = new JSONArray();
	private Vector<Integer> counter= new Vector<>();
	protected Vector<Double> varianceValues = new Vector<Double>();
	protected Vector<Double> mediaValues = new Vector<Double>();
	protected Vector<Double> varianzeStagionali = new Vector<Double>();
	protected Vector<Double> maxStagionali = new Vector<>();
	protected Vector<Double> minStagionali = new Vector<>();
	
	
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
		this.mediaValues.add(somma/cont);
		this.varianceValues.add(getVarianza(somma/cont, month, year));
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

			if (d.getYear() == year) {
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
			}else {
				if(year == 0) {
					contWinter ++; sommaWinter += value; year=d.getYear();
				}else {
					contWinter ++; sommaWinter += value; year=d.getYear();
					medieStagionali.add(sommaSpring/contSpring);
					varianzeStagionali.add(getVarianzaSeason(sommaSpring/contSpring, new Spring(year)));
					maxStagionali.add(MaxSeason(new Spring(year)));
					minStagionali.add(MinSeason(new Spring(year)));
					sommaSpring = 0; contSpring = 0;
					medieStagionali.add(sommaSummer/contSummer);
					varianzeStagionali.add(getVarianzaSeason(sommaSummer/contSummer, new Summer(year)));
					maxStagionali.add(MaxSeason(new Summer(year)));
					minStagionali.add(MinSeason(new Summer(year)));
					sommaSummer = 0; contSummer = 0;
					medieStagionali.add(sommaAutumn/contAutumn);
					varianzeStagionali.add(getVarianzaSeason(sommaAutumn/contAutumn, new Autumn(year)));
					maxStagionali.add(MaxSeason(new Autumn(year)));
					minStagionali.add(MinSeason(new Autumn(year)));
					sommaAutumn = 0; contAutumn = 0;				
				}}	
			if(d.getMonth() == 3 && d.getDay() == 22) {
				medieStagionali.add(sommaWinter/contWinter);
				varianzeStagionali.add(getVarianzaSeason(sommaWinter/contWinter, new Winter(year)));
				maxStagionali.add(MaxSeason(new Winter(year)));
				minStagionali.add(MinSeason(new Winter(year)));
				sommaWinter = 0; contWinter = 0;
			}
		}//Chiusura FOR

		
		medieStagionali.add(sommaSpring/contSpring);
		varianzeStagionali.add(getVarianzaSeason(sommaSpring/contSpring, new Spring(year)));
		maxStagionali.add(MaxSeason(new Spring(year)));
		minStagionali.add(MinSeason(new Spring(year)));
		medieStagionali.add(sommaSummer/contSummer);
		varianzeStagionali.add(getVarianzaSeason(sommaSummer/contSummer, new Summer(year)));
		maxStagionali.add(MaxSeason(new Summer(year)));
		minStagionali.add(MinSeason(new Summer(year)));
		medieStagionali.add(sommaAutumn/contAutumn);
		varianzeStagionali.add(getVarianzaSeason(sommaAutumn/contAutumn, new Autumn(year)));
		maxStagionali.add(MaxSeason(new Autumn(year)));
		minStagionali.add(MinSeason(new Autumn(year)));
		medieStagionali.add(sommaWinter/contWinter);
		varianzeStagionali.add(getVarianzaSeason(sommaWinter/contWinter, new Winter(year)));
		maxStagionali.add(MaxSeason(new Winter(year)));
		minStagionali.add(MinSeason(new Winter(year)));
		
		return medieStagionali;
	}
	
	public Vector<Double> varianzaSeason() {
		return this.varianzeStagionali;
	}

	public double getVarianzaSeason (double media, Period period) {
		double varianza0 = 0.0;
		int cont = 0;	

		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() || d.getMonth() == period.getStart_month()+1 
					||d.getMonth() == period.getStart_month()+2	|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day()) {
				cont ++; 
				varianza0 += Math.pow(value-media, 2); 
			}	
		}//chiusura FOR
		return varianza0/cont;
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
		counter.add(cont);
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
		max.add(max_value);
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
		min.add(min_value);
		return min;
	}

	public Vector<Double> getMaxSeason(){
		return this.maxStagionali;
	}
	
	public Vector<Double> getMinSeason(){
		return this.minStagionali;
	}
	
	public double MaxSeason(Period period) {
		double max = 0;
		
		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() || d.getMonth() == period.getStart_month()+1 
					||d.getMonth() == period.getStart_month()+2	|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day()) {
				if(value>max) max = value;
			}	
		}
		return max;
	}
	
	public double MinSeason(Period period) {
		double min = 15.0;
		
		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() || d.getMonth() == period.getStart_month()+1 
				||d.getMonth() == period.getStart_month()+2	|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day()) {
				if(value<min) min = value;
			}
		}
		return min;
	}
	
	@SuppressWarnings("unchecked")
	public Collection<Dati> MonthlyDataStats(){
//		Map<Integer, Dati> d =new HashMap<>();
//		int cont = 0;
		JSONArray data = new JSONArray();
		for(int i=0; i<counter.size();i++){
			
			JSONObject jo = new JSONObject();
			jo.put("media", mediaValues.get(i));
			jo.put("varianza", varianceValues.get(i));
			jo.put("max", getMax().get(i));
			jo.put("min", getMin().get(i));
//			Dati da = (new Dati(mediaValues.get(i),varianceValues.get(i), getMax().get(i),getMin().get(i)));
//			d.put(cont++, da);
			data.add(jo);
		}
		
		return data;
	}
	
	
	public Vector<Dati> SeasonDataStats(){
		Vector<Dati> data = new Vector<>();
		for(int i=0; i<counter.size();i++){
			data.add(new Dati(mediaSeason().get(i),varianzeStagionali.get(i) ,maxStagionali.get(i),minStagionali.get(i)));
		}
		return data;
	}

}
