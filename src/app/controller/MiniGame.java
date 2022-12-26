package app.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class MiniGame {

	app.model.MiniGame miniGameModel;
	app.view.MiniGameActionBar miniGameView;
	
	public MiniGame(String name, Pet pet) {
		
		switch(name){
			case "throw-stick":
				miniGameModel = new app.model.ThrowAndFetch(pet.getModel());
				miniGameView = new app.view.ActionBarThrowAndFetch();
				break;
		}
	}
	
	public void actionThrow() {
		if ( miniGameModel.getIdName().equals("throw-and-fetch"))
			((app.model.ThrowAndFetch)miniGameModel).throwStick();
	}
	public void setActionThrow(EventHandler<ActionEvent> e) {
		if ( miniGameModel.getIdName().equals("throw-and-fetch"))
			((app.view.ActionBarThrowAndFetch)miniGameView).setActionButtonThrow(e);
	}
	
	public app.model.MiniGame getModel(){
		return miniGameModel;
	}
	
	public app.view.MiniGameActionBar getView(){
		return miniGameView;
	}
}
