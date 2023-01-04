package app.controller;

import javafx.scene.shape.Rectangle;

import java.util.Random;

import app.Cleanable;
import app.Localisable;
import app.view.Sprite;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Pet implements Cleanable, Localisable {
	
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
	
	String currentSkin;
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
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Normal.png", Game.GAMEIMAGEPATH+"cat/Coloriage_Chat_Normal.png");
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
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Normal.png", Game.GAMEIMAGEPATH+"dog/Coloriage_Chien_Normal.png"); 
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
				petView = new app.view.Pet(Game.GAMEIMAGEPATH+"robot/Animation_Robot.png", Game.GAMEIMAGEPATH+"robot/Coloriage_Robot.png");
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
		
		petView.setViewHunger(hungerController.getView());
		petView.setViewThirst(thirstController.getView());
		petView.setViewWeight(weightController.getView());
		petView.setViewHygiene(hygieneController.getView());
		petView.setViewMoral(moralController.getView());
		
		currentSkin="";
		
		updateSkin();
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
	
	public void setFat() {
		switch(petModel.getType()) {
			case "cat":
				petView.setSheet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Gros.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"cat/colorPetFat.png");
				break;
			case "dog":
				petView.setSheet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Gros.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"dog/colorPetFat.png");
				break;
			case "rabbit":
				petView.setSheet(Game.GAMEIMAGEPATH+"rabbit/Animation_Lapin_Gros.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"rabbit/colorPetFat.png");
				break;
		}
	}
	
	public void setFit() {
		switch(petModel.getType()) {
			case "cat":
				petView.setSheet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Normal.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"cat/colorPet.png");
				break;
			case "dog":
				petView.setSheet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Normal.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"dog/colorPet.png");
				break;
			case "rabbit":
				petView.setSheet(Game.GAMEIMAGEPATH+"rabbit/Animation_Lapin_Normal.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"rabbit/colorPet.png");
				break;
		}
	}
	
	public void setThin() {
		switch(petModel.getType()) {
			case "cat":
				petView.setSheet(Game.GAMEIMAGEPATH+"cat/Animation_Chat_Maigre.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"cat/colorPetThin.png");
				break;
			case "dog":
				petView.setSheet(Game.GAMEIMAGEPATH+"dog/Animation_Chien_Maigre.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"dog/colorPetThin.png");
				break;
			case "rabbit":
				petView.setSheet(Game.GAMEIMAGEPATH+"rabbit/Animation_Lapin_Maigre.png");
				petView.getColorSprite().setSheet("file:"+Main.USERPATH+"rabbit/colorPetThin.png");
				break;
		}
	}
	
	public void updateSkin(){
		if ( !currentSkin.equals("fat") && weightController.getModel().getValue() > 0.75 ) {
			currentSkin="fat";
			setFat();
		}
		else if ( !currentSkin.equals("thin") && weightController.getModel().getValue() < 0.25 ) {
			currentSkin="thin";
			setThin();
		}
		else if ( !currentSkin.equals("fit") && ( weightController.getModel().getValue() > 0.25 && weightController.getModel().getValue() < 0.75 )) {
			currentSkin="fit";
			setFit();
		}
		else if ( !currentSkin.equals("fix") && petModel.getType().equals("robot") ) {
			currentSkin="fix";
			petView.getColorSprite().setSheet("file:"+Main.USERPATH+"robot/colorPet.png");
		}
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
		
		updateSkin();
		
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
	
	public void saveOrigin() {
		originX = petView.getDestX();
		originY = petView.getDestY();
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
	public void clean() {
		
		petModel.clean();
		petModel = null;
		
		petView.clean();
		petView = null;
		
		hungerController.clean();
		hungerController = null;
		
		thirstController.clean();
		thirstController = null;
		
		weightController.clean();
		weightController = null;
		
		hygieneController.clean();
		hygieneController = null;
		
		moralController.clean();
		moralController = null;
	}
}
