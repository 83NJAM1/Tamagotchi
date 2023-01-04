package app.view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BrigthEffect implements WeatherEffect {

	double w;
	double h;
	GraphicsContext gc;
	Rectangle fx;
	
	boolean makestop;
	double opacity;
	double maxIntensity;
	Color color;
	
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
	
	public boolean drawEffect(int numPass) {
		switch (numPass) {
			case 2:
				return drawEffect();
			default:
				return false;
		}
	}
	
	public void stopEffect() {
		makestop=true;
	}
	public void resize(double width, double height) {
		
	}
	public void clear() {
		
	}
}
