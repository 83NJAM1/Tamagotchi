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
	
	
	//############################ METHODES #####################################
	
	public Menu() {
		model = new app.model.Option();
		view = new app.view.Menu();
	}
	
	public void setVolume(Double value) {
		model.setVolume(value);
	}
	public Double getVolume() {
		return model.getVolume();
	}
	
	public app.model.Option getModel(){
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
