package app.view;
 
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * affiche les stats ainsi que les actions
 */
public class Hud extends AnchorPane implements Reinstanciable, TextDisplayable {
	
	//########################### ATTRIBUTS #####################################
 
	// barre des actions
	private ActionBar actionBar;
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
		
		actionBar.setActionButtonStat(click_stat);
		
		this.getChildren().addAll(actionBar, statsBox);
		updateStyle();
	}
	
	/**
	 * obtient la vue enfant ActionBar
	 * @return l'instance de ActionBar
	 */
	public ActionBar getChildAction() {
		return actionBar;
	}
	
	/**
	 * masque la liste des états
	 */
	public void hideStats() {
		statsBox.setVisible(false);;
	}
	
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
	public void exit() {
		stats.clear();
		actionBar = null;
		statsBox = null;
		stats = null;
	}
}
