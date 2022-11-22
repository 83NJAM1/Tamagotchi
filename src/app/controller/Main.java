package app.controller;

import java.util.Date;

import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	
	//########################### ATTRIBUTS #####################################
	
	private app.view.Main view;
	private app.model.Save save;
	
	private Menu menu;
	private Game game;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionLoop effectué toute les 1 second (1e+9 ns)
	 * déclendeur -> this
	 */ 
	private AnimationTimer actionLoop = new AnimationTimer() {
		int n_access=0; // compteur d'acces au fichier de sauvegarde
		long old_time=0;
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+(1<<30); // aproximativement une seconde
				save.addData("Accès n°" + n_access++ + "\n");
				System.out.print(save.getData("test")); // ne pas utiliser println pour des raisons obscure
			}
        }
    };
	
	/**
	 * ActionEvent effectué quand t-on veut charger une partie
	 * déclencheur -> c.Menu -> v.Menu -> v.Load
	 */
	private EventHandler<ActionEvent> load_file = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			System.out.println("Loading... : " + menu.getView().getLoad().getChoosenSave());
			menu.getView().closeLoad();
		}
	};

	//############################ METHODES #####################################

	public Main() {
		save = new app.model.Save("./res/save.tmg");
		menu = new Menu();
		game = new Game();
		view = new app.view.Main( game.getView(), menu.getView() );
		
		menu.getView().getLoad().setValidateAction(load_file);
		actionLoop.start();
	}
	
	public app.view.Main getView() {
		return view;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Game getGame() {
		return game;
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 */
	public void exit() {
		save.saveToDisk();
	}
}
