package app.controller;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import app.App;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le optionModel
 */
public class Menu {
	 
	//########################### ATTRIBUTS #####################################
	
	private app.model.Option optionModel;
	
	//ATTENTION: reference partagé avec view.Main
	private app.view.Menu menuView;
	
	//############################ METHODES #####################################
	
	public Menu() {
		optionModel = new app.model.Option("res/config.txt");
		menuView = new app.view.Menu();
	}
	
	public void setVolume(Double value) {
		optionModel.setVolume(value);
	}
	public Double getVolume() {
		return optionModel.getVolume();
	}
	
	public void loadOption() {
		optionModel.load();
		if ( optionModel.getLanguage().equals( Locale.ENGLISH.toString() ) ) {
			App.setLanguage(Locale.ENGLISH);
		}
		else {
			App.setLanguage(Locale.FRENCH);
		}
		
		if ( optionModel.getWindowWidth() <= 640 )
			menuView.getOption().setSelectedDim(0);
		else
			menuView.getOption().setSelectedDim(1);
		
		menuView.getOption().setVolumeValue(optionModel.getVolume());
		
		System.out.println("loading option:\n" + optionModel.toString().indent(4));
	}
	
	public int getChoosenDim() {
		return menuView.getOption().getChoosenDim();
	}
	
	public app.model.Option getOption(){
		return optionModel;
	}
	
	public app.view.Menu getView(){
		return menuView;
	}
	
	public void exit() {
		optionModel = null;
		menuView = null;
	}
}
