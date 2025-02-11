package it.univpm.progettoOOP.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.json.simple.JSONArray;
import org.json.simple.JSONValue;
import org.json.simple.parser.ParseException;

import it.univpm.progettoOOP.exception.WrongCityException;
import it.univpm.progettoOOP.model.Period;

/**
 * <p>
 * <b>Classe</b> che implementa l'interfaccia <b>APICallService</b> e permette di gestire le <i>chiamate API</i> 
 * <p>
 * 
 *  * @see it.univpm.progettoOOP.filter.APICallService
 * 
 * @author RebeccaGiuliani
 * @author SimoneBruschi
 *
 */

public class APICall implements APICallService {

	private static String appid = "786ef3c38e2018b2823094ad93925380"; //appid Simone "1ea00837bb533c8b22f844c29a16341e"
	private static int hour = 13;
	private static int min = 0;
	private Period period;
	private double lat, lon;
	private long start, end;
	private String url;
	
	/**
	 * Costruttore della classe
	 * 
	 * @param period indica il periodo di interesse
	 * @param city indica la città di interesse
	 * 
	 * @throws WrongCityException città inserita sbagliata
	 */
	
	public APICall (Period period, CityFileReader city) throws WrongCityException {
		this.period = period;
		this.lat = city.getLat();
		this.lon = city.getLon();
		this.start = StartDateUnixConverter();
		this.end = EndDateUnixConverter();
		this.url = "http://api.openweathermap.org/data/2.5/uvi/history?lat="+ this.lat +"&lon="+ this.lon +"&start="+ this.start +"&end="+ this.end +"&appid="+appid;
	}
	
	/**
	 * metodo che ritorna il valore del periodo
	 * 
	 * @return <code>Period</code>
	 */
	
	public Period getPeriod() {
		return this.period;
	}
	
	@Override
	public long StartDateUnixConverter() {
		int month = (this.period.getStart_month())-1;
		GregorianCalendar cal = new GregorianCalendar(this.period.getStart_year(), month, this.period.getStart_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long start = zdt.toEpochSecond() ;
		return start;
	}
	
	@Override
	public long EndDateUnixConverter() {
		int month = (this.period.getEnd_month())-1;
		GregorianCalendar cal = new GregorianCalendar(this.period.getEnd_year(), month, this.period.getEnd_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long end = zdt.toEpochSecond() ;
		return end;
	}
	
	@Override
	public JSONArray getData() {   

		String api = this.url;
		JSONArray ja = null;
		String data_filter = "";
		String line = "";

		try {
			
			URLConnection openConnection = new URL(api).openConnection();
			InputStream in = openConnection.getInputStream();

			InputStreamReader input = new InputStreamReader( in );
			BufferedReader buf = new BufferedReader( input );

			while ( ( line = buf.readLine() ) != null ) {
				data_filter += line;
			}
			
			in.close();
			ja = (JSONArray) JSONValue.parseWithException(data_filter);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		catch (IOException e ) {
			e.printStackTrace();
		}
		
		return ja;
	}
}