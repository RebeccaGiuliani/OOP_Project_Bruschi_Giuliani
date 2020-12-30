package it.univpm.progettoOOP.filter;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;

import org.json.simple.JSONArray;

import it.univpm.progettoOOP.model.Period;

//lat = 43.5991, lon = 13.511

public class APICall implements APICallService {

	private static String appid = "786ef3c38e2018b2823094ad93925380";
	private static int hour = 13;
	private static int min = 0;
	private Period period;
	private double lat, lon;
	private long start, end;
	
	public APICall(Period period, double lat, double lon) {
		this.period = period;
		this.lat = lat;
		this.lon = lon;
		this.start = StartDateUnixConverter();
		this.end = EndDateUnixConverter();
	}

	private String url = "http://api.openweathermap.org/data/2.5/uvi/history?lat="+ lat +"&lon="+ lon +"&start="+ start +"&end="+ end +"&appid="+appid;
	
	public long StartDateUnixConverter() {
		GregorianCalendar cal = new GregorianCalendar(this.period.getStart_year(), this.period.getStart_month(), this.period.getStart_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long start = zdt.toEpochSecond() ;
		return start;
	}
	
	public long EndDateUnixConverter() {
		GregorianCalendar cal = new GregorianCalendar(this.period.getEnd_year(), this.period.getEnd_month(), this.period.getEnd_day(), hour, min);
		ZonedDateTime zdt = cal.toZonedDateTime() ;
		long end = zdt.toEpochSecond() ;
		return end;
	}
	
	public JSONArray getData() {
		try {
			URL api = new URL(url);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		JSONArray n = null;

		return n;
	}
}