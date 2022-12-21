package app.view;

import javafx.scene.shape.Rectangle;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 * TODO une classe Sprite
 *      Ca me semble mieux de faire une classe Sprite et que view.Pet
 *      hérite de Sprite
 */
public class Pet extends AnimatedSprite {

	//########################### ATTRIBUTS #####################################
	 
	private Stat hunger;
	private Stat thirst;
	private Stat weight;
	private Stat hygiene;
	private Stat moral;
	
	//############################ METHODES #####################################
	
	public Pet(String spritesheet) {
		super(spritesheet, 0, 0, 512, 512);
		setSize(0, 92, 128, 128);
	}
	
	public void setHunger(Stat hunger) {
		this.hunger = hunger;
	}
	public void setThirst(Stat thirst) {
		this.thirst = thirst;
	}
	public void setWeight(Stat weight) {
		this.weight = weight;
	}
	public void setHygiene(Stat hygiene) {
		this.hygiene = hygiene;
	}
	public void setMoral(Stat moral) {
		this.moral = moral;
	}
	
	public Stat getHunger() {
		return hunger;
	}
	public Stat getThirst() {
		return thirst;
	}
	public Stat getWeight() {
		return weight;
	}
	public Stat getHygiene() {
		return hygiene;
	}
	public Stat getMoral() {
		return moral;
	}
	
	public void exit() {
		super.exit();
		hunger.exit();
		thirst.exit();
		weight.exit();
		hygiene.exit();
		moral.exit();
	}
}
