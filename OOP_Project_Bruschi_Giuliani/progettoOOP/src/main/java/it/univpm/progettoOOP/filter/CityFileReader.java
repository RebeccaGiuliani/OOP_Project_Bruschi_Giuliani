package it.univpm.progettoOOP.filter;

import it.univpm.progettoOOP.model.City;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class CityFileReader implements CityFileReaderService {

	private static String name = "city.list.json";	
	private double lat, lon;
	private City city;

	public CityFileReader(City city) {
		this.city = city;
	}

	public void readFile () {
		try {
			Scanner file_input = new Scanner(new BufferedReader (new FileReader(name)));
			System.out.println(name);
			file_input.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	

/*	public void stampaFile () {
		try {
			PrintWriter file_output = new PrintWriter(new BufferedWriter (new FileWriter(name)));
			
			
			file_output.close();
		} 
		catch (IOException e) {
			System.out.println("ERORE di I/O");
			System.out.println(e);
		}
	}*/

	public double getLat() {

		return lat;
	}

	public double getLon() {
		return lon;
	}
}
