package it.univpm.progettoOOP.model;

public class Dati {
	
	private double max, min, media, varianza, mediaSeason, varianzaSeason, maxSeason, minSeason;
	
	

	public Dati( double media,double varianza ,double max,double min ) {
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
	
	public double getMediaSeason() {
		return mediaSeason;
	}

	public void setMediaSeason(double mediaSeason) {
		this.mediaSeason = mediaSeason;
	}

	public double getVarianzaSeason() {
		return varianzaSeason;
	}

	public void setVarianzaSeason(double varianzaSeason) {
		this.varianzaSeason = varianzaSeason;
	}

	public double getMaxSeason() {
		return maxSeason;
	}

	public void setMaxSeason(double maxSeason) {
		this.maxSeason = maxSeason;
	}

	public double getMinSeason() {
		return minSeason;
	}

	public void setMinSeason(double minSeason) {
		this.minSeason = minSeason;
	}

}
