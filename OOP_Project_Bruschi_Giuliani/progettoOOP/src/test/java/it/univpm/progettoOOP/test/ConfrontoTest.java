package it.univpm.progettoOOP.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.exception.WrongPeriodException;
import it.univpm.progettoOOP.model.City;
import it.univpm.progettoOOP.model.Period;
import it.univpm.progettoOOP.stats.Confronto;
import it.univpm.progettoOOP.stats.Stats;

/**
 *<p>
 *L'unità di test <b>ConfrontoTest</b> è generata per testare i metodi generici della classe <b>Confronto</b>.
 *</p>
 *
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

class ConfrontoTest {
	
	Confronto c1, c2, c3;
	Vector<Double> dati_mese;
	JSONArray ja;
	JSONObject jo,jop,y,yp;

	@BeforeEach
	void setUp() throws Exception {
		c1 = new Confronto(8,2019, new City("Ancona", "IT"));
		c2 = new Confronto(11,2019, new City("Ancona", "IT"));
		c3 = new Confronto(2,2019, new City("Ancona", "IT"));
		dati_mese = new Vector<>();
		ja = new JSONArray();
		jo = new JSONObject();
		jop = new JSONObject();
		y = new JSONObject();
		yp = new JSONObject();
	}

	@AfterEach
	void tearDown() throws Exception {
	}
	
	/**
	 * test del metodo che ritorna il valore che identifica il giorno finale del mese, considerando Agosto
	 * 
	 * @see it.univpm.progettoOOP.stats.Confronto#end_day(int)
	 */

	@Test
	@DisplayName("Corretto giorno finale di Agosto")
	void end_dayTest1() {

		assertEquals(31, c1.end_day(8));
	}
	
	/**
	 * test del metodo che ritorna il valore che identifica il giorno finale del mese, considerando Novembre
	 * 
	 * @see it.univpm.progettoOOP.stats.Confronto#end_day(int)
	 */
	
	@Test
	@DisplayName("Corretto giorno finale di Novembre")
	void end_dayTest2() {

		assertEquals(30, c2.end_day(11));
	}
	
	/**
	 * test del metodo che ritorna il valore che identifica il giorno finale del mese, considerando Febbraio
	 * 
	 * @see it.univpm.progettoOOP.stats.Confronto#end_day(int)
	 */
	
	@Test
	@DisplayName("Corretto giorno finale di Febbraio")
	void end_dayTest3() {

		assertEquals(28, c3.end_day(2));
	}
	
	/**
	 * test del metodo che inserisce i dati di un mese(media, varianza, max, min) in un vettore
	 * 
	 * @see it.univpm.progettoOOP.stats.Confronto#gestioneDati(Stats)
	 */

	@Test
	@DisplayName("Corretta Gestione Dati")
	void gestioneDatiTest() throws WrongPeriodException, WrongCityException {
		
		dati_mese.add(7.633333333333334);
		dati_mese.add(0.33922222222222237);
		dati_mese.add(8.8);
		dati_mese.add(6.52);
		
		assertEquals(dati_mese, c1.gestioneDati(new Stats(new Period(1,8,2019,31,8,2019), new City("Ancona", "IT"))));
	}

	/**
	 * test del metodo che ritorna un JSONArray con 2 JSONObject contenenti i dati del medesimo mese presi in due anni consecutivi
	 * 
	 * @see it.univpm.progettoOOP.stats.Confronto#ConfrontoStats()
	 */
	
	@SuppressWarnings("unchecked")
	@Test
	@DisplayName("Confronto Stats Corretto")
	void ConfrontoStatsTest() {
		
		jop.put("Media", 7.316129032258062);
		jop.put("Varianza", 0.4452946930280958);
		jop.put("Massimo", 8.39);
		jop.put("Minimo", 6.09);
		
		jo.put("Media", 7.633333333333334);
		jo.put("Varianza", 0.33922222222222237);
		jo.put("Massimo", 8.8);
		jo.put("Minimo", 6.52);
		
		yp.put("Dati 2018", jop);
		y.put("Dati 2019", jo);
		
		ja.add(yp);
		ja.add(y);
		
		assertEquals(ja.toString(), c1.ConfrontoStats().toString());
	}
}
