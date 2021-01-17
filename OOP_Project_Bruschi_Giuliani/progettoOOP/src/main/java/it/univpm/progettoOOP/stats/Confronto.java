package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
/**
 * <p>
 * <b>Classe</b> che confronta le <i>statistiche</i> di un determinato <i>mese</i> con quelle dell' <i>anno</i> precedente
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class Confronto implements ConfrontoService {
	private int year;
	private Stats stats, stats_prec;
	private static int start_day = 1;
/**
 * richiama le statistiche del mese nell'anno scelto e in quello precedente
 * 
 * @param month indica il mese
 * @param year indica l'anno
 * @param city indica la città
 * 
 * @throws WrongPeriodException
 * @throws WrongCityException
 */
	public Confronto (int month, int year ,City city) throws WrongPeriodException, WrongCityException {
		this.stats = new Stats(new Period(start_day, month, year, end_day(month), month, year),city);
		this.stats_prec = new Stats(new Period(start_day, month, year-1, end_day(month), month, year-1), city);
		this.year = year;
	}
/**
 * restituisce il numero dei giorni del mese inserito
 * 
 * @param month indica il mese
 * 
 * @return un <code>int</code> con il numero dei giorni del mese
 */
	public int end_day(int month) {
		int end_day = 0;

		switch (month) {
		case 4: case 6: case 9: case 11:
			end_day = 30;
			break;

		case 1: case 3: case 5: case 7 : case 8 : case 10: case 12:
			end_day = 31;
			break;

		case 2:
			end_day = 28;
			break;	

		default:
			System.out.println("Error");
		}
		return end_day;
	}
/**
 * crea il vettore con le statiche del mese d'interesse
 * 
 * @param s indica un oggetto Stats
 * 
 * @see Stats#media 
 * @see Stats#getVarianza
 * @see Stats#getMax
 * @see Stats#getMin 
 * 
 * @return un <code>Vector</code> con le statistiche del mese preso in esame
 */
	public Vector<Double> gestioneDati(Stats s) {
		Vector<Double> mese = new Vector<Double>();

		mese.add(s.media().get(0));
		mese.add(s.getVarianza().get(0));
		mese.add(s.getMax().get(0));
		mese.add(s.getMin().get(0));
		return mese;
	}
/**
 * crea il vettore con le statiche della stagione d'interesse
 * 
 * @param s indica un oggetto Stats
 * 
 * @return un <code>Vector</code> con le statistiche della stagione presa in esame
 */
	public Vector<Double> gestioneDati(SeasonStats s){
		return new Vector<>();
	}
/**
 * ritorna un JSONArray con le statistiche del mese/stagione di due anni consecutivi
 * 
 * @return un <code>JSONAray</code> contenente due <code>JSONObject</code> con le statistiche del mese/stagione di due anni consecutivi
 */
	@SuppressWarnings("unchecked")
	public JSONArray ConfrontoStats(){
		Vector<Double> mese_prec = gestioneDati(this.stats_prec);
		Vector<Double> mese = gestioneDati(this.stats);
		
		JSONObject jo_prec = new JSONObject();
		JSONObject jo = new JSONObject();
		JSONObject y_prec = new JSONObject();
		JSONObject y = new JSONObject();
	
		JSONArray ja = new JSONArray();
		
		jo_prec.put("Media", mese_prec.get(0));
		jo_prec.put("Varianza", mese_prec.get(1));
		jo_prec.put("Massimo", mese_prec.get(2));	
		jo_prec.put("Minimo", mese_prec.get(3));
		
		jo.put("Media", mese.get(0));
		jo.put("Varianza", mese.get(1));
		jo.put("Massimo", mese.get(2));
		jo.put("Minimo", mese.get(3));
		
		y_prec.put("Dati "+ (year-1), jo_prec);
		y.put("Dati "+ year, jo);
		
		ja.add(y_prec);
		ja.add(y);
		
		return ja;
	}
}

