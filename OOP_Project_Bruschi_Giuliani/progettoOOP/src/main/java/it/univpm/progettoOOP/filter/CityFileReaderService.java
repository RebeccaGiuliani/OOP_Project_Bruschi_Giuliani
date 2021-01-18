package it.univpm.progettoOOP.filter;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.exception.WrongCityException;

/**
 * <p>
 * <b>Interfaccia</b> che permette di leggere il file e estrarne i dati di interesse 
 * <p>
 * 
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

public interface CityFileReaderService {
	
	/**
	 * metodo che legge il file e lo copia su un JSONArray
	 * 
	 * @return <code>JSONArray</code>
	 */
	
	public abstract JSONArray caricaArray();
	
	/**
	 * metodo che scorre gli oggetti del file e estrae latitudine e longitudine della città di interesse
	 * 
	 * @param ja indica il JSONArray copiato dal file
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	public abstract void getCity(JSONArray ja) throws WrongCityException;
	
	/**
	 * metodo che ritorna il valore della latitudine della città di interesse
	 * 
	 * @return <code>double</code>
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	public abstract double getLat() throws WrongCityException;
	
	/**
	 * metodo che ritorna il valore della longitudine della città di interesse
	 * 
	 * @return <code>double</code>
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	public abstract double getLon() throws WrongCityException;
	
}
