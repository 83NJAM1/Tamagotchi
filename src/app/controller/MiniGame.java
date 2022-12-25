package app.controller;

public class MiniGame {

	app.model.MiniGame miniGameModel;
	app.view.ActionBarThrowStick miniGameView;
	
	public MiniGame(String name, Pet pet) {
		
		switch(name){
			case "throw-stick":
				miniGameModel = new app.model.ThrowStick(pet.getModel());
				miniGameView = new app.view.ActionBarThrowStick();
				break;
		}
	}
	
	public void actionThrow() {
		((app.model.ThrowStick)miniGameModel).throwStick();
	}
	
	public app.model.MiniGame getModel(){
		return miniGameModel;
	}
	
	public app.view.ActionBarThrowStick getView(){
		return miniGameView;
	}
}
