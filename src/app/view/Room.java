package app.view;
 
import javafx.scene.image.Image;

/**
 * 
 * @author ben
 * view.Room est de type Image
 * 
 * pour le moment car si zone cliquable alors le type Image
 * ne sera pas suffisant
 */
public class Room extends Image {

	//########################### ATTRIBUTS #####################################
	 
	private double x;
	private double y;
	private double w;
	private double h;
	
	//############################ METHODES #####################################
	
	public Room(String imagepath) {
		super(imagepath);
		x = 0;
		y = 0;
		w = getWidth();
		h = getHeight();
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
	
	public void exit() {
		cancel();
	}
}
