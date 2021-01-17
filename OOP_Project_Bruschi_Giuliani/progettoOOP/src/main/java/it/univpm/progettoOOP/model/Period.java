package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;
/**
 * <p>
 * <b>Classe</b> che rapresenta l'oggetto <i>periodo</i>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class Period {

	protected String start_date;
	protected String end_date;
	private int start_day;
	private int start_month;
	private int start_year;
	private int end_day;
	private int end_month;
	private int end_year;	
/**
 * costruzione dell'oggetto periodo
 * 
 * @param start_day indica il giorno d'inizio
 * @param start_month indica il mese d'inizio
 * @param start_year indica l'anno d'inizio
 * @param end_day indica il giorno di fine
 * @param end_month indica il mese di fine
 * @param end_year indica l'anno di fine
 * @throws WrongPeriodException
 */
	public Period(int start_day, int start_month, int start_year, int end_day, int end_month, int end_year) throws WrongPeriodException {
		this.start_date = start_day+"-"+start_month+"-"+ start_year;
		this.end_date = end_day+"-"+end_month+"-"+end_year;
		setStart_day(start_day);
		setStart_month(start_month);
		setStart_year(start_year);
		setEnd_day(end_day);
		setEnd_month(end_month);
		setEnd_year(end_year);
	}
/**
 * ritorna la data d'inizio
 * 
 * @return una <code>String</code> che indica la data d'inizio
 */
	public String getStart() {
		return this.start_date;
	}
/**
 *  permette di scegliere la data d'inizio
 * 
 * @param start_date indica la data d'inizio
 */
	public void setStart(String start_date) {
		this.start_date = start_date;
	}
	/**
	 * ritorna la data di fine
	 * 
	 * @return una <code>String</code> che indica la data di fine
	 */
	public String getEnd() {
		return this.end_date;
	}
	/**
	 *  permette di scegliere la data di fine
	 * 
	 * @param start_date indica la data di fine
	 */
	public void setEnd(String end_date) {
		this.end_date = end_date;
	}
	/**
	 * ritorna il giorno d'inizio
	 * 
	 * @return una <code>String</code> che indica il giorno d'inizio
	 */
	public int getStart_day() {
		return start_day;
	}
	/**
	 *  permette di scegliere il giorno d'inizio
	 * 
	 * @param start_date indica il giorno d'inizio
	 */
	public void setStart_day(int start_day) throws WrongPeriodException {
		if(start_day>31 || start_day == 0)throw new WrongPeriodException();
		else this.start_day = start_day;
	}
	/**
	 * ritorna il mese d'inizio
	 * 
	 * @return una <code>String</code> che indica il mese d'inizio
	 */
	public int getStart_month() {
		return start_month;
	}
	/**
	 *  permette di scegliere il mese d'inizio
	 * 
	 * @param start_date indica il mese d'inizio
	 */
	public void setStart_month(int start_month) throws WrongPeriodException {
		if(start_month>12 || start_month == 0) throw new WrongPeriodException();
		this.start_month = start_month;
	}
	/**
	 * ritorna l'anno d'inizio
	 * 
	 * @return una <code>String</code> che indica l'anno d'inizio
	 */
	public int getStart_year() {
		return start_year;
	}
	/**
	 *  permette di scegliere l'anno d'inizio
	 * 
	 * @param start_date indica l'anno d'inizio
	 */
	public void setStart_year(int start_year) throws WrongPeriodException {
		if(start_year<2017) throw new WrongPeriodException();
		else this.start_year = start_year;
	}
	/**
	 * ritorna il giorno di fine
	 * 
	 * @return una <code>String</code> che indica il giorno di fine
	 */
	public int getEnd_day() {
		return end_day;
	}
	/**
	 *  permette di scegliere il giorno di fine
	 * 
	 * @param start_date indica il giorno di fine
	 */
	public void setEnd_day(int end_day) throws WrongPeriodException {
		if(end_day == 0 || end_day >31) throw new WrongPeriodException();
		else this.end_day = end_day;
	}
	/**
	 * ritorna il mese di fine
	 * 
	 * @return una <code>String</code> che indica il mese di fine
	 */
	public int getEnd_month() {
		return end_month;
	}
	/**
	 *  permette di scegliere il mese di fine
	 * 
	 * @param start_date indica il mese di fine
	 */
	public void setEnd_month(int end_month) throws WrongPeriodException {
		if(end_month>12 || end_month == 0 ) throw new WrongPeriodException();
		else this.end_month = end_month;
	}
	/**
	 * ritorna l'anno di fine
	 * 
	 * @return una <code>String</code> che indica l'anno di fine
	 */
	public int getEnd_year() {
		return end_year;
	}
	/**
	 *  permette di scegliere l'anno di fine
	 * 
	 * @param start_date indica l'anno di fine
	 */
	public void setEnd_year(int end_year) throws WrongPeriodException {
		
		if(end_year<2017) throw new WrongPeriodException();
		else this.end_year = end_year;
		
	}
}
