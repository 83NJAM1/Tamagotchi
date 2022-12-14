package app.view;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import app.App;
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * view.Game est de type StakPane
 * Permet les opérations d'affichage concernant le jeu
 */
public class Game extends StackPane implements Cleanable, Localisable {
	 
	//########################### ATTRIBUTS #####################################
	
	// la vue du pet
	private Pet pet; //NOTE: reference partagé avec c.Pet
	// la vue de la pièce
	private Room room; //NOTE: reference partagé avec c.Room
	
	// pixel buffer
	private Canvas canvas;
	// traite les opérations d'affichage
	private GraphicsContext gc;
	// police d'écriture
	private Font font;
	// la zone du canvas
	private AnchorPane drawingArea;
	// le head-up display
	private Hud hud;
		
	// la view du minijeu cuisiner
	private Cook cookView;
		
	// états partie
	private boolean gameover;
	private String gameover_msg;
	private double petOpacity;
	private double petMoveOpacity;
	private double gameOpacity;
	
	String current_weather;
	WeatherEffect weatherEffect;
	WeatherEffect oldWeatherEffect;
	boolean weatherIsActivated;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action effectué quand le pet se déplace
	 * déclencher -> c.Game
	 */
	ChangeListener<Number> petXMove = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			petOpacity = 0.3;
			System.out.println("pet has move to x:" + vnew + " from x:" + vold);
		}
	};
	
	/**
	 * Action effectué quand le pet se déplace
	 * déclencher -> c.Game
	 */
	ChangeListener<Number> petYMove = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			petOpacity = 0.3;
			System.out.println("pet has move to y:" + vnew + " from y:" + vold);
		}
	};
	
	/**
	 * effectué tout les 1/3 secondes.
	 * met à jour la vue
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
	 * effectué tout les 1/3 secondes.
	 * met à jour la vue lors d'un gameover
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
					hud.getViewAction().setAllowedMainAction(false, false, false, true);
					hud.getViewAction().enableCooking(false);
				}
			}
        }
    };
    
	//############################ METHODES #####################################
	
    /**
     * constructeur
     * @param pet_instance le pet de jeu
     * @param room_instance la pièce courante
     */
	public Game(Pet pet_instance, Room room_instance) {
		pet = pet_instance;
		room = room_instance;
		hud = new Hud(pet.getViewHygiene(), pet.getViewHunger(), pet.getViewMoral(), pet.getViewWeight(), pet.getViewThirst());
		cookView=new Cook();
		drawingArea = new AnchorPane();
		canvas = new Canvas(640, 360);
		
		gc = canvas.getGraphicsContext2D();
		gameover=false;
		gameover_msg="GAME OVER";

		drawingArea.getChildren().add(canvas);
		this.getChildren().addAll(drawingArea, hud, cookView);
		
		drawLoop.start();
		pet.changeTypeColor(2); //TODO : a modifier 
		
		petOpacity=0.0;
		petMoveOpacity = 1.0;
		gameOpacity = 0.0;
		pet.getXProperty().addListener(petXMove);
		pet.getYProperty().addListener(petYMove);

		weatherIsActivated = false;
		
		updateStyle();
		updateDraw();
	}
	
	/**
	 * met à jour le jeu en redéssinant
	 */
	public void updateDraw() {
		
		//BlendMode m = gc.getGlobalBlendMode();
		
		// netoie
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// fondu transition
		if ( gameOpacity < 1.0 )
			gameOpacity += 0.05;
		
		gc.setGlobalAlpha(gameOpacity);
		
		// sky
		gc.setFill(Color.SKYBLUE);
		gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
		
		// weather-back
		if ( weatherIsActivated ) {
			if( weatherEffect != null )
				weatherEffect.drawEffect(1);
			if ( oldWeatherEffect != null ) {
				if ( oldWeatherEffect.drawEffect(1) ) {
					oldWeatherEffect = null;
				}
			}
		}
		
		//room
		gc.drawImage(room, 0, 0, canvas.getWidth(), canvas.getHeight());
		
		//pet
		drawPet();
		
		// weather-front
		if ( weatherIsActivated ) {
			if( weatherEffect != null ) {
				weatherEffect.drawEffect(2);
				weatherEffect.drawEffect(3);
			}
			if ( oldWeatherEffect != null ) {
				boolean p2 = oldWeatherEffect.drawEffect(2);
				boolean p3 = oldWeatherEffect.drawEffect(3);
				if (  p2 && p3 ) {
					oldWeatherEffect = null;
				}
			}
		}

		// NOTE: la vue cook n'utlise pas le canvas, pas sur que l'appel ici soit util 
		cookView.setDimension(canvas.getWidth(),canvas.getHeight());
		
		// fin fondu transition
		if ( gameOpacity > 1.0 ) {
			gc.setGlobalAlpha(1.0);
			gameOpacity = 1.0;
		}
	}
	
	/**
	 * afficher / masquer la météo
	 * @param state
	 */
	public void setWeatherTo(boolean state) {
		weatherIsActivated = state;
	}
	
	/**
	 * dessine le pet
	 */
	public void drawPet() {
		
		// fondu transition
		if ( pet.getVisible() ) {
			
			petMoveOpacity = 1.0;
			
			if ( gameOpacity < 1.0 ) {
				petOpacity = gameOpacity;
			}
			else if ( petOpacity < 1.0 ) {
				petOpacity += 0.05;
			}
			
			gc.setGlobalAlpha(petOpacity);
			//System.out.println("cas 1");
		}
		else {
			
			petOpacity = 0.0;
			
			if ( petMoveOpacity > 0.0 ) {
				petMoveOpacity -= 0.05;
			}
			
			gc.setGlobalAlpha(petMoveOpacity);
			//System.out.println("cas 2");
		}

		// la couleur du pet
		gc.drawImage(pet.getColorSprite().getSheet(), 
				     pet.getColorSprite().getSrcX()  , pet.getColorSprite().getSrcY(), 
			         pet.getColorSprite().getSrcW()  , pet.getColorSprite().getSrcH(),
			         canvas.getWidth()*room.getXOriginRatio()-pet.getColorSprite().getDestW()*0.5+pet.getDestX() , 
			         canvas.getHeight()*room.getYOriginRatio()-pet.getColorSprite().getDestH()*0.9+pet.getDestY(),
			         pet.getColorSprite().getDestW()*pet.getDisctanceFactor() , pet.getColorSprite().getDestH()*pet.getDisctanceFactor() );
		
		// la ligne de dessin
		gc.drawImage(pet.getSheet(), 
				     pet.getSrcX(), pet.getSrcY(), 
			         pet.getSrcW(), pet.getSrcH(),
			         canvas.getWidth()*room.getXOriginRatio()-pet.getDestW()*0.5+pet.getDestX() , 
			         canvas.getHeight()*room.getYOriginRatio()-pet.getDestH()*0.9+pet.getDestY(),
			         pet.getDestW() * pet.getDisctanceFactor(), pet.getDestH() * pet.getDisctanceFactor() );
		
		// l'objet
		if ( pet.getObject() != null ) {
			gc.drawImage(pet.getObject().getSheet(), 
					     pet.getObject().getSrcX(), pet.getObject().getSrcY(), 
				         pet.getObject().getSrcW(), pet.getObject().getSrcH(),
				         canvas.getWidth()*room.getXOriginRatio()-pet.getObject().getDestW()*0.5+pet.getDestX() , 
				         canvas.getHeight()*room.getYOriginRatio()-pet.getObject().getDestH()*0.9+pet.getDestY(),
				         pet.getObject().getDestW() * pet.getDisctanceFactor(), pet.getObject().getDestH() * pet.getDisctanceFactor() );
		}
		
		// un carré délimitant le pet NOTE debug		
		if ( App.DEBUG ) {
			gc.setStroke(Color.RED);
			gc.strokeRect(canvas.getWidth()*room.getXOriginRatio()-pet.getDestW()*0.5+pet.getDestX() , 
						  canvas.getHeight()*room.getYOriginRatio()-pet.getDestH()*0.9+pet.getDestY(),
				          pet.getDestW() * pet.getDisctanceFactor(), pet.getDestH() * pet.getDisctanceFactor() );
		}

	}
	
	/**
	 * dessine le gameover
	 */
	private void drawGameOver(double fade, String text) {
	
		updateDraw();
		
		gc.setFill(Color.rgb(0, 0, 0, fade*0.8));
		gc.fillRect(0, 0, canvas.getWidth(),canvas.getHeight());
		gc.setFont(font);
		gc.setFill(Color.rgb( (int)(Color.ORANGERED.getRed()*255), (int)(Color.ORANGERED.getGreen()*255), (int)(Color.ORANGERED.getBlue()*255), fade));
		gc.setTextAlign(TextAlignment.CENTER);
		gc.fillText(text, canvas.getWidth()/2, canvas.getHeight()/2);
	}
	
	/**
	 * initie l'affichage du gameover
	 */
	public void startDrawingGameOver() {
		drawLoop.stop();
		drawGameoverLoop.start();
	}
	
	/**
	 * redimensionne la vue du jeu
	 * @param numChoice l'indice du choix de définition
	 */
	public void changeDefinition(int numChoice) {
		
		switch (numChoice) {
			case 0:
				canvas.setWidth(640);
				canvas.setHeight(360);
				pet.setSize((int)pet.getDestX(), (int)pet.getDestY(), 128, 128);
				break;
			case 1:
				canvas.setWidth(1280);
				canvas.setHeight(720);
				pet.setSize((int)pet.getDestX(), (int)pet.getDestY(), 256, 256);
				break;
		}
		
		if( weatherEffect != null )
			weatherEffect.resize(canvas.getWidth(), canvas.getHeight());
		
		updateDraw();
		
		if ( gameover )
			drawGameOver(1.0, "GAME OVER");
	}
	
	/**
	 * créer un nouvelle effet meteo
	 * @param weather
	 */
	public void setWeather(String weather) {

		if ( !weather.equals(current_weather) ) {
			
			Sprite fx;
			Sprite fx2;
			
			if( weatherEffect != null ) {
				
				oldWeatherEffect = weatherEffect;
				oldWeatherEffect.stopEffect();
				oldWeatherEffect.clear();
			}

			switch( weather ) {
				
				case "rainy":
					fx = new Sprite(Main.GAMEIMAGEPATH+"effects/gouttes.png", 0, 0, 64, 64);
					weatherEffect = new FallingEffect(this, canvas.getWidth(), canvas.getHeight(), 24, fx, null, 
													  FallingEffect.MapType.QUINCUNX, FallingEffect.FxType.ALTERNATE, App.DEBUG, gc);
					System.out.println("rainy effect");
					break;
				case "cloudy":
					weatherEffect = new SlidingEffect(gc, canvas.getWidth(), SlidingEffect.SlideType.RIGHT_LEFT, 
													  new int[]{((int)canvas.getWidth()/640)+1, ((int)canvas.getWidth()/640)+1},
							new Sprite(Main.GAMEIMAGEPATH+"effects/nuage-2.png", 0, 0, 640, 80),
							new Sprite(Main.GAMEIMAGEPATH+"effects/nuage-3.png", 0, 0, 640, 80));
					
					System.out.println("cloudy effect");
					break;
				case "suny":
					weatherEffect = new BrigthEffect(canvas.getWidth(), canvas.getHeight(), gc, Color.YELLOW, 0.2);
					break;
				case "stormy":
					weatherEffect = new StormEffect(canvas.getWidth(), canvas.getHeight(), gc);

					System.out.println("stormy effect");
					break;
				case "scorchy":
					weatherEffect =  new BrigthEffect(canvas.getWidth(), canvas.getHeight(), gc, Color.ORANGE, 0.25);
					break;
				case "icy":
					fx = new Sprite(Main.GAMEIMAGEPATH+"effects/flocons.png", 0, 0, 64, 64);
					fx2 = new Sprite(Main.GAMEIMAGEPATH+"effects/flocons_alt.png", 0, 0, 64, 64);
					weatherEffect = new FallingEffect(this, canvas.getWidth(), canvas.getHeight(), 4, fx, fx2,
													  FallingEffect.MapType.QUINCUNX, FallingEffect.FxType.ALTERNATE, App.DEBUG, gc);
					
					System.out.println("icy effect");
					break;
				default:
					weatherEffect = null;
					break;
			}
			
			current_weather = weather;
		}
	}
	
	/**
	 * définit une nouvelle instance de pièce
	 * @param new_room la nouvelle pièce
	 */
	public void setViewRoom(Room new_room) {
		gameOpacity = 0.0;
		room = new_room;
		updateDraw();
	}
	
	/**
	 * obtient la vue enfant Hud
	 * @return l'instance de Hud
	 */
	public Hud getViewHud() {
		return hud;
	}
	
	/**
	 * met a jour le style
	 */
	public void updateStyle() {
		font = Font.font("Linux Biolinum Keyboard O", FontWeight.NORMAL, FontPosture.REGULAR, 46);
		AnchorPane.setLeftAnchor(canvas, 0.);
		AnchorPane.setTopAnchor(canvas, 0.);
		setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
	}
	
	/**
	 * obtient la vue cook
	 * @return
	 */
	public Cook getViewCook() {
		return cookView;
	}
	
	@Override
	public void updateText() {
		hud.updateText();
		cookView.updateText();
	}
	
	@Override
	public void clean() {
		
		if ( drawLoop != null) {
			drawLoop.stop();
			drawLoop = null;
		}
		if ( drawGameoverLoop != null) {
			drawGameoverLoop.stop();
			drawGameoverLoop = null;
		}
		if ( hud != null ) {
			hud.clean();
			hud = null;
		}
		if ( room != null ) {
			room.clean();
			room = null;
		}
		if ( pet != null ) {
			pet.clean();
			pet = null;
		}

		canvas = null;
		gc = null;
		font = null;
		drawingArea = null;
		gameover_msg = null;
	}
}
