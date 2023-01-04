package app.view;

import javafx.scene.image.Image;

import java.util.ArrayList;

import app.Cleanable;

/**
 * 
 * @author ben
 * view.Room est de type Image
 */
public class Room extends Image implements Cleanable {

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
			return 5267/8192.0;
		else if ( this.getUrl().contains("Cuisine") ) // 728
			return 728/8192.0;
		else if ( this.getUrl().contains("Chambre") )// 6145
			return 6145/8192.0;
		else if ( this.getUrl().contains("Jardin") ) // 4026
			return 4026/8192.0;
		else
			return 369/948.0;
	}
	
	/**
	 * obtient l'origine y en pourcentage
	 * @return son origine y compris en 0.0 et 1.0
	 */
	public double getYOriginRatio() {
		// salle de bain
		if ( this.getUrl().contains("bain") )
			return 3496/4608.0;
		else if ( this.getUrl().contains("Cuisine") ) // 4172
			return 4172/4608.0;
		else if ( this.getUrl().contains("Chambre") ) // 4246
			return 4246/4608.0;
		else if ( this.getUrl().contains("Jardin") ) // 3993
			return 3993/4608.0;
		else
			return 394/576.0;
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
	public void clean() {
		cancel();
	}
}
