package app.view;

import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

/**
 * 
 * @author ben
 * view.Game est de type StakPane
 * Permet les opérations d'affichage concernant le jeu
 */
public class Game extends StackPane {
	
	//########################### ATTRIBUTS #####################################
	
	// le view.pet a manipuler
	// ATTENTION reference partagé avec controller.Pet
	private Pet pet;
	
	// ATTENTION reference partagé avec controller.Room
	private Room room;
	
	private Canvas canvas;
	private GraphicsContext gc;
	private Font font;
	private AnchorPane drawingArea;
	private Hud hud;
	//######################### EVENT-ACTION ####################################

	/**
	 * DrawLoop effectué toute les 0.335 second
	 * déclendeur -> this
	 */ 
	private AnimationTimer drawGameoverLoop = new AnimationTimer() {
		long old_time=0;
		double fade=0;
		int pos=0;
		String text="GAME OVER";
		boolean doonce=false;
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+(1<<25); // aproximativement 33 millisecond
				
				drawGameOver(fade, text.substring(0, pos));
				
				fade+=0.01;
				pos=(int)((fade+0.1)*(double)text.length());
				
				if ( fade > 1.0 ) {
					this.stop();
					old_time=0;
					fade=0;
					pos=1;
				}
				
				if(!doonce) {
					hud.hideStats();
					hud.getActionBar().setActive(false, false, false, false, false, true);
				}
			}
        }
    };
    
	//############################ METHODES #####################################
	
	public Game(Pet pet_instance, Room room_instance) {
		pet = pet_instance;
		room = room_instance;
		hud = new Hud(pet.getHygiene(), pet.getHunger(), pet.getMoral(), pet.getWeight(), pet.getThirst());
		drawingArea = new AnchorPane();
		
		canvas = new Canvas(640, 360);
		gc = canvas.getGraphicsContext2D();
		font = Font.font("Linux Biolinum Keyboard O", FontWeight.NORMAL, FontPosture.REGULAR, 46);
		updateDraw();
		updateStyle();
		
		drawingArea.getChildren().add(canvas);
		AnchorPane.setLeftAnchor(canvas, 0.);
		AnchorPane.setTopAnchor(canvas, 0.);
		this.getChildren().addAll(drawingArea, hud);
		
		for ( String f : Font.getFamilies() ) {
			System.out.println(f);
		}
	}
	
	public void updateText() {
		hud.updateText();
	}
	
	public void updateDraw() {
		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.drawImage(room, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(pet, canvas.getWidth()/2-pet.getW()/2, pet.getY()+canvas.getHeight()/2-pet.getH()/2, pet.getW(),pet.getH());
		
	}
	
	private void drawGameOver(double fade, String text) {
		
		gc.setFill(Color.rgb(255, 192, 128, fade));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.setFont(font);
		gc.setFill(Color.rgb(255, 255, 255, fade));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(text, canvas.getWidth()/2, canvas.getHeight()/2);
	}
	
	public void startDrawingGameOver() {
		drawGameoverLoop.start();
	}
	
	public void updateStyle() {
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		drawingArea.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 0, 0.5), null, null)));
		//hud.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}
	
	public Action getActionBar() {
		return hud.getActionBar();
	}
}
