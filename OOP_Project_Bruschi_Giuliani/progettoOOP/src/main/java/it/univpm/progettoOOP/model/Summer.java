package it.univpm.progettoOOP.model;

import it.univpm.progettoOOP.exception.WrongPeriodException;
/**
 * <p>
 * <b>Classe</b> che rapresenta l'oggetto <i>estate</i> e che estende la classe <i>Period</i>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class Summer extends Period{

	private static int start_day = 21;
	private static int start_month = 6;
	private static int end_day = 22;
	private static int end_month = 9;
	private int year;
	/**
	 * costruzione dell'oggetto estate
	 * 
	 * @param year indica l'anno
	 * @throws WrongPeriodException
	 */	
	public Summer (int year) throws WrongPeriodException {
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
		return super.getEnd_day()+"-0"+super.getEnd_month()+"-"+this.year;
	}

}
