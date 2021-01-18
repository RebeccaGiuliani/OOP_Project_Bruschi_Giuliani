package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;
/**
 * <p>
 * <b>Classe</b> che rapresenta l'oggetto <i>anno</i> e che estende la classe <i>Period</i>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class Year extends Period{
	
	private static int start_day = 1;
	private static int start_month = 1;
	private static int end_day = 31;
	private static int end_month = 12;
	private int year;
/**
 * costruzione dell'oggetto anno
 * 
 * @param year indica l'anno
 * @throws WrongPeriodException periodo inserito sbagliato
 */
	public Year(int year) throws WrongPeriodException {
		super (start_day, start_month, year, end_day, end_month, year);
		this.year = year;
	}
	/**
	 * ritorna la data d'inizio
	 * 
	 * @return una <code>String</code> che indica la data d'inizio
	 */	
	public String getStart() {
		return super.getStart_day()+"-0"+super.getStart_month()+"-"+this.year;
	}
	/**
	 * ritorna la data di fine
	 * 
	 * @return una <code>String</code> che indica la data di fine
	 */
	public String getEnd() {
		return super.getEnd_day()+"-"+super.getEnd_month()+"-"+this.year;
	}
}
