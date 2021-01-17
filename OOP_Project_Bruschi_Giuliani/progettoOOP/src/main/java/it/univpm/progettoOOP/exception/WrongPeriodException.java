package it.univpm.progettoOOP.exception;

import java.io.IOException;

/**
 *L'eccezione <b>WrongPeriodException</b> estende <b>IOException</b>.
 *Questa viene generata quando l'utente inserisce un periodo non valido.
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 * 
 */

public class WrongPeriodException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * Costruttore della classe <b>WrongPeriodException</b>
	 */
	
	public WrongPeriodException() {
		super();
	}
	
	/**
	 * Questo metodo ritorna una stringa che descrive l'errore
	 * 
	 * @return <code>String</code>
	 */
	
	public String getMex() {
		return "ERR: Il periodo inserito non è valido!";
	}
}
