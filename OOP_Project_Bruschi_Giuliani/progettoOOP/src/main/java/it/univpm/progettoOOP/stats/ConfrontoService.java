package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;
/**
 * <p>
 * <b>Interfaccia</b> che confronta le <i>statistiche</i> di un determinato <i>mese</i> con quelle dell' <i>anno</i> precedente
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public interface ConfrontoService {
	/**
	 * restituisce il numero dei giorni del mese inserito
	 * 
	 * @param month indica il mese
	 * 
	 * @return un <code>int</code> con il numero dei giorni del mese
	 */
	public abstract int end_day(int month);
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
	public abstract Vector<Double> gestioneDati(Stats s);
	/**
	 * crea il vettore con le statiche della stagione d'interesse
	 * 
	 * @param s indica un oggetto Stats
	 * 
	 * @return un <code>Vector</code> con le statistiche della stagione presa in esame
	 */
	public abstract Vector<Double> gestioneDati(SeasonStats s);
	/**
	 * ritorna un JSONArray con le statistiche del mese/stagione di due anni consecutivi
	 * 
	 * @return un <code>JSONAray</code> contenente due <code>JSONObject</code> con le statistiche del mese/stagione di due anni consecutivi
	 */
	public abstract JSONArray ConfrontoStats();
}
