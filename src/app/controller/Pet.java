package app.controller;

import javafx.scene.shape.Rectangle;

import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Pet implements Componable, Localisable {
	
	//########################### ATTRIBUTS #####################################
	
	// donnée du pet
	private app.model.Pet petModel; //NOTE: référence partagé avec model.Game
	
	// affichage de pet
	private app.view.Pet petView; //NOTE: référence partagé avec view.Game
	
	// les controller des états du pet
	private State hungerController;
	private State thirstController;
	private State weightController;
	private State hygieneController;
	private State moralController;
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 * @param type la chaîne de charactère identifiant le type: "cat", "dog", "rabbit" ou "robot"
	 */
	public Pet(String type) {
		
		hungerController = new State("hunger");
		thirstController = new State("thirst");
		weightController = new State("weight");
		hygieneController = new State("hygiene");
		moralController = new State("moral");
		
		switch(type) {
			case "cat":
				petModel = new app.model.Animal("cat");
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Normal.png", Game.GAMEIMAGEPATH+"cat/colorPet.png");
				petView.addAnime("heureux", new int[]{0, 1, 2}, new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512));
				petView.addAnime("mort", new int[]{1}, new Rectangle(1024, 1536, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			case "dog":
				petModel = new app.model.Animal("dog");
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Normal.png", Game.GAMEIMAGEPATH+"dog/colorPet.png");
				petView.addAnime("heureux", new int[]{0, 1, 2, 3, 4}, new Rectangle(0, 0, 512, 512), 
										 new Rectangle(512, 0, 512, 512), new Rectangle(1024, 0, 512, 512),
										 new Rectangle(1536, 0, 512, 512), new Rectangle(2048, 0, 512, 512));
				petView.addAnime("mort", new int[]{0}, new Rectangle(4*512, 3*512, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			case "robot":
				petModel = new app.model.Robot();
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"robot/Animation_Robot.png", Game.GAMEIMAGEPATH+"robot/colorPet.png");
				petView.addAnime("heureux", new int[]{0, 1}, new Rectangle(0, 0, 512, 512), new Rectangle(512, 0, 512, 512));
				petView.addAnime("mort", new int[]{4}, new Rectangle(3*512, 512, 512, 512));
				petView.setAnime("heureux");
				petView.play();
				break;
			default:
				petModel = new app.model.Robot();
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"error/no_image.png", Game.GAMEIMAGEPATH+"error/no_image.png");
				break;
		}
		
		petModel.init(hungerController.getModel(),
					  thirstController.getModel(),
			 		  weightController.getModel(),
			 		  hygieneController.getModel(),
			 		  moralController.getModel());
		
		petView.setChildHunger(hungerController.getView());
		petView.setChildThirst(thirstController.getView());
		petView.setChildWeight(weightController.getView());
		petView.setChildHygiene(hygieneController.getView());
		petView.setChildMoral(moralController.getView());
	}
	
	/**
	 * obtient le controller enfant hunger
	 * @return hunger, un State controller
	 */
	public State getControllerHunger() {
		return hungerController;
	}
	
	/**
	 * obtient le controller enfant thirst
	 * @return thirst, un State controller
	 */
	public State getControllerThirst() {
		return thirstController;
	}
	
	/**
	 * obtient le controller enfant weight
	 * @return weight, un State controller
	 */
	public State getControllerWeight() {
		return weightController;
	}
	
	/**
	 * obtient le controller enfant hygienne
	 * @return hygienne, un State controller
	 */
	public State getControllerHygiene() {
		return hygieneController;
	}
	
	/**
	 * obtient le controller enfant moral
	 * @return moral, un State controller
	 */
	public State getControllerMoral() {
		return moralController;
	}
	
	/**
	 * obtient le model Pet
	 * @return Pet, le model
	 */
	public app.model.Pet getModel() {
		return petModel;
	}
	
	/**
	 * obtient la vue Pet
	 * @return Pet, la vue
	 */
	public app.view.Pet getView() {
		return petView;
	}
	
	/**
	 * met a jour les states
	 */
	public void updateView() {
		
		hungerController.updateValue();
		thirstController.updateValue();
		weightController.updateValue();
		hygieneController.updateValue();
		moralController.updateValue();
		
		if ( petModel.isDead() ) {
			petView.setAnime("mort");
		}
	}
	
	@Override
	public void updateText() {
		hungerController.updateText();
		thirstController.updateText();
		weightController.updateText();
		hygieneController.updateText();
		moralController.updateText();
	}
	
	@Override
	public void exit() {
		
		petModel.exit();
		petModel = null;
		
		petView.exit();
		petView = null;
		
		hungerController.exit();
		hungerController = null;
		
		thirstController.exit();
		thirstController = null;
		
		weightController.exit();
		weightController = null;
		
		hygieneController.exit();
		hygieneController = null;
		
		moralController.exit();
		moralController = null;
	}
}
