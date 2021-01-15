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
	private SeasonStats stats, statsp;

	public ConfrontoSeason(Spring s, City c) {
		super(s.getStart_month(), s.getStart_year(), c);
		this.stats = new SeasonStats(s,c);
		this.year = s.getStart_year();
		this.statsp = new SeasonStats(new Spring(this.year-1), c);
	}
	
	public ConfrontoSeason(Summer s, City c) {
		super(s.getStart_month(), s.getStart_year(), c);
		this.stats = new SeasonStats(s,c);
		this.year = s.getStart_year();
		this.statsp = new SeasonStats(new Summer(this.year-1), c);
	}
	
	public ConfrontoSeason(Autumn a, City c) {
		super(a.getStart_month(), a.getStart_year(), c);
		this.stats = new SeasonStats(a,c);
		this.year = a.getStart_year();
		this.statsp = new SeasonStats(new Autumn(this.year-1), c);
	}
	
	public ConfrontoSeason(Winter w, City c) {
		super(w.getStart_month(), w.getStart_year(), c);
		this.stats = new SeasonStats(w,c);
		this.year = w.getStart_year();
		this.statsp = new SeasonStats(new Winter(this.year-1), c);
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
		Vector<Double> stagioneP = gestioneDati(this.statsp);
		Vector<Double> stagione = gestioneDati(this.stats);
		
		JSONObject jop = new JSONObject();
		JSONObject jo = new JSONObject();
		JSONObject yp = new JSONObject();
		JSONObject y = new JSONObject();
	
		JSONArray ja = new JSONArray();
		
		jop.put("Media", stagioneP.get(0));
		jop.put("Varianza", stagioneP.get(1));
		jop.put("Massimo", stagioneP.get(2));	
		jop.put("Minimo", stagioneP.get(3));
		
		jo.put("Media", stagione.get(0));
		jo.put("Varianza", stagione.get(1));
		jo.put("Massimo", stagione.get(2));
		jo.put("Minimo", stagione.get(3));
		
		yp.put("Dati "+ (year-1), jop);
		y.put("Dati "+ year, jo);
		
		ja.add(yp);
		ja.add(y);
		
		return ja;
	}
}
