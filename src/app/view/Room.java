package app.view;

import javafx.scene.image.Image;

import java.util.ArrayList;

import app.Componable;

/**
 * 
 * @author ben
 * view.Room est de type Image
 */
public class Room extends Image implements Componable {

	//########################### ATTRIBUTS #####################################
	 
	private double x;
	private double y;
	private double w;
	private double h;
	
	private ArrayList<Sprite> elements;
	
	//############################ METHODES #####################################
	
	public Room(String imagepath) {
		super(imagepath);
		x = 0;
		y = 0;
		w = getWidth();
		h = getHeight();
		elements = new ArrayList<>();
	}
	
	/**
	 * obtient l'origine x en pourcentage
	 * @return son origine x compris en 0.0 et 1.0
	 */
	public double getXOriginRatio() {
		// salle de bain
		if ( this.getUrl().contains("bain") )
			return 5267/getWidth();
		// autre
		else
			return 0.5;
	}
	
	/**
	 * obtient l'origine y en pourcentage
	 * @return son origine y compris en 0.0 et 1.0
	 */
	public double getYOriginRatio() {
		// salle de bain
		if ( this.getUrl().contains("bain") )
			return 3496/getHeight();
		// autre
		else
			return 0.5;
	}
	
	public void addElement(Sprite e) {
		elements.add(e);
	}
	
	public Sprite getElement(int i) {
		return elements.get(i);
	}
	
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getW() {
		return w;
	}
	public double getH() {
		return h;
	}
	
	@Override
	public void exit() {
		cancel();
	}
}
