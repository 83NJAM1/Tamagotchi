package app.controller;

import java.util.Locale;

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
			App.setLocale(Locale.ENGLISH);
		}
		else {
			App.setLocale(Locale.FRENCH);
		}
		
		if ( optionModel.getWindowWidth() <= 640 )
			menuView.getChildOption().setSelectedChoiceDefinition(0);
		else
			menuView.getChildOption().setSelectedChoiceDefinition(1);
		
		menuView.getChildOption().setVolumeValue(optionModel.getVolume());
		
		System.out.println("loading option:\n" + optionModel.toString().indent(4));
	}
		
	public app.model.Option getModelOption(){
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
