package app.view;

import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * 
 * @author ben
 * affiche les stats ainsi que les actions
 */
public class Hud extends AnchorPane{
	
	//########################### ATTRIBUTS #####################################

	// Barre des actions pour le moment car plus simple a implémenter
	// les futures version pourront implémenter le clique sur objets
	private Action actionBar;
	private VBox statsBox;

	// ATTENTION: references partagées avec controller.Stat
	private ArrayList<Stat> stats;
	
	
	//######################### EVENT-ACTION ####################################
	
	private EventHandler<ActionEvent> click_stat = new EventHandler<ActionEvent>() {
		boolean visible = true;
		public void handle(ActionEvent e) {
			visible=!visible;
			statsBox.setVisible(visible);
		}
	};
	
	//############################ METHODES #####################################
	
	public Hud(Stat ... stats_instances) {
		this.actionBar = new Action();
		this.statsBox = new VBox();
		this.stats = new ArrayList<>();
		
		for (Stat s : stats_instances) {
			stats.add(s);
			statsBox.getChildren().add(s);
		}
		
		actionBar.setActionStat(click_stat);
		
		this.getChildren().addAll(actionBar, statsBox);
		updateStyle();
	}
	
	public void updateText() {
		actionBar.updateText();
	}
	
	public void updateStyle() {
		AnchorPane.setTopAnchor(statsBox, 10.);
		AnchorPane.setLeftAnchor(statsBox, 10.);
		AnchorPane.setBottomAnchor(actionBar, 10.);
		AnchorPane.setLeftAnchor(actionBar, 10.);
	}
	
	public Action getActionBar() {
		return actionBar;
	}
	
	public void hideStats() {
		statsBox.setVisible(false);;
	}
}
