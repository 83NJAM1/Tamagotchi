package app.controller;

import app.model.*;
import app.model.states.*;
import javafx.animation.AnimationTimer;
import javafx.scene.layout.Pane;
import java.util.List;

public class Controler {
	
	//Vue
	private static app.view.View mainView;
	
	//Timer (mal nommé pour l'instant puisqu'il n'y a pas encore de canvas) d'actualisation de l'affichage
	private static AnimationTimer timerRefreshCanvas;
	
	//Mémoire permettant de savoir s'il faut réinitialiser l'affichage des boutons (sinon on le ferait à chaque frame).
	//Si on opte pour une approche où tous les boutons sont créés d'office, elle ne servira plus.
	private static List<Action> lastEnabledActions;

	//Initialisation du controler
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
	
	/*
	Triplé de fonctions suffisant à gérer tout le modèle.
	Aucune autre interaction avec le modèle n'est nécessaire.
	Le système est le même qu'un observable, sauf qu'au lieu d'une liste d'observable on utilise une table de Strings.
	C'est amplement suffisant pour notre projet, mais il sera toujours envisageable de passer à une version "liste" si quelqu'un a l'envie et le temps.
	*/
	
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
	
	
	/*
	 * Fonction d'actualisation de l'affichage.
	 * 
	 * Elle est pour l'instant assez bricolée dans sa forme puisque la vue n'est pas encore au point.
	 * Mais son fond est là, et il n'y aura besoin que de quelques modifications pour s'adapter à de nouvelles fonctionnalités du modèle.
	 * 
	 */
	
	
	public static void updateCanvas() {
		//Actualisation du modèle
		Game.refresh();
		//Récupération des actions possibles.
		List<Action> actions = Game.getEnabledActions();
		Pet pet = Game.getSelectedPet();
		
		//Création si besoin des boutons correspondant aux actions.
		//Ce n'est qu'un premier jet qui n'aura rien à voir dans la forme avec la version finale, mais qui fera dans l'idée globale la mêmle chose.
		if(!actions.equals(lastEnabledActions)) {
			lastEnabledActions=actions;
			mainView.clearBoutons();
			
			//Position des boutons.
			//Pour la version finale, il faudra réfléchir à utiliser plutôt une position en pourcentage ou une grille.
			//Il faudrait aussi répertorier les positions (et les images) des boutons dans une fonction/classe/énum pour une meilleure lisibilité.
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
					case selectPet:
						top=280;
						left=80;
						index=0;
						for(Pet pets : Game.getPetList()) {
							mainView.addBouton(top,left,pets.getName(),action.name(),index);
							index++;
							top+=30;
						}
						break;
					
					case removePet:
						top=280;
						left=210;
						index=0;
						for(;index<Game.getPetList().size();index++) {
							mainView.addBouton(top,left,Text.call(action),action.name(),index);
							top+=30;
						}
						break;
					
					case changeSpecie:
						top=280;
						left=20;
						index=0;
						for(Specie specie : Specie.values()) {
							if(specie!=Specie.getDefault()) mainView.addBouton(top,left,Text.call(specie),action.name(),index);
							index++;
							top+=30;
						}
						break;
					
					case changeLanguage:
						top=320;
						left=520;
						index=0;
						for(Language language : Language.values()) {
							if(language!=Language.getCurrentLanguage()) mainView.addBouton(top,left,language.call(),action.name(),index);
							index++;
							top+=30;
						}
						break;
					
					case startCreation:
						mainView.setTextField(320, 240, Action.changeName.name());
						mainView.addBouton(30*(action.ordinal()/4),160*(action.ordinal()%4),Text.call(action),action.name(),-1);
						break;
					
					case changeName:
					case changeColor:
					case changePet:
					case doNothing:
							break;
					default:
						mainView.addBouton(30*(action.ordinal()/4),160*(action.ordinal()%4),Text.call(action),action.name(),-1);
				}
			}
			//Gestion de l'input texte. Idem, à adapter plus tard une fois la vue bien faite.
			if(Game.is(Stage.creation)) mainView.setTextFieldContent(pet.getName());
			else mainView.hideTextField();
		
		}
		
		//Gestion des champs de texte. Idem, à adapter plus tard une fois la vue bien faite.
		String stats = "";
		String stage = "";
		String infos = "";
		
				
		if(Game.is(Stage.playing)) {
		
			if(pet.getStats()) {
				for(State state : pet.getStateList()) {
					String percent = Double.toString(state.getValue()*100);
					stats+=Text.call(state)+" : "+(percent.contains("E")?"0":percent.substring(0,percent.length()>5?6:percent.length()))+" %\n";
				}
			}
			
			long age = pet.getAge();
			infos+=((age/(1000*60*60*24))>0?+age/(1000*60*60*24)+"d ":"")
					+String.format("%02d:%02d:%02d.%d", (age / (1000 * 60 * 60)) % 24, (age / (1000 * 60)) % 60, (age / 1000) % 60, age % 1000)
					+"\n  (cycle "+pet.getCycle()+")"
					+"\n\n"+pet.getName()+"\n  ("+Text.call(pet.getSpecie())+")\n\n"+Text.call(pet.getRoom())
					+"\n\n"+Text.call(pet.getWeather());
		
		}
		else stage="--------------------------------------------------------"+Text.call(Game.getStage())
				+"--------------------------------------------------------";
		
		mainView.setText(stats,stage,infos);

	}
	
	public static Pane getRoot() {
		
		return mainView;
	}
}
