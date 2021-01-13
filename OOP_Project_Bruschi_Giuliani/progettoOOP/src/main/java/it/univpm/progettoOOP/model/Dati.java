package it.univpm.progettoOOP.model;

public class Dati {
	
	private double max, min, media, varianza;
	
	public Dati(double max, double min, double media, double varianza) {
		this.max = max;
		this.min = min;
		this.media = media;
		this.varianza = varianza;
	}

	public double getMax() {
		return max;
	}

	public void setMax(double max) {
		this.max = max;
	}

	public double getMin() {
		return min;
	}

	public void setMin(double min) {
		this.min = min;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	public double getVarianza() {
		return varianza;
	}

	public void setVarianza(double varianza) {
		this.varianza = varianza;
	}

	/*public Date getD() {
		return d;
	}

	public void setD(Date d) {
		this.d = d;
	}*/

}
