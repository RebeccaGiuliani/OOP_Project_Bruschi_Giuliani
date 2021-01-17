package it.univpm.progettoOOP.model;
/**
 * <p>
 * <b>Classe</b> che permette di separare i <i>giorni, mesi e anni</i> dalla stringa ottenuta con l'<i>APICall</i>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class Date {
	protected String date;
	protected String year;
	protected String month;
	protected String day;
/**
 * dalla data iso definisce giorno, mese e anno come interi
 * 
 * @param date indica la data iso
 */
	public Date(String date) {
		
		String[] param0 = date.split("T");
		String date0 = param0[0];
		
		this.date = date0;
		String[] param = date0.split("-");
		this.year = param[0];
		this.month = param[1];
		this.day = param[2];
	}
/**
 * ritorna il giorno
 * 
 * @return un <code>int</code> che rappresenta il giorno
 */
	public int getDay(){
		int giorno = Integer.parseInt(this.day);
		return giorno;
	} 
	/**
	 * ritorna il mese
	 * 
	 * @return un <code>int</code> che rappresenta il mese
	 */
	public int getMonth(){
		int mese = Integer.parseInt(this.month);
		return mese;
	}
	/**
	 * ritorna l'anno
	 * 
	 * @return un <code>int</code> che rappresenta l'anno
	 */
	public int getYear(){
		int anno = Integer.parseInt(this.year);
		return anno;
	}
	/**
	 * ritorna la data
	 * 
	 * @return un <code>String</code> che rappresenta la data
	 */
	public String getDate() {
		return this.date;
	}
}
