package app.view;

import java.util.Random;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class FallingEffect implements WeatherEffect {
	
	GraphicsContext gc;
	StackPane parent;
	int lastIndexChild;
	
	double width;
	double height;
	
	//Rectangle effect;
	Sprite effect;
	Sprite effectAlt;
	boolean isSecondary;
	
	Random seed;

	private double fallingOffset;
	private int[] rowOrder;
	int fallingMap[][];
	int mapMaxValue;
	
	boolean debugmode;
	boolean stopValue;
	
	double opacity;
	
	double velocity;
	boolean infiniteFall;
	boolean makeStop;
	
	public static enum MapType{RANDOM, QUINCUNX};
	public static enum FxType{ALTERNATE, COMBINED, TAILED};
	
	MapType maptype;
	FxType fxtype;
	
	public FallingEffect(StackPane parent, double width, double height, double velocity, 
						 Sprite fx1, Sprite fx2, MapType maptype, FxType fxtype, boolean debugmode, GraphicsContext gc) {
		this.parent = parent;
		this.width = width; 
		this.height = height;
		this.velocity=velocity;
		this.debugmode=debugmode;
		this.gc = gc;
		this.maptype = maptype;
		this.fxtype = fxtype;
		
		seed = new Random();
		effect = fx1;
		effectAlt = fx2;
		mapMaxValue = ( fxtype == FxType.ALTERNATE ) ? 2 : 1;
		fallingOffset = 0;
		
		infiniteFall=true;
		makeStop=false;
		stopValue=false;
		opacity=0.0;
		
		initFallingMap();
		if ( debugmode )// NOTE DEBUG
			initDebug();
	}
	
	public void resize(double width, double height) {
		
		this.width = width; 
		this.height = height;
		
		initFallingMap();
		
		if ( debugmode ) {// NOTE DEBUG
			((VBox)parent.getChildren().get(lastIndexChild)).setMaxWidth(width/5);
			((VBox)parent.getChildren().get(lastIndexChild)).setMaxHeight(height/5);
		}
	}
	
	public void initFallingMap() {
		
		fallingMap = new int[(int)(height/effect.getWidth())+3][(int)(width/effect.getWidth())];
		rowOrder = new int[fallingMap.length];
		
		generateFallingMap();
		
		if ( debugmode ) { // NOTE DEBUG
			printFallingMap();
			System.out.println("Falling map initialisation done");
		}
		
	}
	
	public void generateFallingMap() {
		
		if ( maptype == MapType.RANDOM ) {
			for ( int j=0; j<fallingMap.length; j++ ) {
				for ( int i=0; i<fallingMap[j].length; i++ ) {
					fallingMap[j][i] = seed.nextInt(0, mapMaxValue+1);
				}
				rowOrder[j] = j;
			}
			rowOrder[fallingMap.length-1] = fallingMap.length-1;
		}
		else if ( maptype == MapType.QUINCUNX ) {
			for ( int j=0; j<fallingMap.length; j++ ) {
				for ( int i=0; i<fallingMap[j].length; i++ ) {
					fallingMap[j][i] = ( (i+j)%2 == 0 ) ? seed.nextInt(1, mapMaxValue+1) : 0;
				}
				rowOrder[j] = j;
			}
			rowOrder[fallingMap.length-1] = fallingMap.length-1;
		}
	}
	
	public void shiftRightRowOrder() {
		
		int lastValue = rowOrder[rowOrder.length-1];
		
		for ( int i=rowOrder.length-1; i>0; i-- ) {
			
			rowOrder[i] = rowOrder[i-1];
			
		}
		rowOrder[0] = lastValue;	
	}
	
	public void printFallingMap() {
		System.out.println("Falling map : ");
		for ( int j=0; j<fallingMap.length; j++ ) {
			for ( int i=0; i<fallingMap[j].length; i++ ) {
				if(fallingMap[j][i] == 1) {
					System.out.print( "[1]" );
				}
				else if (fallingMap[j][i] == 2) {
					System.out.print( "[2]" );
				}
				else {
					System.out.print( "[0]" );
				}
			}
			System.out.print(System.lineSeparator());
		}
	}
	
	public boolean drawEffect() {
		
		gc.setGlobalAlpha(opacity);
		
		if ( infiniteFall ) {
			if ( fallingOffset >= effect.getHeight() ) {
				fallingOffset=velocity+1;
				shiftRightRowOrder();
			}
			else
				fallingOffset+=velocity;	
		}
		else if ( fallingOffset < 2*effect.getHeight()+height ) {
			fallingOffset+=velocity;
		}
		
		if(makeStop) {
			gc.setGlobalAlpha( opacity );
			stopValue = opacity<=0;
			opacity-=((double)3/(500));
		}
		else {
			if( opacity<0.5 )
				opacity+=((double)3/(2*1000));
		}
		
		if ( !stopValue ) {
			for ( int j=0; j<fallingMap.length; j++ ) {
				
				gc.setGlobalAlpha((1-((j-2)*effect.getHeight()+fallingOffset)/height)*opacity);
								
				for ( int i=0; i<fallingMap[j].length; i++ ) {
					
					if ( fallingMap[ rowOrder[j] ][i] == 1 ) {
						
						if ( fxtype == FxType.TAILED && effectAlt != null && j<fallingMap.length-1 && fallingMap[ rowOrder[j+1] ][i] == 0 ) {
							gc.drawImage(effectAlt, i*effectAlt.getWidth(), (j-2)*effectAlt.getHeight()+fallingOffset, effectAlt.getWidth(), effectAlt.getHeight());
						}
						else {
							gc.drawImage(effect, i*effect.getWidth(), (j-2)*effect.getHeight()+fallingOffset, effect.getWidth(), effect.getHeight());
						}
					}
					else if ( fallingMap[ rowOrder[j] ][i] == 2 && effectAlt != null ) {
						gc.drawImage(effectAlt, i*effectAlt.getWidth(), (j-2)*effectAlt.getHeight()+fallingOffset, effectAlt.getWidth(), effectAlt.getHeight());
					}
					else if ( fxtype == FxType.COMBINED && effectAlt != null ) {
						gc.drawImage(effectAlt, i*effectAlt.getWidth(), (j-2)*effectAlt.getHeight()+fallingOffset, effectAlt.getWidth(), effectAlt.getHeight());
					}
				}
			}
		}
		
		gc.setGlobalAlpha(1.0);
		return stopValue;
	}
	
	public void stopEffect() {
		makeStop = true;
	}
	
	/**
	 * NOTE debug
	 */
	public void initDebug() {
		
		/**
		 * change l'indice de selection vers la gauche
		 * déclencheur -> this
		 */
		EventHandler<ActionEvent> toogleInfiniteFall = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				infiniteFall = !infiniteFall;
			}
		};
		
		/**
		 * change l'indice de selection vers la gauche
		 * déclencheur -> this
		 */
		EventHandler<ActionEvent> newFallingMap = new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				generateFallingMap();
			}
		};
		
		/**
		 * Action effectué quand t-on modifie le volume via la vue Option
		 * déclencher -> v.Option
		 */
		ChangeListener<Number> change_velocityWeatherEffect = new ChangeListener<Number>() {
			public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {

				velocity = vnew.doubleValue();
				System.out.println("velocity to :" + velocity + " from :" + vold.intValue());
			}
		};
		
		VBox debug = new VBox();
		VBox content = new VBox();
		Label debugLabel = new Label("Debug");
		Button fallWeatherEffectDebug = new Button("toogle-infinite-fall");
		Slider velocityWeatherEffectDebug = new Slider();
		Button mapFallingWeatherEffectDebug = new Button("new-falling-map");
		Label velocityLabel = new Label("Velocity");

		fallWeatherEffectDebug.setOnAction(toogleInfiniteFall);
		mapFallingWeatherEffectDebug.setOnAction(newFallingMap);
		velocityWeatherEffectDebug.valueProperty().addListener(change_velocityWeatherEffect);

		content.getChildren().addAll(velocityLabel, velocityWeatherEffectDebug, fallWeatherEffectDebug, mapFallingWeatherEffectDebug);
		debug.getChildren().addAll(debugLabel, content);
		
		debugLabel.setStyle("-fx-background-color: black; -fx-text-fill: red;");
		velocityLabel.setStyle("-fx-background-color: black; -fx-text-fill: red;");
		debug.setStyle("-fx-font-weight: bold; -fx-color: red; -fx-background-color: black;");
		content.setStyle("-fx-background-color: black;");
		debug.setAlignment(Pos.CENTER);
		content.setAlignment(Pos.BOTTOM_LEFT);
		debugLabel.setPadding(new Insets(15, 15, 15, 15));
		debug.setMaxWidth(width/5);
		debug.setMaxHeight(height/5); 
		StackPane.setAlignment(debug, Pos.TOP_RIGHT);
		
		parent.getChildren().add(debug);
		lastIndexChild = parent.getChildren().lastIndexOf(debug);
		
	}
	
	public void clear() {
		
		if ( debugmode ) {// NOTE DEBUG
			
			parent.getChildren().remove(lastIndexChild);
			debugmode = false;
		}
	}
}
