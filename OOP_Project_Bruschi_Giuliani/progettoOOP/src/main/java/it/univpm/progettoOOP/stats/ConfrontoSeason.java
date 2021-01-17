package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Autumn;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Spring;
import it.univpm.progettoOOP.model.Summer;
import it.univpm.progettoOOP.model.Winter;
/**
 * <p>
 * <b>Classe</b> che confronta le <i>statistiche</i> di una determinata <i>stagione</i> con quelle dell' <i>anno</i> precedente
 * e che estende <b>Confronto</b>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class ConfrontoSeason extends Confronto {
	private int year;
	private SeasonStats stats, stats_prec;
	/**
	 * richiama le statistiche della primavera nell'anno scelto e in quello precedente
	 * 
	 * @param spring indica la primavera 
	 * @param city indica la città
	 * 
	 * @throws WrongPeriodException
	 * @throws WrongCityException
	 */
	public ConfrontoSeason(Spring spring, City city) throws WrongPeriodException, WrongCityException {
		super(spring.getStart_month(), spring.getStart_year(), city);
		this.stats = new SeasonStats(spring, city);
		this.year = spring.getStart_year();
		this.stats_prec = new SeasonStats(new Spring(this.year-1), city);
	}
	/**
	 * richiama le statistiche dell'estate nell'anno scelto e in quello precedente
	 * 
	 * @param summer indica l'estate
	 * @param city indica la città
	 * 
	 * @throws WrongPeriodException
	 * @throws WrongCityException
	 */
	public ConfrontoSeason(Summer summer, City city) throws WrongPeriodException, WrongCityException {
		super(summer.getStart_month(), summer.getStart_year(), city);
		this.stats = new SeasonStats(summer, city);
		this.year = summer.getStart_year();
		this.stats_prec = new SeasonStats(new Summer(this.year-1), city);
	}
	/**
	 * richiama le statistiche dell'autunno nell'anno scelto e in quello precedente
	 * 
	 * @param autumn indica l'autunno
	 * @param city indica la città
	 * 
	 * @throws WrongPeriodException
	 * @throws WrongCityException
	 */	
	public ConfrontoSeason(Autumn autumn, City city) throws WrongPeriodException, WrongCityException {
		super(autumn.getStart_month(), autumn.getStart_year(), city);
		this.stats = new SeasonStats(autumn, city);
		this.year = autumn.getStart_year();
		this.stats_prec = new SeasonStats(new Autumn(this.year-1), city);
	}
	/**
	 * richiama le statistiche dell'inverno nell'anno scelto e in quello precedente
	 * 
	 * @param winter indica l'inverno 
	 * @param city indica la città
	 * 
	 * @throws WrongPeriodException
	 * @throws WrongCityException
	 */
	public ConfrontoSeason(Winter winter, City city) throws WrongPeriodException, WrongCityException {
		super(winter.getStart_month(), winter.getStart_year(), city);
		this.stats = new SeasonStats(winter, city);
		this.year = winter.getStart_year();
		this.stats_prec = new SeasonStats(new Winter(this.year-1), city);
	}
	/**
	 * crea il vettore con le statiche della stagione d'interesse
	 * 
	 * @param s indica un oggetto Stats
	 * 
	 * @see Stats#media 
	 * @see Stats#getVarianza
	 * @see Stats#getMax
	 * @see Stats#getMin 
	 * 
	 * @return un <code>Vector</code> con le statistiche della stagione presa in esame
	 */	
	public Vector<Double> gestioneDati(SeasonStats s) {
		Vector<Double> stagione = new Vector<Double>();

		stagione.add(s.media());
		stagione.add(s.getVarianza(s.media()));
		stagione.add(s.getMax());
		stagione.add(s.getMin());
		
		return stagione;
	}
	/**
	 * ritorna un JSONArray con le statistiche della stagione di due anni consecutivi
	 * 
	 * @return un <code>JSONAray</code> contenente due <code>JSONObject</code> con le statistiche della stagione di due anni consecutivi
	 */	
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
