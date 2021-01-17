package it.univpm.progettoOOP.stats;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

public class SeasonStats implements SeasonStatsService{

	private JSONArray ja = new JSONArray();

	public SeasonStats(Spring spring, City city) throws WrongCityException {
		APICall call = new APICall(spring, new CityFileReader(city));
		this.ja = call.getData();
	}

	public SeasonStats(Summer summer, City city) throws WrongCityException {
		APICall call = new APICall(summer, new CityFileReader(city));
		this.ja = call.getData();
	}

	public SeasonStats(Autumn autumn, City city) throws WrongCityException {
		APICall call = new APICall(autumn, new CityFileReader(city));
		this.ja = call.getData();
	}

	public SeasonStats(Winter winter, City city) throws WrongCityException {
		APICall call = new APICall(winter, new CityFileReader(city));
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

	@SuppressWarnings("unchecked")
	public JSONObject SeasonDataStats(){
		JSONObject jo = new JSONObject();
		jo.put("Media", media());
		jo.put("Varianza", getVarianza(media()));
		jo.put("Massimo", getMax());
		jo.put("Minimo", getMin());
		return jo;
	}
}
