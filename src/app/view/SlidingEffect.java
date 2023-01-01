package app.view;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.paint.Color;

//import java.util.ArrayList;

public class SlidingEffect implements WeatherEffect {

	GraphicsContext gc;
	Sprite[] lefts;
	Sprite[] rights;
	double opacity;
	double velocity=0.5;
	double width;
	boolean makeStop;
	boolean doonce=false;
	public static enum SlideType{RIGHT_LEFT, ONLY_RIGHT, ONLY_LEFT};
	SlideType slidetype;
	double gapx;
	
	public SlidingEffect (GraphicsContext gc, double width, SlideType slidetype, int[] numbers, Sprite ... sprites) {
		this.slidetype = slidetype;
		this.gc = gc;
		opacity=0.0;
		this.width=width;
		makeStop = false;
		Random seed = new Random();
		
		int numbersLeft = 0;
		int numbersRight = 0;
		
		if ( slidetype == SlideType.RIGHT_LEFT ) {
			for ( int i=0; i<numbers.length/2; i++ ) {
				numbersLeft += numbers[i];
			}
			for ( int i=numbers.length/2; i<numbers.length; i++ ) {
				numbersRight += numbers[i];
			}
		}
		else if (slidetype == SlideType.ONLY_RIGHT) {
			numbersLeft = 0;
			for ( int i=numbers.length/2; i<numbers.length; i++ ) {
				numbersRight += numbers[i];
			}
		}
		else if (slidetype == SlideType.ONLY_LEFT) {
			numbersRight = 0;
			for ( int i=0; i<((numbers.length == 1) ? 1 : numbers.length/2); i++ ) {
				numbersLeft += numbers[i];
			}
		}
		
		lefts = new Sprite[numbersLeft];
		rights = new Sprite[numbersRight];
		
		double space = 0.0;
		double gap = 0;
		int oldj=0;
		for (int i=0; i<((numbers.length == 1) ? 1 : numbers.length/2); i++) {
			
			if ( slidetype == SlideType.ONLY_LEFT || slidetype == SlideType.RIGHT_LEFT ) {
				
				int j;
				for ( j=0; j<numbers[i]; j++ ) {
					//System.out.println("ONLY_LEFT : lefts.len="+lefts.length+" i="+i+" i<"+((numbers.length == 1) ? 1 : numbers.length/2)+" oldj+j="+(oldj+j)+ " sprites.len="+sprites.length );
					lefts[oldj+j] = new Sprite(sprites[i]);
					
					lefts[oldj+j].setPos(space, -lefts[oldj+j].getSheet().getHeight()+velocity);
					lefts[oldj+j].setLimitX(-lefts[oldj+j].getSheet().getWidth()+velocity, width);
					lefts[oldj+j].setLimitY(-width, width);
					lefts[oldj+j].setVelocity(velocity, 1);
					
					space += lefts[i].getSheet().getWidth()+gap;
					
					System.out.println("ONLY_LEFT add");
				}
				oldj+=j;
			}
		}
		
		space = 0.0;
		gap = 0;
		oldj=0;
		for (int i=numbers.length/2; i<numbers.length; i++) {
			if ( slidetype == SlideType.ONLY_RIGHT || slidetype == SlideType.RIGHT_LEFT ) {
				
				int j;
				for ( j=0; j<numbers[i]; j++ ) {
					rights[oldj+j] = new Sprite(sprites[i]);
					rights[oldj+j].setPos(space, -(rights[oldj+j].getSheet().getHeight()+velocity)+rights[oldj+j].getSheet().getHeight()*0.2);
					rights[oldj+j].setLimitX(-rights[oldj+j].getSheet().getWidth()+velocity, width);
					rights[oldj+j].setLimitY(-width, width);
					rights[oldj+j].setVelocity(-velocity, 1);
					
					space += rights[oldj+j].getSheet().getWidth()+gap;
					
					System.out.println("ONLY_RIGHT add");
				}
				oldj+=j;
			}
		}
	}
	public boolean drawEffect() {
		
		boolean stopValue = initDraw();
		gc.setGlobalAlpha( opacity );
		
		drawPass1(stopValue);
		drawPass2(stopValue);
		
		gc.setGlobalAlpha( 1.0 );
		return stopValue;
	}
	
	public boolean initDraw() {
		
		if(makeStop) {
			opacity-=((double)3/(500));
		}
		else {
			if( opacity<1.0 )
				opacity+=((double)3/(250));
		}
		
		return opacity<=0;
	}
	
	public void drawPass1(boolean stopValue) {

		if ( !stopValue ) {
			for (int i=0; i<rights.length; i++) {
				
				if ( makeStop ) {
					rights[i].setVelocity(rights[i].getVelocityX(), -0.35);
				}
				else if ( rights[i].getVelocityY() > 0 && rights[i].getDestY()+rights[i].getVelocityY() >= rights[i].getSheet().getHeight()*0.2 ) {
					rights[i].setVelocity(rights[i].getVelocityX(), 0.0);
				}
				
				gc.drawImage(rights[i].getSheet(), rights[i].getDestX(), rights[i].getDestY());
				rights[i].move();

			}
		}	
	}
	public void drawPass2(boolean stopValue) {
				
		if ( !stopValue ) {
			for (int i=0; i<lefts.length; i++) {
				
				if ( makeStop ) {
					lefts[i].setVelocity(lefts[i].getVelocityX(), -0.35);
				}
				else if ( lefts[i].getVelocityY() > 0 && lefts[i].getDestY()+lefts[i].getVelocityY() >= 0 ) {
					lefts[i].setVelocity(lefts[i].getVelocityX(), 0.0);
				}
				
				gc.drawImage(lefts[i].getSheet(), lefts[i].getDestX(), lefts[i].getDestY());
				lefts[i].move();
			}
		}
	}
	
	public boolean drawEffect(int numPass) {
		
		boolean stopValue = initDraw();
		gc.setGlobalAlpha( opacity );
		
		switch ( numPass ) {
			case 1:
				drawPass1(stopValue);
				break;
			case 2:
				drawPass2(stopValue);
				break;
			default:
				drawEffect();
				break;
		}
		
		gc.setGlobalAlpha( 1.0 );
		return stopValue;
	}
	
	public void stopEffect() {
		makeStop = true;
	}
	
	public void resize(double width, double height) {
		this.width = width;
		double space;
		
		space = 0.0;
		for (int i=0; i<rights.length; i++) {
			rights[i].setPos(space, rights[i].getSheet().getHeight()*0.2);
			rights[i].setLimitX(-rights[i].getSheet().getWidth()+velocity, width);
			rights[i].setLimitY(-width, width);
			
			space += rights[i].getSheet().getWidth();

		}
		
		space = 0.0;
		for (int i=0; i<lefts.length; i++) {
			lefts[i].setPos(space, 0);
			lefts[i].setLimitX(-lefts[i].getSheet().getWidth()+velocity, width);
			lefts[i].setLimitY(-width, width);
			
			space += lefts[i].getSheet().getWidth();
		}
	}
	public void clear() {
		
	}
}
