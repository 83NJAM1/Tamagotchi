package app.view;

import javafx.animation.AnimationTimer;
import javafx.scene.shape.Rectangle;

import app.Cleanable;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 */
public class Pet extends AnimatedSprite implements Cleanable {

	//########################### ATTRIBUTS #####################################
	AnimatedSprite fx;
	AnimatedSprite color;
	Sprite object;
	
	private State hunger; // \
	private State thirst; // |
	private State weight; // |--> NOTE: references partagées avec c.Stat et v.Hud
	private State hygiene;// |
	private State moral;  // /
	
	Double distanceFactor;
	
	boolean visible;
	
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
		object = null;
		setSize(0, 0, 128, 128);
		distanceFactor=1.0;
		visible = true;
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param numColorSprite la liste des numéro de case
	 * @param rects la liste des frames
	 */
	public void addAnime(String name, int[] numColorSprite, Rectangle ... rects) {
		addAnime(name, rects);	
		color.addAnime(name, numColorSprite);
	}
	
	/**
	 * ajoute une animation et les couleurs
	 * @param name l'identifiant de l'animation
	 * @param numColorSprite la liste des numéro de case
	 * @param numSprite la liste des numéro de case
	 */
	public void addAnime(String name, int[] numColorSprite, int[] numSprite) {	
		addAnime(name, numSprite);
		color.addAnime(name, numColorSprite);
	}
	
	public void changeTypeColor(int num) {
		color.changeLine(num);
	}
	
	public Sprite getObject() {
		return object;
	}
	public void setObject(Sprite sheet) {
		object = sheet;
		if ( object != null )
			object.setSize(0,  0, dest_h.getValue()*0.25, dest_w.getValue()*0.25);
	}
	public boolean getVisible() {
		return visible;
	}
	public void setVisible(boolean mode) {
		visible = mode;
	}
	
	@Override 
	public void setPos(double dest_x, double dest_y) {
		super.setPos(dest_x, dest_y);
		if ( color != null )
			color.setPos(dest_x, dest_y);
	}
	
	@Override 
	public void setSize( double dest_x, double dest_y, double dest_h, double dest_w ) {
		super.setSize(dest_x, dest_y, dest_h, dest_w);
		if ( color != null )
			color.setSize(dest_x, dest_y, dest_h, dest_w);
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
	
	public void setDisctanceFactor(double factor) {
		distanceFactor=factor;
	}
	public Double getDisctanceFactor() {
		return 1.0;//distanceFactor;
	}
	
	@Override
	public void clean() {
		super.clean();
		animation.stop();
		if ( hunger != null ) {
			hunger.clean();
			thirst.clean();
			weight.clean();
			hygiene.clean();
			moral.clean();
		}
	}
}
