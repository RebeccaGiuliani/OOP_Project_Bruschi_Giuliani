package it.univpm.progettoOOP.stats;

import org.json.simple.JSONObject;
/**
 * 
 * <p>
 * <b>Interfaccia</b> che calcola le <i>statistiche (media, varianza, max e min)</i> di una determinata <i>stagione</i> e che implementa l'interfaccia <b>SeasonStatsService</b>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 *
 */
public interface SeasonStatsService {
	/**
     * calcola la media dei valori UV della stagione d'interesse
     * 
     * @see #getValue
     * 
     * @return un <code>double</code> che indica la media di una stagione
     */
	public abstract double media();
	/**
	 * calcola la varianza dei valori UV della stagione d'interesse
	 * 
	 * @param media indica la media stagionale
	 * 
	 * @see #getValue
	 * 
	 * @return un <code>double</code> che indica la varianza di una stagione
	 */
	public abstract double getVarianza(double media);
	/**
	 * calcola il massimo dei valori UV della stagione d'interesse
	 * 
	 * @see #getValue
	 * 
	 * @return un <code>double</code> che indica il massimo di una stagione
	 */
	public abstract double getMax();
	/**
	 * calcola il minimo dei valori UV della stagione d'interesse
	 * 
	 * @see #getValue
	 * 
	 * @return un <code>double</code> che indica il minimo di una stagione
	 */
	public abstract double getMin();
	/**
	 * prende il valore UV di un determinato giorno dal JSONArray
	 * 
	 * @param date indica la data
	 * 
	 * @return un <code>double</code> con il valore UV
	 */
	public abstract double getValue(String date);
	/**
	 * ritorna le statistiche della stagione 
	 * @see #media
	 * @see #getVarianza
	 * @see #getMax 
	 * @see #getMin 
	 * 
	 * @return un <code>JSONObject</code> con le statistiche
	 */
	public abstract JSONObject SeasonDataStats();

}
