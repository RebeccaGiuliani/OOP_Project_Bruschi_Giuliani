package it.univpm.progettoOOP.exception;

import java.io.IOException;

/**
 *L'eccezione <b>WrongCityException</b> estende <b>IOException</b>.
 *Questa viene generata quando l'utente inserisce una città non presente nel file 
 *oppure quando inserisce il nome della città o la sigla dello stato in modo erroneo.
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 * 
 */

public class WrongCityException extends IOException{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Costruttore della classe <b>WrongCityException</b>
	 */
	
	public WrongCityException() {
		super();
	}

	/**
	 * Questo metodo ritorna una stringa che descrive l'errore
	 * 
	 * @return <code>String</code>
	 */
	
	public String getMex() {
		return "ERR: La città o lo stato inseriti non sono validi!";
	}
}
