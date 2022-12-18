package app.controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
	
	//############################ METHODES #####################################
	
	public Menu() {
		model = new app.model.Option("res/config.txt");
		view = new app.view.Menu();
	}
	
	public void setVolume(Double value) {
		model.setVolume(value);
	}
	public Double getVolume() {
		return model.getVolume();
	}
	
	public void loadOption() {
		model.load();
		if ( model.getLanguage().equals( Locale.ENGLISH.toString() ) ) {
			App.language = ResourceBundle.getBundle("language", Locale.ENGLISH);
			App.languageNumber = NumberFormat.getNumberInstance(Locale.ENGLISH);
		}
		else {
			App.language = ResourceBundle.getBundle("language", Locale.FRENCH);
			App.languageNumber = NumberFormat.getNumberInstance(Locale.FRENCH);
		}
		
		if ( model.getWindowWidth() <= 640 )
			view.getOption().setSelectedDim(0);
		else
			view.getOption().setSelectedDim(1);
		
		view.getOption().setVolumeValue(model.getVolume());
		
		System.out.println("loading option:\n" + model.toString().indent(4));
	}
	
	public int getChoosenDim() {
		return view.getOption().getChoosenDim();
	}
	
	public app.model.Option getOption(){
		return model;
	}
	
	public app.view.Menu getView(){
		return view;
	}
	
	public void exit() {
		model = null;
		view = null;
	}
}
