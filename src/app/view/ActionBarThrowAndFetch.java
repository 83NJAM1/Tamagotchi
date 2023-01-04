package app.view;

import app.App;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Bar d'action pour mini jeu
 * @author ben
 *
 */
public class ActionBarThrowAndFetch extends MiniGameActionBar {

	// bouton affiche menu
	private Button butThrow;
	
	/**
	 * constructeur
	 */
	public ActionBarThrowAndFetch() {
		super();
		butThrow = new Button("button-throw");
		this.getChildren().addAll(butThrow);
		updateText();
	}
	
	@Override
	public void updateText() {
		super.updateText();
		if ( butThrow != null )
			butThrow.setText(App.getString("button-throw"));
	}
	
	/**
	 * définit l'action pour le bouton lancer
	 * @param e ce qui doit être déclencher par le bouton butMenu
	 */
	public void setActionButtonThrow(EventHandler<ActionEvent> e) {
		butThrow.setOnAction(e);
	}
}
