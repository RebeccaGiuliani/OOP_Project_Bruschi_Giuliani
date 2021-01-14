package it.univpm.progettoOOP.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Dati;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

public class SeasonStats implements SeasonStatsService{
	
	private JSONArray ja = new JSONArray();

	public SeasonStats(Spring s, City c) {
		APICall call = new APICall(s, new CityFileReader(c));
		this.ja = call.getData();
	}

	public SeasonStats(Summer s, City c) {
		APICall call = new APICall(s, new CityFileReader(c));
		this.ja = call.getData();
	}

	public SeasonStats(Autumn a, City c) {
		APICall call = new APICall(a, new CityFileReader(c));
		this.ja = call.getData();
	}

	public SeasonStats(Winter w, City c) {
		APICall call = new APICall(w, new CityFileReader(c));
		this.ja = call.getData();
	}

	public double media() {
		double somma = 0;
		int cont = 0;
		
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			cont++;
			somma += value;
		}
		return somma/cont;
	}
	
	public double getVarianza(double media) {
		double varianza0 = 0.0;
		int cont = 0;	

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			
			cont ++;
			varianza0 += Math.pow(value-media, 2);

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
	
	public double getMax() {
		double max = 0.0;
		
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			if(value>max) max = value;
		}
		return max;
	}
	
	public double getMin() {
		double min = 15.0;
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			if(value<min) min = value;
		}
		return min;
		}
	
	public Dati SeasonDataStats(){
		Dati data = new Dati(media(), getVarianza(media()), getMax(), getMin());
		return data;
	}
}
