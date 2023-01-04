package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import app.App;
import app.Localisable;

/**
 * Bar d'action minimale d'un mini jeu
 * @author ben
 *
 */
public class MiniGameActionBar extends HBox implements Localisable{
	// bouton affiche/masque états
	private Button butStop;
	
	public MiniGameActionBar() {
		butStop = new Button("button-stop");
		this.getChildren().add(butStop);
		updateText();
	}
	
	@Override
	public void updateText() {
		if ( butStop != null )
			butStop.setText(App.getString("button-quit"));
	}
	
	/**
	 * Utilisé par v.Hud
	 * @param e ce qui doit être déclencher par le bouton butStat
	 */
	public void setActionButtonStop(EventHandler<ActionEvent> e) {
		butStop.setOnAction(e);
	}
}
