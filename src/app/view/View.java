package app.view;

import app.controller.Controler;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

/*
 * Vue primitive.
 * 
 * L'essentiel du dévelopement restant concerne la vue
 * 
 * Il faudra entre autre faire un système de scènes à afficher/cacher, faire des classes de boutons décentes,
 * introduire un vrai systeme de positionement, rajouter un canvas, faire une boite à dialogue, etc...
 * 
 * La plupart de ce qui est dans ce main vue doit à terme être changé/bougé
 * 
 */

public class View extends Pane {
	
	private Pane boutonsPane;
	
	//Espaces de textes pour les stats, le stade de la partie et les infos sur le pet
	private Text textStats;
	private Text textStage;
	private Text textInfos;
	
	//Input texte
	TextField textField;
	
	
	public View(double width, double height) {
		super();
		
		//Espace reservé pour mettre/actualiser les boutons
		boutonsPane = new Pane();
		
		this.setBackground(new Background( new BackgroundFill(Color.BEIGE, null, null) ));		
		
		textStats = new Text();
		textStats.setTranslateX(470);
		textStats.setTranslateY(250);

		textStage = new Text();
		textStage.setTranslateX(0);
		textStage.setTranslateY(250);

		textInfos = new Text();
		textInfos.setTranslateX(350);
		textInfos.setTranslateY(250);
		
		textField = new TextField();
		
		this.getChildren().addAll(textStats,textStage,textInfos,boutonsPane,textField);
		
		
		

	}
	
	
	
	//Fonctions de gestion de l'input texte, elles devront être changées et bougées dans une classe indépendante
	
	public void setTextField(int top, int left, String eventCode) {
		this.getChildren().remove(textField);
		textField = new TextField();

		textField.textProperty().addListener((observable, oldValue, newValue) -> {
		    Controler.contextChange(newValue);
		    Controler.boutonClicked(eventCode);
		});
		
		this.getChildren().add(textField);
		
		textField.setTranslateX(left);
		textField.setTranslateY(top);
			
			
		}
	
	public void setTextFieldContent(String content) {
		if(!content.equals(textField.getText())) textField.setText(content);
		showTextField();
	}
	
	public void hideTextField() {
		textField.setVisible(false);
	}
	
	public void showTextField() {
		textField.setVisible(true);
	}
	
	
	
	//Fonctions de gestion des boutons
	
	public void addBouton(int top, int left, String content, String eventCode, int context) {
		
		Bouton button = new Bouton(content,eventCode,context);
		boutonsPane.getChildren().add(button);
		button.setPosition(top,left);
		
		
	}
	
	public void clearBoutons() {
			boutonsPane.getChildren().clear();
		}
	
	
	//Setter des champs de texte
		
	public void setText(String s1,String s2,String s3) {
			textStats.setText(s1);
			textStage.setText(s2);
			textInfos.setText(s3);
		
			
		}


	
	

}
