package app.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import app.App;
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * permet l'affichage d'un état représenter par une JAUGE et un NOM
 */
public class State extends VBox implements Cleanable, Localisable, Stylable {
	
	Label name;
	Label valueTxt;
	ProgressBar valueBar;
	StackPane fieldValue;
	String key;
	
	/**
	 * constructeur
	 * @param key la chaîne de charactère identifiant le nom à donner via la localisation
	 */
	public State(String key) {
		this.key = key;
		name = new Label(App.getString(key));
		valueTxt = new Label("0.0");
		valueBar = new ProgressBar();
		fieldValue = new StackPane();
		
		fieldValue.getChildren().addAll(valueBar, valueTxt);
		this.getChildren().addAll(name, fieldValue);
	}
	
	/**
	 * change la valeur affichée
	 * @param value la nouvelle valeur
	 */
	public void changeValue(Double value) {
		valueTxt.setText(App.getString(value));
		valueBar.setProgress(value);
	}
	
	@Override
	public void updateStyle() {
	}
	
	@Override
	public void updateText() {
		name.setText(App.getString(key));
	}
	
	@Override
	public void clean() {
		name = null;
		valueTxt = null;
		valueBar = null;
		fieldValue = null;
	}
}
