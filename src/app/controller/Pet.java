package app.controller;

import javafx.scene.shape.Rectangle;

import java.util.Random;

import app.Componable;
import app.Localisable;
import app.view.Sprite;

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
	
	double originX;
	double originY;
	
	Random seed;
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 * @param type la chaîne de charactère identifiant le type: "cat", "dog", "rabbit" ou "robot"
	 */
	public Pet(String type) {
		
		seed = new Random();
		
		hungerController = new State("hunger");
		thirstController = new State("thirst");
		weightController = new State("weight");
		hygieneController = new State("hygiene");
		moralController = new State("moral");
		
		switch(type) {
			case "cat":
				petModel = new app.model.Animal("cat");
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Normal.png", Game.GAMEIMAGEPATH+"cat/colorPet.png");
				petView.addAnime("heureux", new int[]{0, 1, 2, 1}, new int[]{0, 1, 2, 1});
				petView.addAnime("neutre", new int[]{0, 1, 2, 1}, new int[]{4, 5, 6, 5});
				petView.addAnime("triste", new int[]{0, 1, 2, 1}, new int[]{8, 9, 10, 9});
				petView.addAnime("punis", new int[]{2}, new int[]{11});
				petView.addAnime("dort", new int[]{2, 2}, new int[]{12, 13});
				petView.addAnime("balle-1", new int[]{3}, new int[]{3});
				petView.addAnime("balle-2", new int[]{4}, new int[]{7});
				petView.addAnime("mort", new int[]{1}, new int[]{14});
				petView.addAnime("soif", new int[]{0}, new int[]{16});
				petView.addAnime("crie", new int[]{0}, new int[]{17});
				petView.addAnime("malade", new int[]{0}, new int[]{18});
				petView.setAnime("neutre");
				petView.play();
				break;
			case "dog":
				petModel = new app.model.Animal("dog");
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Normal.png", Game.GAMEIMAGEPATH+"dog/colorPet.png");
				petView.addAnime("heureux-couche", new int[]{0}, new int[]{0});
				petView.addAnime("heureux", new int[]{1, 2}, new int[]{1, 2});
				petView.addAnime("heureux-debout", new int[]{3, 4}, new int[]{3, 4});
				
				petView.addAnime("neutre-couche", new int[]{0}, new int[]{6});
				petView.addAnime("neutre", new int[]{1, 2}, new int[]{7, 8});
				petView.addAnime("neutre-debout", new int[]{3, 4}, new int[]{9, 10});
				
				petView.addAnime("triste-couche", new int[]{0}, new int[]{12});
				petView.addAnime("triste", new int[]{1, 2}, new int[]{13, 14});
				petView.addAnime("triste-debout", new int[]{3, 4}, new int[]{15, 16});
				
				petView.addAnime("punis", new int[]{2}, new int[]{17});
				petView.addAnime("dort", new int[]{0, 0}, new int[]{23, 29});
				petView.addAnime("balle-1", new int[]{3}, new int[]{5});
				petView.addAnime("balle-2", new int[]{5}, new int[]{11});
				petView.addAnime("mort", new int[]{0}, new int[]{22});
				
				petView.addAnime("soif-couche", new int[]{0}, new int[]{18});
				petView.addAnime("soif", new int[]{1}, new int[]{19});
				petView.addAnime("soif-debout", new int[]{3}, new int[]{20});
				
				petView.addAnime("crie-couche", new int[]{0}, new int[]{24});
				petView.addAnime("crie", new int[]{1}, new int[]{25});
				petView.addAnime("crie-debout", new int[]{3}, new int[]{26});
				
				petView.addAnime("malade-couche", new int[]{0}, new int[]{21});
				petView.addAnime("malade", new int[]{1}, new int[]{27});
				petView.addAnime("malade-debout", new int[]{3}, new int[]{28});
				
				petView.setAnime("neutre");
				petView.play();
				break;
			case "robot":
				petModel = new app.model.Robot();
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"robot/Animation_Robot.png", Game.GAMEIMAGEPATH+"robot/colorPet.png");
				petView.addAnime("heureux", new int[]{0, 1}, new int[]{0, 1});
				petView.addAnime("neutre", new int[]{0, 1}, new int[]{4, 5});
				petView.addAnime("triste", new int[]{0, 1}, new int[]{8, 9});
				petView.addAnime("punis", new int[]{4}, new int[]{6});
				petView.addAnime("dort", new int[]{4}, new int[]{12, 13});
				petView.addAnime("balle-1", new int[]{2}, new int[]{2});
				petView.addAnime("balle-2", new int[]{3}, new int[]{3});
				petView.addAnime("mort", new int[]{4}, new int[]{7});
				petView.addAnime("soif", new int[]{0}, new int[]{10});
				petView.addAnime("crie", new int[]{0}, new int[]{11});
				petView.addAnime("malade", new int[]{4}, new int[]{14});
				
				petView.setAnime("neutre");
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
		
		petView.setPos(originX, originY);
		petView.setObject(null);
		
		hungerController.updateValue();
		thirstController.updateValue();
		weightController.updateValue();
		hygieneController.updateValue();
		moralController.updateValue();
		
		if ( petModel.isDead() ) {
			petView.setAnime("mort");
		}
		else if ( petModel.isSleeping() ) {
			petView.setAnime("dort");
			System.out.println(petModel.getEnergy().getValue());
		}
		else if ( petModel.isThirsty() ) {
			petView.setAnime("soif");
		}
		else if ( petModel.isSick() ) {
			petView.setAnime("malade");
		}
		else if ( petModel.isPunish() ) {
			petView.setAnime("punis");
		}
		else if ( petModel.isPlaying() ) {
			
			if ( petModel.isCatching() ) {
				petView.setAnime("balle-1");
				petView.setObject(new Sprite("res/game/images/objects/object.png", 0, 0, 64, 64));
				
			}
			else if ( petModel.isFetching() ) {
				petView.setAnime("balle-2");
				petView.setPos(originX+seed.nextDouble(-petView.getDestW(), petView.getDestW()), 
							   originY+seed.nextDouble(-petView.getDestH()/2,petView.getDestH())/2); //*
			}
			
		}
		else if ( petModel.isCrying() ) {
			petView.setAnime("crie");
		}
		else if ( petModel.isHappy() ) {
			petView.setAnime("heureux");
		}
		else if ( petModel.isSad() ) {
			petView.setAnime("triste");
		}
		else {
			petView.setAnime("neutre");
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
	
	public void saveOrigin() {
		originX = petView.getDestX();
		originY = petView.getDestY();
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
