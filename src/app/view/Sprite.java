package app.view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.image.Image;

public class Sprite { // TODO changer l'heritage par un attribut
	
	//########################### ATTRIBUTS #####################################
	Image spritesheet;
	
	// taille du rectange délimitant une frame
	double src_x;
	double src_y;
	double src_h;
	double src_w;
	
	// taille lors de l'affichage 
	DoubleProperty dest_x;
	DoubleProperty dest_y;
	DoubleProperty dest_h;
	DoubleProperty dest_w;
	
	DoubleProperty velocity_x;
	DoubleProperty velocity_y;
	double limit_minx;
	double limit_miny;
	double limit_maxx;
	double limit_maxy;
	//############################ METHODES #####################################
	
	

	
	/**
	 * constructeur
	 * @param spritesheet l'image des sprites
	 * @param src_x l'origine x du sprite dans l'image
	 * @param src_y l'origine y du sprite dans l'image
	 * @param src_h sa hauteur
	 * @param src_w sa largeur
	 */
	public Sprite(String spritesheet, double src_x, double src_y, double src_h, double src_w) {
		
		this.spritesheet = new Image(spritesheet);
		
		dest_x =  new SimpleDoubleProperty();
		dest_y =  new SimpleDoubleProperty();
		dest_h =  new SimpleDoubleProperty();
		dest_w =  new SimpleDoubleProperty();
		velocity_x =  new SimpleDoubleProperty(0.0);
		velocity_y =  new SimpleDoubleProperty(0.0);
		limit_minx = 0.0;
		limit_miny = 0.0;
		limit_maxx = 0.0;
		limit_maxy = 0.0;
		
		this.src_x = src_x;
		this.src_y = src_y;
		this.src_h = src_h;
		this.src_w = src_w;
		
		this.setSize(src_x, src_y, src_h, src_w);
	}
	
	public Image getSheet() {
		return spritesheet;
	}
	
	public void setSheet(String newSheet) {
		spritesheet = new Image(newSheet);
	}
	/**
	 * copie
	 */
	public Sprite(Sprite copie) {
		this(copie.getSheet().getUrl(), copie.src_x, copie.src_y, copie.src_w, copie.src_h);
	}
	
	public void setLimitX(double min, double max) {
		limit_minx = min;
		limit_maxx = max;
	}
	public void setLimitY(double min, double max) {
		limit_miny = min;
		limit_maxy = max;
	}
	
	
	public void move() {
		
		if ( dest_x.getValue() < limit_minx ) {
			dest_x.setValue(limit_maxx);
		}
		else if ( dest_x.getValue() > limit_maxx ) {
			dest_x.setValue(limit_minx);
		}
		else {
			dest_x.setValue(dest_x.getValue()+velocity_x.getValue());
		}
		
		if ( dest_y.getValue() < limit_miny ) {
			dest_y.setValue(limit_maxy);
		}
		else if ( dest_y.getValue() > limit_maxy ) {
			dest_y.setValue(limit_miny);
		}
		else {
			dest_y.setValue(dest_y.getValue()+velocity_y.getValue());
		}
		
		
	}
	
	public void setVelocity(double x, double y) {
		velocity_x.setValue(x);
		velocity_y.setValue(y);
	}
	
	/**
	 * obtient la velocité x
	 * @return x
	 */
	public double getVelocityX() {
		return velocity_x.getValue();
	}
	
	/**
	 * obtient la velocité y
	 * @return y
	 */
	public double getVelocityY() {
		return velocity_y.getValue();
	}
	
	/**
	 * définit la position et la taille du sprite
	 * @param dest_x sa position x dans le canvas
	 * @param dest_y sa position y dans le canvas
	 * @param dest_h sa hauteur d'affichage
	 * @param dest_w sa largeur d'affichage
	 */
	public void setSize( double dest_x, double dest_y, double dest_h, double dest_w ) {
		this.dest_x.setValue(dest_x);;
		this.dest_y.setValue(dest_y);
		this.dest_h.setValue(dest_h);
		this.dest_w.setValue(dest_w);
	}
	
	/**
	 * définit la position
	 * @param dest_x sa position x dans le canvas
	 * @param dest_y sa position y dans le canvas
	 */
	public void setPos( double dest_x, double dest_y) {
		this.dest_x.setValue(dest_x);;
		this.dest_y.setValue(dest_y);
	}
	
	/**
	 * obtient la position x dans le canvas
	 * @return x
	 */
	public double getDestX() {
		return dest_x.getValue();
	}
	
	/**
	 * obtient la position y dans le canvas
	 * @return y
	 */
	public double getDestY() {
		return dest_y.getValue();
	}
	
	/**
	 * obtient la largeur d'affichage
	 * @return w
	 */
	public double getDestW() {
		return dest_w.getValue();
	}
	
	/**
	 * obtient la hauteur d'affichage
	 * @return h
	 */
	public double getDestH() {
		return dest_h.getValue();
	}
	
	/**
	 * obtient la position x dans l'image
	 * @return x
	 */
	public double getSrcX() {
		return src_x;
	}
	
	/**
	 * obtient la position y dans l'image
	 * @return y
	 */
	public double getSrcY() {
		return src_y;
	}
	
	/**
	 * obtient la largeur du sprite dans l'image
	 * @return w
	 */
	public double getSrcW() {
		return src_w;
	}
	
	/**
	 * obtient la hauteur du sprite dans l'image
	 * @return h
	 */
	public double getSrcH() {
		return src_h;
	}
	
	/**
	 * obtient la propriété x
	 * @return x
	 */
	public DoubleProperty getXProperty() {
		return dest_x;
	}
	
	/**
	 * obtient la propriété y
	 * @return y
	 */
	public DoubleProperty getYProperty() {
		return dest_y;
	}
	
	/**
	 * obtient la propriété w
	 * @return w
	 */
	public DoubleProperty getWProperty() {
		return dest_w;
	}
	
	/**
	 * obtient la propriété h
	 * @return h
	 */
	public DoubleProperty getHProperty() {
		return dest_h;
	}
	
	/**
	 * obtient la propriété velocity x
	 * @return velocity x
	 */
	public DoubleProperty getXVelocityProperty() {
		return velocity_x;
	}
	
	/**
	 * obtient la propriété velocity y
	 * @return velocity y
	 */
	public DoubleProperty getYVelocityProperty() {
		return velocity_y;
	}
}
