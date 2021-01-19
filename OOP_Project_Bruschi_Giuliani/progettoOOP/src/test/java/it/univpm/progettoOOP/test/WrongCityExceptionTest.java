package it.univpm.progettoOOP.test;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;

/**
 *<p>
 *L'unità di test <b>WrongCityExceptionTest</b> è generata per testare la classe <b>WrongCityException</b>.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

class WrongCityExceptionTest {
	
	WrongCityException e;
	CityFileReader c1,c2,c3;
	JSONArray ja;
	
	/**
	 * 
	 * @throws Exception eccezione
	 */
	
	@BeforeEach
	void setUp() throws Exception {
		c1 = new CityFileReader(new City("Ancon", "IT"));
		c2 = new CityFileReader(new City("Ancona", "I"));
		c3 = new CityFileReader(new City("Roma", "IT"));
		ja = new JSONArray();
	}
	
	/**
	 * 
	 * @throws Exception eccezione
	 */
	
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * test per la generazione dell'eccezione <b>WrongCityException<b> 
	 * nel caso di inserimento del nome della città in modo errato
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Città Sbagliata Funzionante")
	void WrongCityExceptionTest1() {
		
		ja = c1.caricaArray();
		
		e = assertThrows(WrongCityException.class, () -> {
			c1.getCity(ja);;
		}) ;
		
		assertEquals("ERR: La città o lo stato inseriti non sono validi!", e.getMex());
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongCityException<b> 
	 * nel caso di inserimento della sigla dello stato in modo errato
	 * 
	 */
	
	@Test
	@DisplayName("Eccezione Città Sbagliata Funzionante")
	void WrongCityExceptionTest2() {
		
		ja = c2.caricaArray();
		
		e = assertThrows(WrongCityException.class, () -> {
			c2.getCity(ja);
		}) ;
		
		assertEquals("ERR: La città o lo stato inseriti non sono validi!", e.getMex());
	}
	
	/**
	 * test per la generazione dell'eccezione <b>WrongCityException<b> 
	 * nel caso di inserimento del nome della città in italiano, in quanto nel file le città sono salvate in inglese
	 * 
	 */

	@Test
	@DisplayName("Eccezione Città Sbagliata Funzionante")
	void WrongCityExceptionTest3() {
		
		ja = c3.caricaArray();
		
		e = assertThrows(WrongCityException.class, () -> {
			c3.getCity(ja);
		}) ;
		
		assertEquals("ERR: La città o lo stato inseriti non sono validi!", e.getMex());
	}
}
