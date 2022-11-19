package app.controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import app.App;
/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Menu {
	
	//########################### ATTRIBUTS #####################################
	
	private app.model.Option model;
	
	//ATTENTION: reference partagé avec view.Main
	private app.view.Menu view;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			System.out.println(vold + " to " + vnew);
		}
	};

	/**
	 * ActionEvent effectué quand t-on change la langue via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	private EventHandler<ActionEvent> choose_lang = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			Locale loc;
			if ( view.getOption().getChoosenLang() == 0) {
				loc = Locale.FRENCH;
				App.language = ResourceBundle.getBundle("language", loc);
				App.languageNumber = NumberFormat.getNumberInstance(loc);
			}
			else {
				loc = Locale.ENGLISH;
				App.language = ResourceBundle.getBundle("language", loc);
				App.languageNumber = NumberFormat.getNumberInstance(loc);
			}
			
			System.out.println("Locale:" + loc.getDisplayLanguage());
			System.out.println("Number test: " + App.languageNumber.format(25.8));
			view.updateText();
		}
	};
	
	//############################ METHODES #####################################
	
	public Menu() {
		model = new app.model.Option();
		view = new app.view.Menu();
		
		view.getOption().setVolumeAction(change_volume_value);
		view.getOption().setLangAction(choose_lang);
	}
	
	public app.model.Option getModel(){
		return model;
	}
	
	public app.view.Menu getView(){
		return view;
	}
}
