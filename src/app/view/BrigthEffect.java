package app.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Effet pour la météo
 * @author ben
 *
 */
public class BrigthEffect implements WeatherEffect {
	
	double w;
	double h;
	GraphicsContext gc;
	Rectangle fx;
	
	boolean makestop;
	double opacity;
	double maxIntensity;
	Color color;
	
	/**
	 * constructeur
	 * @param w largeur
	 * @param h hauteur
	 * @param gc provenant de view.Game
	 * @param c la couleur voulu
	 * @param i l'intensité ( opacité ) 
	 */
	public BrigthEffect(double w, double h, GraphicsContext gc, Color c, double i) {
		this.w = w;
		this.h = h;
		this.gc = gc;
		fx = new Rectangle(0, 0, w, h);
		opacity = 0.0;
		makestop=false;
		maxIntensity=i;
		color=c;
		
	}
	
	@Override
	public boolean drawEffect() {
		
		if ( !makestop ) {
			if ( opacity < maxIntensity )
				opacity+=0.03;
		}
		else {
			if ( opacity > 0.0 )
				opacity-=0.03;
			else {
				return false;
			}
		}
		
		
		gc.setGlobalAlpha(opacity);
		//gc.setGlobalBlendMode(BlendMode.SOFT_LIGHT);
		gc.setFill(color);
		gc.fillRect(0, 0, w, h);

		gc.setGlobalAlpha(1.0);
		
		return true;
	}
	
	@Override
	public boolean drawEffect(int numPass) {
		switch (numPass) {
			case 2:
				return drawEffect();
			default:
				return false;
		}
	}
	
	@Override
	public void stopEffect() {
		makestop=true;
	}
	
	@Override
	public void resize(double width, double height) {
		w = width;
		h = height;
		fx = new Rectangle(0, 0, w, h);
	}
	
	@Override
	public void clear() {
	}
}
