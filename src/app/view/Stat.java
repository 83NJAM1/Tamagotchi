package app.view;

import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import app.App;

/**
 * 
 * @author ben
 * Permet l'affichage d'un état représenter par une JAUGE et un NOM
 * REMARQUE: les valeurs de la JAUGE et de NOM sont transmises par le model.Stat via le controller.Stat
 */
public class Stat extends VBox {
	
	Label name;
	Label valueTxt;
	ProgressBar valueBar;
	StackPane fieldValue;
	 
	public Stat(String name) {
		
		this.name = new Label(name);
		this.valueTxt = new Label("0.0");
		valueBar = new ProgressBar();
		fieldValue = new StackPane();
		
		fieldValue.getChildren().addAll(valueBar, valueTxt);
		this.getChildren().addAll(this.name, fieldValue);
	}
	
	public void updateText(String key) {
		name.setText(App.language.getString(key));
	}
	
	public void updateStyle() {
	}
	
	public void updateValue(Double v) {
		valueTxt.setText(App.languageNumber.format(v));
		valueBar.setProgress(v);
	}
}
