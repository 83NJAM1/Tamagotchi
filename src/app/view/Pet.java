package app.view;

import app.Componable;

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
	
	//NOTE: references partagées avec c.Stat et v.Hud
	private State hunger;
	private State thirst;
	private State weight;
	private State hygiene;
	private State moral;
	
	//############################ METHODES #####################################
	
	public Pet(String spritesheet) {
		super(spritesheet, 0, 0, 512, 512);
		setSize(0, 92, 128, 128);
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
		hunger.exit();
		thirst.exit();
		weight.exit();
		hygiene.exit();
		moral.exit();
	}
}
