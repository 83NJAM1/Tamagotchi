package app.controller;

import app.model.*;
import app.model.states.*;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import java.util.List;

public class Controler {
	
	private static app.view.View mainView;
	
	private static AnimationTimer timerRefreshCanvas;
	
	private static List<Action> lastEnabledActions;

	public static void start() {
		

	    
	    timerRefreshCanvas = new AnimationTimer() {
			long old_time=0;
			@Override
	        public void handle(long new_time) {
				if (new_time > old_time ) {
					old_time = new_time+(4*16700000);
					updateCanvas();
				}
	        }
	    };
	    
	    
		mainView = new app.view.View(640, 480);
				
		updateCanvas();
		
		timerRefreshCanvas.start();
	}
	
	public static void boutonClicked(String eventCode) {
		lastEnabledActions = null;
		Action.get(eventCode).execute();
	}
	
	public static void contextChange(String s) {
		Action.setContext(s);
	}
	
	public static void contextChange(int n) {
		Action.setContext(n);
	}
	
	
	public static void updateCanvas() {
		Game.refresh();
		List<Action> actions = Game.getEnabledActions();
		Pet pet = Game.getSelectedPet();
		
		if(!actions.equals(lastEnabledActions)) {
			lastEnabledActions=actions;
			mainView.clearBoutons();
			
			int top;
			int left;
			int index;
			for(Action action : actions) {
				switch(action) {
					case fridgeSlot:
						top=240;
						left=20;
						index=0;
						for(Ingredient ingredient : pet.getFridge()) {
							if(ingredient!=null) mainView.addBouton(top,left,Text.call(ingredient),action.name(),index);
							index++;
							top+=30;
						}
						break;
					case ingredient:
						top=280;
						left=220;
						index=0;
						for(Ingredient ingredient : pet.getIngredients()) {
							if(ingredient!=null) mainView.addBouton(top,left,Text.call(ingredient),action.name(),index);
							index++;
							top+=30;
						}
						break;
					case doNothing:
							break;
						default:
							mainView.addBouton(30*(action.ordinal()/4),160*(action.ordinal()%4),Text.call(action),action.name(),-1);
				}
			}
		
		}
				
		if(Game.is(Stage.playing)) {
		
		String stats="";
		if(pet.getStats()) {
			for(State state : pet.getStateList()) {
				String percent = Double.toString(state.getValue()*100);
				stats+=state.getText()+" : "+(percent.contains("E")?"0":percent.substring(0,percent.length()>5?6:percent.length()))+" %\n";
			}
		}
		mainView.setText(stats);
		
		}
	}
	
	public static Pane getRoot() {
		
		return mainView;
	}
}
