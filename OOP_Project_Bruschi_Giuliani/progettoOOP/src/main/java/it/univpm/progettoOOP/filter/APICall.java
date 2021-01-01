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

import it.univpm.progettoOOP.model.Period;

//lat = 43.5991, lon = 13.511

public class APICall implements APICallService {

	private static String appid = "786ef3c38e2018b2823094ad93925380";
	private static int hour = 13;
	private static int min = 0;
	private Period period;
	private double lat, lon;
	private long start, end;
	private String url;
	
	public APICall(Period period, double lat, double lon) {
		this.period = period;
		this.lat = lat;
		this.lon = lon;
		this.start = StartDateUnixConverter();
		this.end = EndDateUnixConverter();
		this.url = "http://api.openweathermap.org/data/2.5/uvi/history?lat="+ this.lat +"&lon="+ this.lon +"&start="+ this.start +"&end="+ this.end +"&appid="+appid;
	}

	 
	
	public long StartDateUnixConverter() {
		int month = (this.period.getStart_month())-1;
		GregorianCalendar cal = new GregorianCalendar(this.period.getStart_year(), month, this.period.getStart_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long start = zdt.toEpochSecond() ;
		return start;
	}
	
	public long EndDateUnixConverter() {
		int month = (this.period.getEnd_month())-1;
		GregorianCalendar cal = new GregorianCalendar(this.period.getEnd_year(), month, this.period.getEnd_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long end = zdt.toEpochSecond() ;
		return end;
	}
	
	public JSONArray getData() {   

		String api = this.url;
		JSONArray ja = null;
		String data = "";
		String line = "";

		try {
			URLConnection openConnection = new URL(api).openConnection();
			InputStream in = openConnection.getInputStream();

			try {
				InputStreamReader input = new InputStreamReader( in );
				BufferedReader buf = new BufferedReader( input );

				while ( ( line = buf.readLine() ) != null ) {
					data+= line;
				}
			} finally {
				in.close();
			}
			ja = (JSONArray) JSONValue.parseWithException(data);
		} catch (ParseException e) {
			e.printStackTrace();
		}	
		catch (IOException e) {
			e.printStackTrace();
		}
		return ja;
	}
}