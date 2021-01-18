package it.univpm.progettoOOP.filter;

import org.json.simple.JSONArray;

/**
 * <p>
 * <b>Interfaccia</b> che gestisce le <i>chiamate API</i> 
 * <p>
 * 
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */
public interface APICallService {

	/**
	 * metodo che effettua una chiamata API e riporta i dati su un JSONArray 
	 * 
	 * @return <code>JSONArray</code>
	 */
	
	public abstract JSONArray getData();
	
	/**
	 * metodo che converte la data di inizio del periodo in codice unix
	 * 
	 * @return <code>long</code>
	 */
	
	public abstract long StartDateUnixConverter();
	
	/**
	 * metodo che converte la data di fine del periodo in codice unix
	 * 
	 * @return <code>long</code>
	 */
	
	public abstract long EndDateUnixConverter();
}
