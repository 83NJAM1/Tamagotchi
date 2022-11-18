package app.view;

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

import java.util.Locale;

import app.App;
/**
 * 
 * @author ben
 * view.Option est de type HBox car l'affichage d'option
 * est souvant représenté sous forme de liste
 */
public class Option extends VBox {
	
	private Button buttonQuit;
	private ChoiceBox<String> choiceLang;
	private Slider volume;
	private ChoiceBox<String> choiceDimWindow;
	
	public Option() {
		super(15);
		buttonQuit = new Button();
		choiceLang = new ChoiceBox<String>();
		volume = new Slider(0.0, 1.0, 0.5);
		choiceDimWindow = new ChoiceBox<String>();
		
		choiceDimWindow.setValue("640x360");
		choiceDimWindow.getItems().addAll("640x360", "1280x720");
		
		updateText();
		updateBuild();
	}
	
	public void updateText() {
		buttonQuit.setText(App.language.getString("option-button-quit"));
		choiceLang.getItems().clear();
		choiceLang.getItems().addAll(App.language.getString("option-choice-fr"), App.language.getString("option-choice-en"));
		
		if ( App.language.getLocale() == Locale.ENGLISH ) {
			choiceLang.setValue(App.language.getString("option-choice-en"));
		}
		else {
			choiceLang.setValue(App.language.getString("option-choice-fr"));
		}
		
	}
	
	public void updateBuild() {
		this.setAlignment(Pos.CENTER);
		this.setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
		this.setMinWidth(200.0);
		this.getChildren().addAll(choiceDimWindow, choiceLang, volume, buttonQuit);
	}
	
	public void setQuitAction(EventHandler<ActionEvent> e) {
		buttonQuit.setOnAction(e);
	}
	
	public void setVolumeAction(ChangeListener<?super Number> n) {
		volume.valueProperty().addListener(n);
	}
	
	public void setLangAction(EventHandler<ActionEvent> e) {
		choiceLang.setOnAction(e);
	}
	
	public int getChoosenLang() {
		return choiceLang.getSelectionModel().getSelectedIndex();
	}
	
	public void setDimensionAction(EventHandler<ActionEvent> e) {
		choiceDimWindow.setOnAction(e);
	}
	
	public int getChoosenDim() {
		return choiceDimWindow.getSelectionModel().getSelectedIndex();
	}
}
