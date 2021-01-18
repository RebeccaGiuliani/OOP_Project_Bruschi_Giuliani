package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.filter.APICall;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Date;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
/**
 * 
 * <p>
 * <b>Classe</b> che calcola le <i>statistiche (media, varianza, max e min)</i> di un determinato <i>periodo</i> e che implementa l'interfaccia <b>StatsService</b>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 *
 */
public class Stats implements StatsService{

	private JSONArray ja = new JSONArray();
	protected Vector<Double> varianceValues = new Vector<Double>();
	protected Vector<Double> mediaValues = new Vector<Double>();
	protected Vector<Double> varianzeStagionali = new Vector<Double>();
	protected Vector<Double> maxStagionali = new Vector<>();
	protected Vector<Double> minStagionali = new Vector<>();
/**
 * trova i valori UV della città desiderata in un determinato periodo tramite una chiamata API
 * 
 * @param period indica il periodo
 * @param city indica la città
 * @throws WrongCityException
 */
	public Stats (Period period, City city) throws WrongCityException {
		APICall call = new APICall(period, new CityFileReader(city));
		this.ja = call.getData();
	}

	@Override
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
						varianceValues.add(varianza(somma/cont, month, year));
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
		this.varianceValues.add(varianza(somma/cont, month, year));
		return this.mediaValues;
	}

	@Override
	public double varianza(double media, int month, int year) {
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

	@Override
	public Vector<Double> getVarianza() {
		return this.varianceValues;
	}

	@Override
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

	@Override
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
	
	@Override
	public Vector<Double> mediaSeason() throws WrongPeriodException {
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
						(d.getMonth() == 6 && d.getDay() <= 20)) {
					contSpring ++;
					sommaSpring += value;	
				}
				else {if((d.getMonth() == 6 && d.getDay() >= 21) || (d.getMonth() == 7|| d.getMonth() == 8) ||
						(d.getMonth() == 9 && d.getDay() <= 22)) {
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
					varianzeStagionali.add(varianzaSeason(sommaSpring/contSpring, new Spring(year)));
					maxStagionali.add(MaxSeason(new Spring(year)));
					minStagionali.add(MinSeason(new Spring(year)));
					sommaSpring = 0; contSpring = 0;
					medieStagionali.add(sommaSummer/contSummer);
					varianzeStagionali.add(varianzaSeason(sommaSummer/contSummer, new Summer(year)));
					maxStagionali.add(MaxSeason(new Summer(year)));
					minStagionali.add(MinSeason(new Summer(year)));
					sommaSummer = 0; contSummer = 0;
					medieStagionali.add(sommaAutumn/contAutumn);
					varianzeStagionali.add(varianzaSeason(sommaAutumn/contAutumn, new Autumn(year)));
					maxStagionali.add(MaxSeason(new Autumn(year)));
					minStagionali.add(MinSeason(new Autumn(year)));
					sommaAutumn = 0; contAutumn = 0;				
				}}	
			if(d.getMonth() == 3 && d.getDay() == 20) {
				medieStagionali.add(sommaWinter/contWinter);
				varianzeStagionali.add(varianzaSeason(sommaWinter/contWinter, new Winter(year-1)));
				maxStagionali.add(MaxSeason(new Winter(year-1)));
				minStagionali.add(MinSeason (new Winter(year-1)));
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
	
	@Override
	public double varianzaSeason (double media, Period period) {
		double varianza0 = 0.0;
		int cont = 0;	

		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() && d.getYear() == period.getStart_year() 
					|| d.getMonth() == period.getEnd_month()-2 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month()-1 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day() && d.getYear() == period.getEnd_year()) {
				cont ++; 
				varianza0 += Math.pow(value-media, 2); 
			}	
		}//chiusura FOR
		return varianza0/cont;
	}
	
	@Override
	public Vector<Double> getVarianzaSeason() {
		return this.varianzeStagionali;
	}
	
	@Override
	public double MaxSeason(Period period) {
		double max = 0;

		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() && d.getYear() == period.getStart_year() 
					|| d.getMonth() == period.getEnd_month()-2 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month()-1 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day() && d.getYear() == period.getEnd_year()) {
				if(value>max)   max = value; 
			}	
		}
		return max;
	}	
	
	@Override
	public Vector<Double> getMaxSeason(){
		return this.maxStagionali;
	}
	
	@Override
	public double MinSeason(Period period) {
		double min = 15.0;

		for (int i = 0; i<this.ja.size(); i++) {
			JSONObject Object = (JSONObject) this.ja.get(i);
			Date d = new Date((String) Object.get("date_iso"));
			double value = getValue((String) Object.get("date_iso"));

			if(d.getMonth() == period.getStart_month() && d.getDay() >= period.getStart_day() && d.getYear() == period.getStart_year() 
					|| d.getMonth() == period.getEnd_month()-2 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month()-1 && d.getYear() == period.getEnd_year() 
					|| d.getMonth() == period.getEnd_month() && d.getDay() <= period.getEnd_day() && d.getYear() == period.getEnd_year()) {
				if(value<min)  min = value; 
			}
		}
		return min;
	}

	@Override
	public Vector<Double> getMinSeason(){
		return this.minStagionali;
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

	@Override
	@SuppressWarnings("unchecked")
	public JSONArray MonthlyDataStats(){
		JSONArray ja = new JSONArray();
		Vector<Integer> counter = DayCounter();
		for(int i=0; i<counter.size();i++){
			JSONObject jo = new JSONObject();
			String nome = (i+1)+"° mese";
			jo.put("Dati", nome);
			jo.put("Media", media().get(i));
			jo.put("Varianza", getVarianza().get(i));
			jo.put("Max", getMax().get(i));
			jo.put("Min", getMin().get(i));
			ja.add(jo);
		}

		return ja;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public JSONArray SeasonDataStats() throws WrongPeriodException{
		JSONArray ja = new JSONArray();
		Vector<Double> medieStagionali = mediaSeason();
		for(int i=0; i<medieStagionali.size();i++){
			JSONObject jo = new JSONObject();
			jo.put("Media stagionale", mediaSeason().get(i));
			jo.put("Varianza stagionale", getVarianzaSeason().get(i));
			jo.put("Massimo stagionale", getMaxSeason().get(i));
			jo.put("Minimo stagionale", getMinSeason().get(i));
			ja.add(jo);
		}
		return ja;
	}

}
