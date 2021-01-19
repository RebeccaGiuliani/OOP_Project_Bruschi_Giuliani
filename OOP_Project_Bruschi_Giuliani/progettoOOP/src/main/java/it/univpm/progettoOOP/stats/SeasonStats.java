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
/**
 * 
 * <p>
 * <b>Classe</b> che calcola le <i>statistiche (media, varianza, max e min)</i> di una determinata <i>stagione</i> e che implementa l'interfaccia <b>SeasonStatsService</b>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 *
 */
public class SeasonStats implements SeasonStatsService{

	private JSONArray ja = new JSONArray();
	/**
	 * trova i valori UV della città desiderata in primavera tramite una chiamata API
	 * 
	 * @param spring indica la primavera
	 * @param city indica la città
	 * 
	 * @throws WrongCityException città insrita sbagliata
	 */
	public SeasonStats(Spring spring, City city) throws WrongCityException {
		APICall call = new APICall(spring, new CityFileReader(city));
		this.ja = call.getData();
	}
	/**
	 * trova i valori UV della città desiderata in estate tramite una chiamata API
	 * 
	 * @param summer indica l'estate
	 * @param city indica la città
	 * 
	 * @throws WrongCityException città insrita sbagliata
	 */
	public SeasonStats(Summer summer, City city) throws WrongCityException {
		APICall call = new APICall(summer, new CityFileReader(city));
		this.ja = call.getData();
	}
	/**
	 * trova i valori UV della città desiderata in autuno tramite una chiamata API
	 * 
	 * @param autumn indica l'autunno
	 * @param city indica la città
	 * 
	 * @throws WrongCityException città insrita sbagliata
	 */
	public SeasonStats(Autumn autumn, City city) throws WrongCityException {
		APICall call = new APICall(autumn, new CityFileReader(city));
		this.ja = call.getData();
	}
	/**
	 * trova i valori UV della città desiderata in inverno tramite una chiamata API
	 * 
	 * @param winter indica l'inverno
	 * @param city indica la città
	 * 
	 * @throws WrongCityException città insrita sbagliata
	 */
	public SeasonStats(Winter winter, City city) throws WrongCityException {
		APICall call = new APICall(winter, new CityFileReader(city));
		this.ja = call.getData();
	}
    
	@Override
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
	
	@Override
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
	
	@Override
	public double getMax() {
		double max = 0.0;

		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			max = Math.max(max, value);
		}
		return max;
	}
	
	@Override
	public double getMin() {
		double min = 15.0;
		for(int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			double value = getValue((String) Object.get("date_iso"));
			min = Math.min(min, value);
		}
		return min;
	}
	
	@Override
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
	
	@Override
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
