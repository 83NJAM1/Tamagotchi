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
	private ChoiceBox<String> choiceLanguage;
	private Slider sliderVolume;
	private ChoiceBox<String> choiceWindowDefinition;
	
	//############################ METHODES #####################################
	
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
	 * Met à jour le texte de tous les élements
	 */
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
	
	/**
	 * Met à jour le style de tous les élements
	 */
	public void updateStyle() {
		this.setAlignment(Pos.CENTER);
		this.setSpacing(15);
		this.setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
	}
	
	public void setSelectedChoiceDefinition(int index) {
		choiceWindowDefinition.setValue(choiceWindowDefinition.getItems().get(index));
	}
	
	public void setVolumeValue(double value) {
		sliderVolume.setValue(value);
	}
	
	/**
	 * Appelant <- v.Menu
	 * @param e ActionEvent à déclencher pour masquer cette vue
	 */
	public void setActionButtonQuit(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu
	 * @param n ActionChange à déclencher pour obtenir la nouvelle valeur
	 */
	public void setActionSliderVolume(ChangeListener<?super Number> n) {
		sliderVolume.valueProperty().addListener(n);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu
	 * @param e ActionEvent à déclencher pour mettre à jour la langue
	 */
	public void setActionChoiceBoxLanguage(EventHandler<ActionEvent> e) {
		choiceLanguage.setOnAction(e);
	}
	
	/**
	 * Appelant <- v.Menu <- c.Menu <- c.Main <- App 
	 * @param e ActionEvent à déclencher pour mettre à jour la définition
	 */
	public void setActionChoiceBoxDefinition(EventHandler<ActionEvent> e) {
		choiceWindowDefinition.setOnAction(e);
	}
	
	/**
	 * Permet de déterminer quelle langue à été choisie
	 * Appelant <- v.Menu <- c.Menu 
	 * @return l'index selectionné
	 */
	public int getChoosenLanguageIndex() {
		return choiceLanguage.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Permet d'obtenir le nom de la langue choisie
	 * @return le nom de la langue
	 */
	public String getChoosenLanguageValue() {
		return choiceLanguage.getSelectionModel().getSelectedItem();
	}
	
	/**
	 * Permet de déterminer quelle définition à été choisi
	 * Appelant <- v.Menu <- c.Menu <- c.Main <- App
	 * @return l'index selectionné
	 */
	public int getChoosenDefinitionIndex() {
		return choiceWindowDefinition.getSelectionModel().getSelectedIndex();
	}
	
	/**
	 * Permet de déterminer le nom de la définition choisi
	 * @return le nom de la définition
	 */
	public String getChoosenDefinitionValue() {
		return choiceWindowDefinition.getSelectionModel().getSelectedItem();
	}
	
	public void exit() {
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
