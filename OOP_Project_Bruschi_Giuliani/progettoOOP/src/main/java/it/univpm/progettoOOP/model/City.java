package it.univpm.progettoOOP.model;
/**
 * <p>
 * <b>Classe</b> che rapresenta l'oggetto <i>città</i> che ha come parametri il <i>nome</i> e lo <i>stato</i>
 * <p>
 * 
 * @author SimoneBruschi
 * @author RebeccaGiuliani
 */
public class City {
		
		private String name;
		private String country;
/**
 * costruzione dell'oggetto città
 * 
 * @param name indica il nome della città
 * @param country indica lo stato
 */
		public City(String name, String country) {
			this.name = name;
			this.country= country;
		}
/**
 * ritorna il nome della città
 * 
 * @return una <code>String</code> con il nome della cità
 */
		public String getName() {
			return name;
		}
/**
 * permette di scegliere il nome della città
 * 
 * @param name indica il nome della città
 */
		public void setName(String name) {
			this.name = name;
		}
		/**
		 * ritorna lo stato
		 * 
		 * @return una <code>String</code> con lo stato
		 */
		public String getCountry() {
			return country;
		}
		/**
		 * permette di scegliere lo stato
		 * 
		 * @param country indica lo stato
		 */
		public void setCountry(String country) {
			this.country = country;
		}
}
