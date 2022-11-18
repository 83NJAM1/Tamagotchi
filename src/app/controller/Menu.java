package app.controller;

import java.util.Locale;
import java.util.ResourceBundle;

import app.App;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le model
 */
public class Menu {
	
	private app.model.Option model;
	
	//ATTENTION: reference partagé avec view.Main
	private app.view.Menu view;
	
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			System.out.println(vold + " to " + vnew);
		}
	};
	
	private EventHandler<ActionEvent> choose_lang = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( view.getOption().getChoosenLang() == 0) {
				App.language = ResourceBundle.getBundle("language", Locale.FRENCH);
			}
			else {
				App.language = ResourceBundle.getBundle("language", Locale.ENGLISH);
			}
			
			System.out.println("locale:" + App.language.getLocale());
			view.updateText();
		}
	};
	
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
