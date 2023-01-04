package app.view;
 
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * affiche les stats ainsi que les actions
 */
public class Hud extends AnchorPane implements Cleanable, Localisable, Stylable {
	
	//########################### ATTRIBUTS #####################################
 
	// barre des actions
	private ActionBar actionBar;
	private HBox actionBarMiniGame;
	
	// encart des états
	private VBox statsBox;
	// liste des états
	private ArrayList<State> stats; //NOTE: references partagées avec c.Stat et v.Pet
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action affectué quand on veut masquer les états du pet
	 * déclencheur -> v.ActionBar
	 */
	private EventHandler<ActionEvent> click_stat = new EventHandler<ActionEvent>() {
		boolean visible = true;
		public void handle(ActionEvent e) {
			visible=!visible;
			statsBox.setVisible(visible);
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 * @param stats_instances la listes de tous les états du pet
	 */
	public Hud(State ... stats_instances) {
		this.actionBar = new ActionBar();
		this.statsBox = new VBox();
		this.stats = new ArrayList<>();
		
		for (State s : stats_instances) {
			stats.add(s);
			statsBox.getChildren().add(s);
		}
		
		actionBar.setActionButtonState(click_stat);
		
		this.getChildren().addAll(actionBar, statsBox);
		updateStyle();
	}
	
	/**
	 * definit la bar d'action du mini jeu
	 * @param actionBarMiniGame
	 */
	public void setActionBarMiniGame(HBox actionBarMiniGame) {
		this.actionBarMiniGame = actionBarMiniGame;
		this.getChildren().remove(actionBar);
		this.getChildren().add(actionBarMiniGame);
		AnchorPane.setBottomAnchor(actionBarMiniGame, 10.);
		AnchorPane.setLeftAnchor(actionBarMiniGame, 10.);
	}
	
	/**
	 * supprime la bar d'action du mini jeu
	 */
	public void removeActionBarMiniGame() {
		this.getChildren().remove(actionBarMiniGame);
		this.getChildren().add(actionBar);
	}
	
	/**
	 * obtient la vue enfant ActionBar
	 * @return l'instance de ActionBar
	 */
	public ActionBar getViewAction() {
		return actionBar;
	}
	
	/**
	 * masque la liste des états
	 */
	public void hideStats() {
		statsBox.setVisible(false);;
	}
	
	@Override
	public void updateStyle() {
		AnchorPane.setTopAnchor(statsBox, 10.);
		AnchorPane.setLeftAnchor(statsBox, 10.);
		AnchorPane.setBottomAnchor(actionBar, 10.);
		AnchorPane.setLeftAnchor(actionBar, 10.);
	}
	
	@Override
	public void updateText() {
		actionBar.updateText();
	}
	
	@Override
	public void clean() {
		stats.clear();
		actionBar = null;
		statsBox = null;
		stats = null;
	}
}
