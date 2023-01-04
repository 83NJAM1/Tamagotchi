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
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * view.Option est de type HBox car l'affichage d'option
 * est souvant représenté sous forme de liste
 */
public class Option extends VBox implements Cleanable, Localisable, Stylable {
	
	//########################### ATTRIBUTS #####################################
 
	private Button buttonQuit;
	private ChoiceBox<String> choiceLanguage;
	private Slider sliderVolume;
	private ChoiceBox<String> choiceWindowDefinition;
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public Option() {
		
		// instanciation
		super();
		buttonQuit = new Button();
		choiceLanguage = new ChoiceBox<String>();
		sliderVolume = new Slider(0.0, 1.0, 0.5);
		choiceWindowDefinition = new ChoiceBox<String>();
		
		// initialisation
		choiceWindowDefinition.getItems().addAll("640x360", "1280x720");
		choiceWindowDefinition.setValue(choiceWindowDefinition.getItems().get(0));
		
		updateText();
		updateStyle();

		// construction
		this.getChildren().addAll(choiceWindowDefinition, choiceLanguage, sliderVolume, buttonQuit);
	}
	
	/**
	 * change la position du slider
	 * @param value la valeur compris entre 0.0 et 1.0
	 */
	public void changeVolumeValue(double value) {
		sliderVolume.setValue(value);
	}
	
	/**
	 * spécifie la definition selectionné 
	 * @param index l'indice de la définition
	 */
	public void setSelectedChoiceDefinition(int index) {
		choiceWindowDefinition.setValue(choiceWindowDefinition.getItems().get(index));
	}
	
	/**
	 * définit l'action pour le bouton quitter option
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionButtonQuit(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	/**
	 * définit l'action lorsque l'on modifie la position du slider
	 * @param c ChangeListener qui gère l'action
	 */
	public void setActionSliderVolume(ChangeListener<?super Number> c) {
		sliderVolume.valueProperty().addListener(c);
	}
	
	/**
	 * définit l'action lorsque l'on choisie une langue
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionChoiceBoxLanguage(EventHandler<ActionEvent> e) {
		choiceLanguage.setOnAction(e);
	}
	
	/**
	 * définit l'action lorsque l'on choisie une définition
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionChoiceBoxDefinition(EventHandler<ActionEvent> e) {
		choiceWindowDefinition.setOnAction(e);
	}
	
	/**
	 * obtient l'indice de langue selectionnée 
	 * @return l'indice selectionné
	 */
	public int getChoosenLanguageIndex() {
		return choiceLanguage.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * obtient le nom de la langue selectionnée
	 * @return le nom de la langue
	 */
	public String getChoosenLanguageValue() {
		return choiceLanguage.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * obtient l'indice de la définition selectionnée 
	 * @return l'indice selectionné
	 */
	public int getChoosenDefinitionIndex() {
		return choiceWindowDefinition.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * obtient le nom de la définition selectionnée
	 * @return le nom de la définition
	 */
	public String getChoosenDefinitionValue() {
		return choiceWindowDefinition.getSelectionModel().getSelectedItem();
	}
	
	@Override
	public void updateStyle() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(15);
		this.setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
	}
	
	@Override
	public void updateText() {
		buttonQuit.setText(App.getString("button-quit"));
		choiceLanguage.getItems().clear();
		choiceLanguage.getItems().addAll(App.getString("choice-fr"), App.getString("choice-en"));
		
		if ( App.getLocale() == Locale.ENGLISH ) {
			choiceLanguage.setValue(App.getString("choice-en"));
		}
		else {
			choiceLanguage.setValue(App.getString("choice-fr"));
		}
		
	}
	
	@Override
	public void clean() {
		buttonQuit.setDisable(true);
		sliderVolume.setDisable(true);
		choiceWindowDefinition.setDisable(true);
		choiceLanguage.setDisable(true);
		buttonQuit = null;
		sliderVolume = null;
		choiceWindowDefinition = null;
		choiceLanguage = null;
	}
}
