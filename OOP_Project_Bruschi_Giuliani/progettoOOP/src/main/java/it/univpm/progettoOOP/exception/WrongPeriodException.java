package it.univpm.progettoOOP.exception;

import java.io.IOException;

public class WrongPeriodException extends IOException {

	private static final long serialVersionUID = 1L;

	/**
	 * constructor
	 */
	
	public WrongPeriodException() {
		super();
	}
	
	public String getMex() {
		return "ERR: Il periodo inserito non è valido!";
	}
}
