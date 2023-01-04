package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

/**
 * Bar d'action minimale d'un mini jeu
 * @author ben
 *
 */
public class MiniGameActionBar extends HBox {
	// bouton affiche/masque états
	private Button butStop;
	
	public MiniGameActionBar() {
		butStop = new Button("button-stop");
		this.getChildren().add(butStop);
	}
	/**
	 * Utilisé par v.Hud
	 * @param e ce qui doit être déclencher par le bouton butStat
	 */
	public void setActionButtonStop(EventHandler<ActionEvent> e) {
		butStop.setOnAction(e);
	}
}
