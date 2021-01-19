package it.univpm.progettoOOP.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.Period;

/**
 *<p>
 *L'unità di test <b>WrongPeriodExceptionTest</b> è generata per testare la classe <b>WrongPeriodException</b>.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

class WrongPeriodExceptionTest {
	
	WrongPeriodException e;

	/**
	 * 
	 * @throws Exception eccezione
	 */
	
	@BeforeEach
	void setUp() throws Exception {
		
	}
	
	/**
	 * 
	 * @throws Exception eccezione
	 */
	
	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongPeriodException<b> 
	 * nel caso di inserimento di un periodo che termina prima del 22.6.2017
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Periodo Sbagliato Funzionante")
	void WrongPeriodExceptionTest1() {
		e = assertThrows(WrongPeriodException.class, () -> {
			new Period(1,1,2017,31,1,2017);
		}) ;
		
		assertEquals("ERR: Il periodo inserito non è valido!", e.getMex());
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongPeriodException<b> 
	 * nel caso di inserimento di un valore errato per il giorno di fine
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Periodo Sbagliato Funzionante")
	void WrongPeriodExceptionTest2() {
		e = assertThrows(WrongPeriodException.class, () -> {
			new Period(1,1,2019,32,1,2019);
		}) ;
		
		assertEquals("ERR: Il periodo inserito non è valido!", e.getMex());
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongPeriodException<b> 
	 * nel caso di inserimento di un valore errato per il mese di fine
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Periodo Sbagliato Funzionante")
	void WrongPeriodExceptionTest3() {
		e = assertThrows(WrongPeriodException.class, () -> {
			new Period(1,1,2019,31,13,2019);
		}) ;
		
		assertEquals("ERR: Il periodo inserito non è valido!", e.getMex());
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongPeriodException<b> 
	 * nel caso di inserimento di un valore errato per il giorno di inizio
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Periodo Sbagliato Funzionante")
	void WrongPeriodExceptionTest4() {
		e = assertThrows(WrongPeriodException.class, () -> {
			new Period(0,1,2019,31,12,2019);
		}) ;
		
		assertEquals("ERR: Il periodo inserito non è valido!", e.getMex());
	}
}
