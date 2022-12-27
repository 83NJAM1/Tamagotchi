package app.view;

import app.Componable;
import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 * TODO une classe Sprite
 *      Ca me semble mieux de faire une classe Sprite et que view.Pet
 *      hérite de Sprite
 */
public class Pet extends AnimatedSprite implements Componable {

	//########################### ATTRIBUTS #####################################
	AnimatedSprite color;
	
	private State hunger; // \
	private State thirst; // |
	private State weight; // |--> NOTE: references partagées avec c.Stat et v.Hud
	private State hygiene;// |
	private State moral;  // /
	
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
		setSize(0, 92, 128, 128);
		color = new AnimatedSprite(spritesheetColor, 0, 0, 512, 512);
		color.setSize(0, 92, 128, 128);
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param rects la liste des frames
	 */
	public void addAnime(String name, int[] indexColor, Rectangle ... rects) {
		addAnime(name, rects);
		
		Rectangle[] backgroundSprites = new Rectangle[indexColor.length];
		
		for(int i=0 ; i < indexColor.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = indexColor[i];
			
			while ( j >= 0 ) {
				x += 1;
				if ( x*512 >= color.getWidth() ) {
					x = 0;
					y += 1;
				}
				j--;
			}
			
			backgroundSprites[i] = new Rectangle(512*x, 512*y, 512, 512);
		}
		
		color.addAnime(name, backgroundSprites);
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param rects la liste des frames
	 */
	public void addAnime(String name, int[] indexColor, int[] indexSprite) {
		
		Rectangle[] sprites = new Rectangle[indexSprite.length];

		for(int i=0 ; i < indexSprite.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = indexSprite[i];
			
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
		
		Rectangle[] backgroundSprites = new Rectangle[indexColor.length];
		

		for(int i=0 ; i < indexColor.length ; i++) {
			
			int x = -1;
			int y = 0;
			int j = indexColor[i];
			
			while ( j >= 0 ) {
				x += 1;
				if ( x*512 >= color.getWidth() ) {
					x = 0;
					y += 1;
				}
				j--;
			}
			
			backgroundSprites[i] = new Rectangle(512*x, 512*y, 512, 512);
			System.out.println("add frame for: " + name + backgroundSprites[i]);
		}
		
		color.addAnime(name, backgroundSprites);
	}
	
	public void changeTypeColor(int num) {
		color.changeLine(num);
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
