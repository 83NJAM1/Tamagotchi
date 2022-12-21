package app.controller;

import javafx.scene.shape.Rectangle;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le petModel
 */
public class Pet {
	
	//########################### ATTRIBUTS #####################################
	 
	// ATTENTION: référence partagé avec petModel.Game
	private app.model.Pet petModel;
	
	// ATTENTION: référence partagé avec petView.Game
	private app.view.Pet petView;
	
	private Stat hungerController;
	private Stat thirstController;
	private Stat weightController;
	private Stat hygieneController;
	private Stat moralController;
	
	//############################ METHODES #####################################
	
	public Pet(String type) {
		
		hungerController = new Stat("hunger");
		thirstController = new Stat("thirst");
		weightController = new Stat("weight");
		hygieneController = new Stat("hygiene");
		moralController = new Stat("moral");
		
		switch(type) {
			case "cat":
				petModel = new app.model.Animal("cat");
				petView = new app.view.Pet("./res/Animation_Chat_Normal.png");
				petView.addAnime("heureux", new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512));
				petView.addAnime("mort", new Rectangle(1024, 1536, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			case "dog":
				petModel = new app.model.Animal("dog");
				petView = new app.view.Pet("./res/Animation_Chien_Normal.png");
				petView.addAnime("heureux", new Rectangle(0, 0, 512, 512), 
										 new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512),
										 new Rectangle(1536, 0, 512, 512), new Rectangle(2048, 0, 512, 512));
				petView.addAnime("mort", new Rectangle(4*512, 3*512, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			case "robot":
				petModel = new app.model.Robot();
				petView = new app.view.Pet("./res/Animation_Robot.png");
				petView.addAnime("heureux", new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512));
				petView.addAnime("mort", new Rectangle(3*512, 512, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			default:
				petModel = new app.model.Robot();
				petView = new app.view.Pet("./res/no_image.png");
				break;
		}
		
		petModel.setHunger(hungerController.getModel());
		petModel.setThirst(thirstController.getModel());
		petModel.setWeight(weightController.getModel());
		petModel.setHygiene(hygieneController.getModel());
		petModel.setMoral(moralController.getModel());
		
		petView.setHunger(hungerController.getView());
		petView.setThirst(thirstController.getView());
		petView.setWeight(weightController.getView());
		petView.setHygiene(hygieneController.getView());
		petView.setMoral(moralController.getView());
	}
	
	public app.model.Pet getModel() {
		return petModel;
	}
	
	public app.view.Pet getView() {
		return petView;
	}
	
	public void updateText() {
		hungerController.updateText();
		thirstController.updateText();
		weightController.updateText();
		hygieneController.updateText();
		moralController.updateText();
	}
	
	public void statsDecreaseOvertime() {
		hungerController.decreaseValue();
		thirstController.decreaseValue();
		weightController.decreaseValue();
		hygieneController.decreaseValue();
		moralController.decreaseValue();
	}
	
	public void setDead() {
		petView.setAnime("mort");
	}
	public Stat getHunger() {
		return hungerController;
	}
	public Stat getThirst() {
		return thirstController;
	}
	public Stat getWeight() {
		return weightController;
	}
	public Stat getHygiene() {
		return hygieneController;
	}
	public Stat getMoral() {
		return moralController;
	}
	
	public void exit() {
		hungerController.exit();
		thirstController.exit();
		weightController.exit();
		hygieneController.exit();
		moralController.exit();
		petModel = null;
		petView = null;
		hungerController = null;
		thirstController = null;
		weightController = null;
		hygieneController = null;
		moralController = null;
	}
}
