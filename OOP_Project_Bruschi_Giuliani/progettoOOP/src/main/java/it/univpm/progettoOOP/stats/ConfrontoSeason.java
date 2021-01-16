package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;

public class ConfrontoSeason extends Confronto {
	private int year;
	private SeasonStats stats, stats_prec;

	public ConfrontoSeason(Spring spring, City city) {
		super(spring.getStart_month(), spring.getStart_year(), city);
		this.stats = new SeasonStats(spring, city);
		this.year = spring.getStart_year();
		this.stats_prec = new SeasonStats(new Spring(this.year-1), city);
	}
	
	public ConfrontoSeason(Summer summer, City city) {
		super(summer.getStart_month(), summer.getStart_year(), city);
		this.stats = new SeasonStats(summer, city);
		this.year = summer.getStart_year();
		this.stats_prec = new SeasonStats(new Summer(this.year-1), city);
	}
	
	public ConfrontoSeason(Autumn autumn, City city) {
		super(autumn.getStart_month(), autumn.getStart_year(), city);
		this.stats = new SeasonStats(autumn, city);
		this.year = autumn.getStart_year();
		this.stats_prec = new SeasonStats(new Autumn(this.year-1), city);
	}
	
	public ConfrontoSeason(Winter winter, City city) {
		super(winter.getStart_month(), winter.getStart_year(), city);
		this.stats = new SeasonStats(winter, city);
		this.year = winter.getStart_year();
		this.stats_prec = new SeasonStats(new Winter(this.year-1), city);
	}
	
	public Vector<Double> gestioneDati(SeasonStats s) {
		Vector<Double> stagione = new Vector<Double>();

		stagione.add(s.media());
		stagione.add(s.getVarianza(s.media()));
		stagione.add(s.getMax());
		stagione.add(s.getMin());
		
		return stagione;
	}
	
	@SuppressWarnings("unchecked")
	public JSONArray ConfrontoStats(){
		Vector<Double> stagione_prec = gestioneDati(this.stats_prec);
		Vector<Double> stagione = gestioneDati(this.stats);
		
		JSONObject jo_prec = new JSONObject();
		JSONObject jo = new JSONObject();
		JSONObject y_prec = new JSONObject();
		JSONObject y = new JSONObject();
	
		JSONArray ja = new JSONArray();
		
		jo_prec.put("Media", stagione_prec.get(0));
		jo_prec.put("Varianza", stagione_prec.get(1));
		jo_prec.put("Massimo", stagione_prec.get(2));	
		jo_prec.put("Minimo", stagione_prec.get(3));
		
		jo.put("Media", stagione.get(0));
		jo.put("Varianza", stagione.get(1));
		jo.put("Massimo", stagione.get(2));
		jo.put("Minimo", stagione.get(3));
		
		y_prec.put("Dati "+ (year-1), jo_prec);
		y.put("Dati "+ year, jo);
		
		ja.add(y_prec);
		ja.add(y);
		
		return ja;
	}
}
