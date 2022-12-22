package app.view;
 
import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
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
	
	// besoin de garder les etat du gameover si on change de resolution après la fin
	private boolean gameover;
	private String gameover_msg;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionLoop effectué toute les 1 second (1e+9 ns)
	 * déclendeur -> this
	 */ 
	private AnimationTimer drawLoop = new AnimationTimer() {
		long old_time=0;
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+33_000_000;
				
				updateDraw();
			}
        }
    };
    
	/**
	 * DrawLoop effectué toute les 0.335 second
	 * déclendeur -> this
	 */ 
	private AnimationTimer drawGameoverLoop = new AnimationTimer() {
		
		long old_time=0;
		double fade=0;
		int pos=0;
		boolean doonce=false;
		
        public void handle(long new_time) {
			if (new_time > old_time ) {
				old_time = new_time+33_000_000; // 33 millisecond
				
				drawGameOver(fade, gameover_msg.substring(0, pos));
				
				fade+=0.01;
				pos=(int)((fade+0.1)*(double)gameover_msg.length());
				
				if ( fade > 1.0 ) {
					this.stop();
					old_time=0;
					fade=0;
					pos=1;
					gameover=true;
				}
				
				if(!doonce) {
					hud.hideStats();
					hud.getChildAction().setAllowedButtons(false, false, false, false, false, true);
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
		gameover=false;
		gameover_msg="GAME OVER";
		updateStyle();
		updateDraw();
		
		drawingArea.getChildren().add(canvas);
		this.getChildren().addAll(drawingArea, hud);
		
		drawLoop.start();
		/*for ( String f : Font.getFamilies() ) {
			System.out.println(f);
		}*/
	}
	
	public void updateText() {
		hud.updateText();
	}
	
	public void updateDraw() {
		
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.drawImage(room, 0, 0, canvas.getWidth(), canvas.getHeight());
		gc.drawImage(pet, pet.getSrcX()                       , pet.getSrcY(), 
				          pet.getSrcW()                       , pet.getSrcH(),
						  canvas.getWidth()/2-pet.getDestW()/2, pet.getDestY()+canvas.getHeight()/2-pet.getDestH()/2,
				          pet.getDestW()                      , pet.getDestH()                                       );
	}
	
	private void drawGameOver(double fade, String text) {
		//gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.setFill(Color.rgb(255, 192, 128, fade));
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		gc.setFont(font);
		gc.setFill(Color.rgb(255, 255, 255, fade));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(text, canvas.getWidth()/2, canvas.getHeight()/2);
		
		gc.drawImage(pet, pet.getSrcX()                       , pet.getSrcY(), 
		             	  pet.getSrcW()                       , pet.getSrcH(),
		             	  canvas.getWidth()/2-pet.getDestW()/2, pet.getDestY()+canvas.getHeight()/2-pet.getDestH()/2,
		             	  pet.getDestW()                      , pet.getDestH()                                       );
	}
	
	public void startDrawingGameOver() {
		drawLoop.stop();
		drawGameoverLoop.start();
	}
	
	public void changeDefinition(int numChoice) {
		
		switch (numChoice) {
			case 0:
				//canvas.resize(640.,  360.);
				canvas.setWidth(640);
				canvas.setHeight(360);
				pet.setSize(0, 92, 128, 128);
				break;
			case 1:
				//canvas.resize(1280., 720.);
				canvas.setWidth(1280);
				canvas.setHeight(720);
				pet.setSize(0, 184, 256, 256);
				break;
		}
		
		updateDraw();
		
		if ( gameover )
			drawGameOver(1.0, "GAME OVER");
	}
	
	public void updateStyle() {
		font = Font.font("Linux Biolinum Keyboard O", FontWeight.NORMAL, FontPosture.REGULAR, 46);
		AnchorPane.setLeftAnchor(canvas, 0.);
		AnchorPane.setTopAnchor(canvas, 0.);
		this.setBackground(new Background(new BackgroundFill(Color.BLUE, null, null)));
		drawingArea.setBackground(new Background(new BackgroundFill(Color.rgb(255, 255, 0, 0.5), null, null)));
		//hud.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
	}
	
	public void setChildRoom(Room new_room) {
		room = new_room;
		updateDraw();
	}
	
	public Hud getChildHud() {
		return hud;
	}
	
	public void exit() {
		drawLoop.stop();
		drawGameoverLoop.stop();
		pet.exit();
		room.exit();
		hud.exit();
		pet = null;
		room = null;
		canvas = null;
		gc = null;
		font = null;
		drawingArea = null;
		hud = null;
		gameover_msg = null;
	}
}
