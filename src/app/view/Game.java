package app.view;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

/**
 * 
 * @author ben
 * view.Game est de type StakPane
 * Permet les opérations d'affichage concernant le jeu
 */
public class Game extends BorderPane {
	
	//########################### ATTRIBUTS #####################################
	
	// le view.pet a manipuler
	// ATTENTION reference partagé avec controller.Pet
	private Pet pet;
	
	// il y aura plusieurs room, restons avec une pour le moment
	// TODO choisir une strucutre de donnée pour stocker les Rooms
	//      ou on les liste une par une sans structure de donnée
	// ATTENTION reference partagé avec controller.Room
	private Room hall;
	private Canvas canvas;
	private GraphicsContext gc;
	private StackPane drawingArea;
	private Hud hud;
	
	//############################ METHODES #####################################
	
	public Game(Pet pet_instance, Room room_instance, Stat stats_instances) {
		pet = pet_instance;
		hall = room_instance;
		hud = new Hud(stats_instances);
		drawingArea = new StackPane();
		
		canvas = new Canvas(640, 360);
		gc = canvas.getGraphicsContext2D();
		
		updateDraw();
		updateStyle();
		
		drawingArea.getChildren().add(canvas);
		this.setTop(drawingArea);
		this.setBottom(hud);
	}
	
	public void updateDraw() {
		
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.drawImage(hall, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(pet, canvas.getWidth()/2-pet.getW()/2, pet.getY()+canvas.getHeight()/2-pet.getH()/2, pet.getW(),pet.getH());
	}
	
	public void updateStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		drawingArea.setBackground(new Background(new BackgroundFill(Color.YELLOW, null, null)));
		hud.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		hud.setAlignment(Pos.CENTER);
	}
	
	public Action getActionBar() {
		return hud.getActionBar();
	}
}
