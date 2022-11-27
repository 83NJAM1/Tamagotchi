package app.model;

/**
 * 
 * @author ben
 * Les options de l'application
 */
public class Option {
	
	private String language;
	private double volume;
	private int h_window;
	private int w_window;
	
	public Option() {
		language="fr";
		volume=0.5;
		h_window=640;
		w_window=360;
	}
	
	public void setVolume(Double value) {
		volume=value;
	}
	public Double getVolume() {
		return volume;
	}
}
