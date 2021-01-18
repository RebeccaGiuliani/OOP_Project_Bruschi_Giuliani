package it.univpm.progettoOOP.stats;

import java.util.Vector;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Period;
/**
 * 
 * <p>
 * <b>Ibterfaccia</b> che calcola le <i>statistiche (media, varianza, max e min)</i> di un determinato <i>periodo</i> e che implementa l'interfaccia <b>StatsService</b>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 *
 */
public interface StatsService {
	/**
	 * crea un vettore con le mediedie dei mesi nel periodo preso in esame
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * @see #varianza
	 * 
	 * @return un <code>Vector</code> con le medie mensili 
	 */
	public abstract Vector<Double> media();
	/**
	 * ritorna la varianza del mese inserito
	 * 
	 * @param media indica la media del mese
	 * @param month indica il mese
	 * @param year indica l'anno
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * 
	 * @return un <code>double</code> che indica la varianza
	 */
	public abstract double varianza(double media, int month, int year);
	/**
	 * ritorna un vettore con la varianza dei vari mesi
	 * 
	 * @return un <code>Vector</code> con la varianza dei vari mesi
	 */
	public abstract Vector<Double> getVarianza();
	/**
	 * trova il valore massimo per ogni mese
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * 
	 * @return un <code>Vector</code> con i valori massimi di ogni mese
	 */
	public abstract Vector<Double> getMax();
	/**
	 * trova il valore minimo per ogni mese
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * 
	 * @return un <code>Vector</code> con i valori minimi di ogni mese
	 */
	public abstract Vector<Double> getMin();
	/**
	 * crea un vettore con le medie delle stagioni nel periodo preso in esame
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * @see it.univpm.progettoOOP.model.Date#getDay
	 * @see #varianzaSeason
	 * @see #MaxSeason
	 * @see #MinSeason
	 * 
	 * @return un <code>Vector</code> con le medie stagionali 
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato
	 */
	public abstract Vector<Double> mediaSeason() throws WrongPeriodException;
	/**
	 * ritorna la varianza della stagione inserita
	 * 
	 * @param media indica la media del mese
	 * @param period indica la stagione
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * @see it.univpm.progettoOOP.model.Date#getDay
	 * @see it.univpm.progettoOOP.model.Period#getEnd_month
	 * @see it.univpm.progettoOOP.model.Period#getEnd_year
	 * @see it.univpm.progettoOOP.model.Period#getEnd_day
	 * @see it.univpm.progettoOOP.model.Period#getStart_month
	 * @see it.univpm.progettoOOP.model.Period#getStart_year
	 * @see it.univpm.progettoOOP.model.Period#getStart_day
	 * 
	 * @return un <code>double</code> che indica la varianza
	 */
	public abstract double varianzaSeason (double media, Period period);
	/**
	 * ritorna un vettore con la varianza delle varie stagioni
	 * 
	 * @return un <code>Vector</code> con la varianza delle varie stagioni
	 */
	public abstract Vector<Double> getVarianzaSeason();
	/**
	 * trova il valore massimo della stagione inserita
	 * 
	 * @param period indica il periodo
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * @see it.univpm.progettoOOP.model.Date#getDay
	 * @see it.univpm.progettoOOP.model.Period#getEnd_month
	 * @see it.univpm.progettoOOP.model.Period#getEnd_year
	 * @see it.univpm.progettoOOP.model.Period#getEnd_day
	 * @see it.univpm.progettoOOP.model.Period#getStart_month
	 * @see it.univpm.progettoOOP.model.Period#getStart_year
	 * @see it.univpm.progettoOOP.model.Period#getStart_day
	 * 
	 * @return un <code>double</code> con i valori massimi di una stagione
	 */
	public abstract double MaxSeason(Period period);
	/**
	 * ritorna il valore massimo per ogni mese
	 * 
	 * @return un <code>Vector</code> con i valori massimi di ogni mese
	 */
	public abstract Vector<Double> getMaxSeason();
	/**
	 * trova il valore minimo della stagione inserita
	 * 
	 * @param period indica il periodo
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * @see it.univpm.progettoOOP.model.Date#getDay
	 * @see it.univpm.progettoOOP.model.Period#getEnd_month
	 * @see it.univpm.progettoOOP.model.Period#getEnd_year
	 * @see it.univpm.progettoOOP.model.Period#getEnd_day
	 * @see it.univpm.progettoOOP.model.Period#getStart_month
	 * @see it.univpm.progettoOOP.model.Period#getStart_year
	 * @see it.univpm.progettoOOP.model.Period#getStart_day
	 * 
	 * @return un <code>double</code> con i valori minimi di una stagione
	 */
	public abstract double MinSeason(Period period);
	/**
	 * ritorna il valore minimo per ogni mese
	 * 
	 * @return un <code>Vector</code> con i valori minimi di ogni mese
	 */
	public abstract Vector<Double> getMinSeason();
	/**
	 * prende il valore UV di un determinato giorno dal JSONArray
	 * 
	 * @param date indica la data
	 * 
	 * @return un <code>double</code> con il valore UV
	 */
	public abstract double getValue(String date);
	/**
	 * conta i giorni di ogni mese
	 * 
	 * @see it.univpm.progettoOOP.model.Date#getMonth
	 * @see it.univpm.progettoOOP.model.Date#getYear
	 * 
	 * @return un <code>Vector</code> con il numero dei giorni dei vari mesi
	 */
	public abstract Vector<Integer> DayCounter();
	/**
	 * ritorna le statistiche mensili
	 * @see #media
	 * @see #getVarianza
	 * @see #getMax 
	 * @see #getMin 
	 * 
	 * @return un <code>JSONArray</code> composto da <code>JSONObject</code> con le statistiche mensili
	 */
	public abstract JSONArray MonthlyDataStats();
	/**
	 * ritorna le statistiche stagionali
	 * @see #mediaSeason
	 * @see #getVarianzaSeason
	 * @see #getMaxSeason 
	 * @see #getMinSeason
	 * 
	 * @return un <code>JSONArray</code> composto da <code>JSONObject</code> con le statistiche stagionali
	 * 
	 * @throws WrongPeriodException periodo inserito sbagliato
	 */
	public abstract JSONArray SeasonDataStats() throws WrongPeriodException;
	
}
