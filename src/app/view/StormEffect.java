package app.view;

import javafx.scene.canvas.GraphicsContext;

public class StormEffect implements WeatherEffect {
	
	double height;
	double width;
	boolean makeStop;
	SlidingEffect fx1;
	FallingEffect fx2;
	SlidingEffect fx3;
	GraphicsContext gc;
	 
	public StormEffect(double w, double h, GraphicsContext gc) {
		this.gc = gc;
		
		fx1 = new SlidingEffect(gc, w, SlidingEffect.SlideType.ONLY_RIGHT, new int[]{4},
				new Sprite(Main.GAMEIMAGEPATH+"effects/nuage-5.png", 0, 0, 768, 96));
		


		Sprite s1 = new Sprite(Main.GAMEIMAGEPATH+"effects/gouttes-1.png", 0, 0, 64, 64);
		Sprite s2 = new Sprite(Main.GAMEIMAGEPATH+"effects/gouttes-2.png", 0, 0, 64, 64);
		
		fx2 = new FallingEffect(null, w, h, 40, s1, s2, FallingEffect.MapType.RANDOM, FallingEffect.FxType.TAILED, false, gc);
		
		fx3 = new SlidingEffect(gc, w, SlidingEffect.SlideType.ONLY_LEFT, new int[]{4},
				new Sprite(Main.GAMEIMAGEPATH+"effects/nuage-4.png", 0, 0, 768, 96));
	}
	
	public boolean drawEffect() {
		
		boolean one = fx1.drawEffect();
		boolean tow = fx2.drawEffect();
		boolean three = fx3.drawEffect();

		return one && tow && three;
	}
	
	public boolean drawEffect(int numPass) {
		
		switch(numPass) {
		
			case 1:
				return fx1.drawEffect();
			case 2:
				return fx2.drawEffect();
			case 3:
				return fx3.drawEffect();
			default:
				return drawEffect();
		}
		
	}
	
	public void stopEffect() {
		makeStop = true;
		fx1.stopEffect();
		fx2.stopEffect();
		fx3.stopEffect();
	}
	
	public void resize(double width, double height) {
		fx1.resize(width, height);
		fx2.resize(width, height);
		fx3.resize(width, height);
	}
	
	public void clear() {
		fx1.clear();
		fx2.clear();
		fx3.clear();
	}
}
