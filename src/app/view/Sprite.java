package app.view;

import javafx.scene.image.Image;

public class Sprite extends Image {
	
	//########################### ATTRIBUTS #####################################

	// taille du rectange délimitant une frame
	double src_x;
	double src_y;
	double src_h;
	double src_w;
	
	// taille lors de l'affichage 
	double dest_x;
	double dest_y;
	double dest_h;
	double dest_w;
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 * @param spritesheet l'image des sprites
	 * @param src_x l'origine x du sprite dans l'image
	 * @param src_y l'origine y du sprite dans l'image
	 * @param src_h sa hauteur
	 * @param src_w sa largeur
	 */
	public Sprite(String spritesheet, int src_x, int src_y, int src_h, int src_w) {
		super(spritesheet);
		this.src_x = src_x;
		this.src_y = src_y;
		this.src_h = src_h;
		this.src_w = src_w;
		this.setSize(src_x, src_y, src_h, src_w);
	}
	
	/**
	 * définit la position et la taille du sprite
	 * @param dest_x sa position x dans le canvas
	 * @param dest_y sa position y dans le canvas
	 * @param dest_h sa hauteur d'affichage
	 * @param dest_w sa largeur d'affichage
	 */
	public void setSize( int dest_x, int dest_y, int dest_h, int dest_w ) {
		this.dest_x = dest_x;
		this.dest_y = dest_y;
		this.dest_h = dest_h;
		this.dest_w = dest_w;
	}
	
	/**
	 * obtient la position x dans le canvas
	 * @return x
	 */
	public double getDestX() {
		return dest_x;
	}
	
	/**
	 * obtient la position y dans le canvas
	 * @return y
	 */
	public double getDestY() {
		return dest_y;
	}
	
	/**
	 * obtient la largeur d'affichage
	 * @return w
	 */
	public double getDestW() {
		return dest_w;
	}
	
	/**
	 * obtient la hauteur d'affichage
	 * @return h
	 */
	public double getDestH() {
		return dest_h;
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
}
