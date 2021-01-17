package it.univpm.progettoOOP.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.filter.CityFileReader;
import it.univpm.progettoOOP.model.City;

/**
 *<p>
 *L'unità di test <b>ConfrontoTest</b> è generata per testare i metodi generici della classe <b>CityFileReader</b>.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

class CityFileReaderTest {

	private City city1;
	private City city2;
	private CityFileReader c1;
	private CityFileReader c2;

	@BeforeEach
	void setUp() throws Exception {
		city1 = new City("Ancona", "IT");  
		c1 = new CityFileReader(city1);	
		city2 = new City("Rome", "IT");   
		c2 = new CityFileReader(city2);	
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * test del metodo che ritorna la latidutine essendo Ancona la città di interesse
	 * 
	 * @see it.univpm.progettoOOP.filter.CityFileReader#getLat()
	 * 
	 * @throws WrongCityException
	 */

	@Test
	@DisplayName("Latitudine Corretta Ancona")
	void getLatTest1() throws WrongCityException {

		assertEquals(43.59816, c1.getLat());
	}
	
	/**
	 * test del metodo che ritorna la latidutine essendo Roma la città di interesse
	 * 
	 * @see it.univpm.progettoOOP.filter.CityFileReader#getLat()
	 * 
	 * @throws WrongCityException
	 */

	@Test
	@DisplayName("Latitudine Corretta Roma")
	void getLatTest2() throws WrongCityException {

		assertEquals(41.894741, c2.getLat());
	}
	
	/**
	 * test del metodo che ritorna la longitudine essendo Ancona la città di interesse
	 * 
	 * @see it.univpm.progettoOOP.filter.CityFileReader#getLon()
	 * 
	 * @throws WrongCityException
	 */

	@Test
	@DisplayName("Longitudine Corretta Ancona")
	void getLonTest1() throws WrongCityException {

		assertEquals(13.51008, c1.getLon());
	}
	
	/**
	 * test del metodo che ritorna la longitudine essendo Roma la città di interesse
	 * 
	 * @see it.univpm.progettoOOP.filter.CityFileReader#getLon()
	 * 
	 * @throws WrongCityException
	 */

	@Test
	@DisplayName("Longitudine Corretta Roma")
	void getLonTest2() throws WrongCityException {

		assertEquals(12.4839, c2.getLon());
	}
}
