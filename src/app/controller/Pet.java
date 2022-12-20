package app.controller;

import javafx.scene.shape.Rectangle;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Pet {
	
	//########################### ATTRIBUTS #####################################
	 
	// ATTENTION: référence partagé avec model.Game
	private app.model.Pet model;
	
	// ATTENTION: référence partagé avec view.Game
	private app.view.Pet view;
	
	private Stat hunger;
	private Stat thirst;
	private Stat weight;
	private Stat hygiene;
	private Stat moral;
	
	//############################ METHODES #####################################
	
	public Pet(String type) {
		hunger = new Stat("hunger");
		thirst = new Stat("thirst");
		weight = new Stat("weight");
		hygiene = new Stat("hygiene");
		moral = new Stat("moral");
		
		switch(type) {
			case "cat":
				model = new app.model.Animal("cat");
				view = new app.view.Pet("./res/Animation_Chat_Normal.png");
				view.addAnime("heureux", new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512));
				view.addAnime("mort", new Rectangle(1024, 1536, 512, 512));
				view.setAnime("heureux");
				view.play();
				break;
			case "dog":
				model = new app.model.Animal("dog");
				view = new app.view.Pet("./res/Animation_Chien_Normal.png");
				view.addAnime("heureux", new Rectangle(0, 0, 512, 512), 
										 new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512),
										 new Rectangle(1536, 0, 512, 512), new Rectangle(2048, 0, 512, 512));
				view.addAnime("mort", new Rectangle(4*512, 3*512, 512, 512));
				view.setAnime("heureux");
				view.play();
				break;
			case "robot":
				model = new app.model.Robot();
				view = new app.view.Pet("./res/Animation_Robot.png");
				view.addAnime("heureux", new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512));
				view.addAnime("mort", new Rectangle(3*512, 512, 512, 512));
				view.setAnime("heureux");
				view.play();
				break;
			default:
				model = new app.model.Robot();
				view = new app.view.Pet("./res/no_image.png");
				break;
		}
		
		model.setHunger(hunger.getModel());
		model.setThirst(thirst.getModel());
		model.setWeight(weight.getModel());
		model.setHygiene(hygiene.getModel());
		model.setMoral(moral.getModel());
		
		view.setHunger(hunger.getView());
		view.setThirst(thirst.getView());
		view.setWeight(weight.getView());
		view.setHygiene(hygiene.getView());
		view.setMoral(moral.getView());
	}
	
	public app.model.Pet getModel() {
		return model;
	}
	
	public app.view.Pet getView() {
		return view;
	}
	
	public void updateText() {
		hunger.updateText();
		thirst.updateText();
		weight.updateText();
		hygiene.updateText();
		moral.updateText();
	}
	
	public void statsDecreaseOvertime() {
		hunger.decreaseValue();
		thirst.decreaseValue();
		weight.decreaseValue();
		hygiene.decreaseValue();
		moral.decreaseValue();
	}
	
	public void setDead() {
		view.setAnime("mort");
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
		hunger.exit();
		thirst.exit();
		weight.exit();
		hygiene.exit();
		moral.exit();
		model = null;
		view = null;
		hunger = null;
		thirst = null;
		weight = null;
		hygiene = null;
		moral = null;
	}
}
