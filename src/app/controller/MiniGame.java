package app.controller;

import app.view.Sprite;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MiniGame {

	app.model.MiniGame miniGameModel;
	app.view.MiniGameActionBar miniGameView;
	Pet pet;
	
	public MiniGame(String name, Pet pet) {
		
		switch(name){
			case "throw-and-fetch":
				this.pet = pet;
				miniGameModel = new app.model.ThrowAndFetch(pet.getModel());
				miniGameView = new app.view.ActionBarThrowAndFetch();
				break;
		}
	}
	
	public void actionThrow() {
		if ( miniGameModel.getIdName().equals("throw-and-fetch")) {
			((app.model.ThrowAndFetch)miniGameModel).throwObject();
			//pet.getView().setObject(null);
		}
	}
	public void setActionThrow(EventHandler<ActionEvent> e) {
		if ( miniGameModel.getIdName().equals("throw-and-fetch"))
			((app.view.ActionBarThrowAndFetch)miniGameView).setActionButtonThrow(e);
	}
	
	public void updateView() {
		if ( miniGameModel.getIdName().equals("throw-and-fetch")) {
			/*if ( ((app.model.ThrowAndFetch)miniGameModel).getProgress() >= 1.0 ) {
				pet.getView().setObject(new Sprite("res/game/images/objects/object.png", 0, 0, 64, 64));
			}*/
		}
	}
	
	public app.model.MiniGame getModel(){
		return miniGameModel;
	}
	
	public app.view.MiniGameActionBar getView(){
		return miniGameView;
	}
}
