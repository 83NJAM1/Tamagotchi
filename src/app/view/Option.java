package app.view;

import java.util.Locale;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;

import app.App;

/**
 * 
 * @author ben
 * view.Option est de type HBox car l'affichage d'option
 * est souvant représenté sous forme de liste
 */
public class Option extends VBox {
	
	//########################### ATTRIBUTS #####################################

	private Button buttonQuit;
	private ChoiceBox<String> choiceLang;
	private Slider volume;
	private ChoiceBox<String> choiceDimWindow;
	
	//############################ METHODES #####################################
	
	public Option() {
		
		// instanciation
		super(15);
		buttonQuit = new Button();
		choiceLang = new ChoiceBox<String>();
		volume = new Slider(0.0, 1.0, 0.5);
		choiceDimWindow = new ChoiceBox<String>();
		
		// initialisation
		choiceDimWindow.getItems().addAll("640x360", "1280x720");
		choiceDimWindow.setValue(choiceDimWindow.getItems().get(0));
		
		updateText();
		updateStyle();

		// construction
		this.getChildren().addAll(choiceDimWindow, choiceLang, volume, buttonQuit);
	}
	
	/**
	 * Met à jour le texte de tous les élements
	 */
	public void updateText() {
		buttonQuit.setText(App.language.getString("button-quit"));
		choiceLang.getItems().clear();
		choiceLang.getItems().addAll(App.language.getString("choice-fr"), App.language.getString("choice-en"));
		
		if ( App.language.getLocale() == Locale.ENGLISH ) {
			choiceLang.setValue(App.language.getString("choice-en"));
		}
		else {
			choiceLang.setValue(App.language.getString("choice-fr"));
		}
		
	}
	
	/**
	 * Met à jour le style de tous les élements
	 */
	public void updateStyle() {
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
	}
	
	public void setSelectedDim(int i) {
		choiceDimWindow.setValue(choiceDimWindow.getItems().get(i));
	}
	public void setVolumeValue(double v) {
		volume.setValue(v);
	}
	
	/**
	 * Appelant <- v.Menu
	 * @param e ActionEvent à déclencher pour masquer cette vue
	 */
	public void setQuitAction(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu
	 * @param n ActionChange à déclencher pour obtenir la nouvelle valeur
	 */
	public void setVolumeAction(ChangeListener<?super Number> n) {
		volume.valueProperty().addListener(n);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu
	 * @param e ActionEvent à déclencher pour mettre à jour la langue
	 */
	public void setLangAction(EventHandler<ActionEvent> e) {
		choiceLang.setOnAction(e);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu <- c.Main <- App 
	 * @param e ActionEvent à déclencher pour mettre à jour la définition
	 */
	public void setDimensionAction(EventHandler<ActionEvent> e) {
		choiceDimWindow.setOnAction(e);
	}
	
	/**
	 * Permet de déterminer quelle langue à été choisie
	 * Appelant <- v.Menu <- c.Menu 
	 * @return l'index selectionné
	 */
	public int getChoosenLang() {
		return choiceLang.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Permet d'obtenir le nom de la langue choisie
	 * @return le nom de la langue
	 */
	public String getChoosenLangValue() {
		return choiceLang.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * Permet de déterminer quelle définition à été choisi
	 * Appelant <- v.Menu <- c.Menu <- c.Main <- App
	 * @return l'index selectionné
	 */
	public int getChoosenDim() {
		return choiceDimWindow.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Permet de déterminer le nom de la définition choisi
	 * @return le nom de la définition
	 */
	public String getChoosenDimValue() {
		return choiceDimWindow.getSelectionModel().getSelectedItem();
	}
}
