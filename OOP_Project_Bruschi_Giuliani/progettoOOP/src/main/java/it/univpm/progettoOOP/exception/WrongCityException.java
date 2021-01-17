package it.univpm.progettoOOP.exception;

public class WrongCityException extends Exception{

	private static final long serialVersionUID = 1L;
	
	/**
	 * constructor
	 */
	
	public WrongCityException() {
		super();
	}

	public String getMex() {
		return "ERR: La città o lo stato inseriti non sono validi!";
	}
}
