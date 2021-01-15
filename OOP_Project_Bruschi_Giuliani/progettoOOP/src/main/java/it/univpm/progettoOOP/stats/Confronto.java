package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;

public class Confronto implements ConfrontoService {
	private int year;
	private Stats stats, statsp;
	private static int start_day = 1;

	public Confronto (int month, int year ,City c) {
		this.stats = new Stats(new Period(start_day, month, year, end_day(month), month, year),c);
		this.statsp = new Stats(new Period(start_day, month, year-1, end_day(month), month, year-1), c);
		this.year = year;
	}

	public int end_day(int month) {
		int end_day = 0;

		switch (month) {
		case 4: case 6: case 9: case 11:
			end_day = 30;
			break;

		case 1: case 3: case 5: case 7 : case 8 : case 10: case 12:
			end_day = 30;
			break;

		case 2:
			end_day = 28;
			break;	

		default:
			System.out.println("Error");
		}
		return end_day;
	}

	public Vector<Double> gestioneDati(Stats s) {
		Vector<Double> mese = new Vector<Double>();

		mese.add(s.media().get(0));
		mese.add(s.getVarianza().get(0));
		mese.add(s.getMax().get(0));
		mese.add(s.getMin().get(0));
		return mese;
	}
	
	public Vector<Double> gestioneDati(SeasonStats s){
		return new Vector<>();
	}

	@SuppressWarnings("unchecked")
	public JSONArray ConfrontoStats(){
		Vector<Double> meseP = gestioneDati(this.statsp);
		Vector<Double> mese = gestioneDati(this.stats);
		
		JSONObject jop = new JSONObject();
		JSONObject jo = new JSONObject();
		JSONObject yp = new JSONObject();
		JSONObject y = new JSONObject();
	
		JSONArray ja = new JSONArray();
		
		jop.put("Media", meseP.get(0));
		jop.put("Varianza", meseP.get(1));
		jop.put("Massimo", meseP.get(2));	
		jop.put("Minimo", meseP.get(3));
		
		jo.put("Media", mese.get(0));
		jo.put("Varianza", mese.get(1));
		jo.put("Massimo", mese.get(2));
		jo.put("Minimo", mese.get(3));
		
		yp.put("Dati "+ (year-1), jop);
		y.put("Dati "+ year, jo);
		
		ja.add(yp);
		ja.add(y);
		
		return ja;
	}
}

