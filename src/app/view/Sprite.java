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

	public Sprite(String spritesheet, int src_x, int src_y, int src_h, int src_w) {
		super(spritesheet);
		this.src_x = src_x;
		this.src_y = src_y;
		this.src_h = src_h;
		this.src_w = src_w;
		setSize(src_x, src_y, src_h, src_w);
	}

	public void setSize( int dest_x, int dest_y, int dest_h, int dest_w ) {
		this.dest_x = dest_x;
		this.dest_y = dest_y;
		this.dest_h = dest_h;
		this.dest_w = dest_w;
	}
	
	public double getDestX() {
		return dest_x;
	}
	public double getDestY() {
		return dest_y;
	}
	public double getDestW() {
		return dest_w;
	}
	public double getDestH() {
		return dest_h;
	}
	
	public double getSrcX() {
		return src_x;
	}
	public double getSrcY() {
		return src_y;
	}
	public double getSrcW() {
		return src_w;
	}
	public double getSrcH() {
		return src_h;
	}
	
	public void exit() {
		cancel();
	}
}
