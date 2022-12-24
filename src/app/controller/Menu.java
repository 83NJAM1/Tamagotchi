package app.controller;

import java.util.Locale;

import app.App;
import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * Permet de mettre a jour la vue avec le optionModel
 */
public class Menu implements Reinstanciable, TextDisplayable {
	 
	//########################### ATTRIBUTS #####################################
	
	// données de configuration
	private app.model.Option optionModel;
	
	
	// interface utilisateur du menu
	private app.view.Menu menuView; //NOTE: reference partagé avec view.Main
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public Menu() {
		optionModel = new app.model.Option("res/config.txt");
		menuView = new app.view.Menu();
	}
	
	/**
	 * charge les données de configuration via le model Option
	 */
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
		
		menuView.getChildOption().changeVolumeValue(optionModel.getVolume());
		
		System.out.println("loading option:\n" + optionModel.toString().indent(4));
	}
	
	/**
	 * sauvegarde les données de configuration via le model Option
	 */
	public void saveOption() {
		optionModel.save();
	}
	
	/**
	 * obtient le model Option
	 * @return Option, le model
	 */
	public app.model.Option getModelOption(){
		return optionModel;
	}
	
	/**
	 * obtient le vue Menu
	 * @return Menu, la vue
	 */
	public app.view.Menu getView(){
		return menuView;
	}
	
	@Override
	public void updateText() {
		menuView.updateText();
	}
	
	@Override
	public void exit() {
		
		optionModel = null;
		
		menuView.exit();
		menuView = null;
	}
}
