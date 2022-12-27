package app.view;

import javafx.animation.AnimationTimer;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.shape.Rectangle;

import app.Componable;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 */
public class Pet extends AnimatedSprite implements Componable {

	//########################### ATTRIBUTS #####################################
	AnimatedSprite color;
	
	private State hunger; // \
	private State thirst; // |
	private State weight; // |--> NOTE: references partagées avec c.Stat et v.Hud
	private State hygiene;// |
	private State moral;  // /
	
	public IntegerProperty x;
	public IntegerProperty y;
	
	/**
	 * animationloop effectué toute les 1 second
	 * déclendeur -> this
	 */ 
	private AnimationTimer animation = new AnimationTimer() {
		
		long old_time=0;
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+1_000_000_000;
				
				nextFrame();
			}
        }
    };
	
	//############################ METHODES #####################################
	
	public Pet(String spritesheet, String spritesheetColor) {
		super(spritesheet, 0, 0, 512, 512);
		color = new AnimatedSprite(spritesheetColor, 0, 0, 512, 512);
		setSize(0, 0, 128, 128);
		
		x = new SimpleIntegerProperty(0);
		y = new SimpleIntegerProperty(0);
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param numColorSprite la liste des numéro de case
	 * @param rects la liste des frames
	 */
	public void addAnime(String name, int[] numColorSprite, Rectangle ... rects) {
		addAnime(name, rects);
		
		Rectangle[] colorSprites = new Rectangle[numColorSprite.length];
		
		for(int i=0 ; i < numColorSprite.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = numColorSprite[i];
			
			while ( j >= 0 ) {
				x += 1;
				if ( x*512 >= color.getWidth() ) {
					x = 0;
					y += 1;
				}
				j--;
			}
			
			colorSprites[i] = new Rectangle(512*x, 512*y, 512, 512);
		}
		
		color.addAnime(name, colorSprites);
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param numColorSprite la liste des numéro de case
	 * @param numSprite la liste des numéro de case
	 */
	public void addAnime(String name, int[] numColorSprite, int[] numSprite) {
		
		Rectangle[] sprites = new Rectangle[numSprite.length];

		for(int i=0 ; i < numSprite.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = numSprite[i];
			
			while ( j >= 0 ) {
				x += 1;
				if ( x*512 >= this.getWidth() ) {
					x = 0;
					y += 1;
				}
				j--;
			}
			
			sprites[i] = new Rectangle(512*x, 512*y, 512, 512);
		}
		
		addAnime(name, sprites);
		
		Rectangle[] colorSprites = new Rectangle[numColorSprite.length];
		
		for(int i=0 ; i < numColorSprite.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = numColorSprite[i];
			
			while ( j >= 0 ) {
				x += 1;
				if ( x*512 >= color.getWidth() ) {
					x = 0;
					y += 1;
				}
				j--;
			}
			
			colorSprites[i] = new Rectangle(512*x, 512*y, 512, 512);
		}
		
		color.addAnime(name, colorSprites);
	}
	
	public void changeTypeColor(int num) {
		color.changeLine(num);
	}
	
	@Override 
	public void setPos(int dest_x, int dest_y) {
		super.setPos(dest_x, dest_y);
		if ( color != null )
			color.setPos(dest_x, dest_y);
		
		if ( x != null )
			x.setValue(dest_x);
		if ( y != null )
			y.setValue(dest_y);
	}
	
	@Override 
	public void setSize( int dest_x, int dest_y, int dest_h, int dest_w ) {
		super.setSize(dest_x, dest_y, dest_h, dest_w);
		if ( color != null )
			color.setSize(dest_x, dest_y, dest_h, dest_w);
		
		if ( x != null )
			x.setValue(dest_x);
		if ( y != null )
			y.setValue(dest_y);
	}
	
	@Override
	public void setAnime(String name) {
		super.setAnime(name);
		color.setAnime(name);
	}
	
	@Override
	public void nextFrame() {
		super.nextFrame();
		color.nextFrame();
	}
	
	@Override
	public void play() {
		animation.start();
	}
	
	@Override
	public void stop() {
		animation.stop();
	}
	
	public AnimatedSprite getColorSprite() {
		return color;
	}
	
	public void setChildHunger(State hunger) {
		this.hunger = hunger;
	}
	public void setChildThirst(State thirst) {
		this.thirst = thirst;
	}
	public void setChildWeight(State weight) {
		this.weight = weight;
	}
	public void setChildHygiene(State hygiene) {
		this.hygiene = hygiene;
	}
	public void setChildMoral(State moral) {
		this.moral = moral;
	}
	
	public State getChildHunger() {
		return hunger;
	}
	public State getChildThirst() {
		return thirst;
	}
	public State getChildWeight() {
		return weight;
	}
	public State getChildHygiene() {
		return hygiene;
	}
	public State getChildMoral() {
		return moral;
	}
	
	@Override
	public void exit() {
		super.exit();
		animation.stop();
		if ( hunger != null ) {
			hunger.exit();
			thirst.exit();
			weight.exit();
			hygiene.exit();
			moral.exit();
		}
	}
}
