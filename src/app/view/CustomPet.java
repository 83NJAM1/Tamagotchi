package app.view;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.scene.paint.Color;
import javafx.scene.canvas.Canvas;

import javax.imageio.ImageIO;

import app.App;
import app.Cleanable;
import app.Localisable;

/**
 * 
 * @author ben
 * view.CustomPet est de type AnchorPane
 * car le choix de design implique qu'il soit composé de sous vue
 */
public class CustomPet extends StackPane implements Cleanable, Localisable, Stylable {

	//########################### ATTRIBUTS #####################################

	// popup
	private ColorPicker colorpicker;
	private Popup popup;
	
	// partie centrale
	private Canvas preview;
	private Image maskPetColor;
	private Image maskPetColorFat;
	private Image maskPetColorThin;
	private Pet previewPet;
	private WritableImage petColor;
	private WritableImage petColorFat;
	private WritableImage petColorThin;
	
	// partie droite
	private Label labelPetType;
	private VBox petType;
	private Button butPetTypeA;
	private Button butPetTypeB;
	private Button butPetTypeC;
	private Button butPetTypeD;
	
	// partie gauche-haut
	private Label labelBackPalet;
	private HBox backPalet;
	private Button butColorA;
	private Color  colorA;
	private Button butColorB;
	private Color  colorB;
	private Button butColorC;
	private Color  colorC;
	
	// l'ensemble
	private VBox TopCenterBottom;
	private HBox LeftCenterRight;
	private VBox leftSide;
	private VBox rightSide;
	private Button validate;
	
	// le bouton à colorier
	private Button currentButton;
	// le type de pet choisie
	private String currentPetType;
	
	// utilisé pour savoir si le thread est terminer
	private BooleanProperty terminated = new SimpleBooleanProperty(true);
	File repA; // repertoire pour chat
	File repB; // repertoire pour chient
	File repC; // repertoire pour robot
	File repD; // repertoire pour lapin
	
	/**
	 * thread pour generer l'image des couleurs
	 */
	private Thread thread = new Thread(){
		@Override
		public void run() {
			
			for( int y=0; y < (int)maskPetColor.getHeight(); y++ ) {
				for ( int x=0; x < (int)maskPetColor.getWidth(); x++ ) {
					
					Color maskColor = maskPetColor.getPixelReader().getColor(x, y);
					
					if ( maskColor.getOpacity() > 0 ) {
						
						// A
						if( maskColor.getBlue() == 1.0 && maskColor.getRed() == 1.0 && maskColor.getGreen() == 1.0) {
							petColor.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// B
						else if( maskColor.getBlue() == 0 && maskColor.getRed() == 0 && maskColor.getGreen() == 0) {
							petColor.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// C
						else if( maskColor.getBlue() >= 0.9 && maskColor.getRed() <= 0.1 && maskColor.getGreen() <= 0.1 ) { 
							petColor.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// D
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() >= 0.9 && maskColor.getGreen() <= 0.1 ) {
							petColor.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// E
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() <= 0.1 && maskColor.getGreen() >= 0.9 ) {
							petColor.getPixelWriter().setColor(x, y, new Color(colorC.getRed(), colorC.getGreen(), colorC.getBlue(), maskColor.getOpacity()));
						}
						
					}
				}
			}

			for( int y=0; y < (int)maskPetColorFat.getHeight(); y++ ) {
				for ( int x=0; x < (int)maskPetColorFat.getWidth(); x++ ) {
					
					Color maskColor = maskPetColorFat.getPixelReader().getColor(x, y);
					
					if ( maskColor.getOpacity() > 0 ) {
						
						// A
						if( maskColor.getBlue() == 1.0 && maskColor.getRed() == 1.0 && maskColor.getGreen() == 1.0) {
							petColorFat.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// B
						else if( maskColor.getBlue() == 0 && maskColor.getRed() == 0 && maskColor.getGreen() == 0) {
							petColorFat.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// C
						else if( maskColor.getBlue() >= 0.9 && maskColor.getRed() <= 0.1 && maskColor.getGreen() <= 0.1 ) { 
							petColorFat.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// D
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() >= 0.9 && maskColor.getGreen() <= 0.1 ) {
							petColorFat.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// E
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() <= 0.1 && maskColor.getGreen() >= 0.9 ) {
							petColorFat.getPixelWriter().setColor(x, y, new Color(colorC.getRed(), colorC.getGreen(), colorC.getBlue(), maskColor.getOpacity()));
						}
						
					}
				}
			}
			
			for( int y=0; y < (int)maskPetColorThin.getHeight(); y++ ) {
				for ( int x=0; x < (int)maskPetColorThin.getWidth(); x++ ) {
					
					Color maskColor = maskPetColorThin.getPixelReader().getColor(x, y);
					
					if ( maskColor.getOpacity() > 0 ) {
						
						// A
						if( maskColor.getBlue() == 1.0 && maskColor.getRed() == 1.0 && maskColor.getGreen() == 1.0) {
							petColorThin.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// B
						else if( maskColor.getBlue() == 0 && maskColor.getRed() == 0 && maskColor.getGreen() == 0) {
							petColorThin.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// C
						else if( maskColor.getBlue() >= 0.9 && maskColor.getRed() <= 0.1 && maskColor.getGreen() <= 0.1 ) { 
							petColorThin.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
						}
						// D
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() >= 0.9 && maskColor.getGreen() <= 0.1 ) {
							petColorThin.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
						}
						// E
						else if( maskColor.getBlue() <= 0.1 && maskColor.getRed() <= 0.1 && maskColor.getGreen() >= 0.9 ) {
							petColorThin.getPixelWriter().setColor(x, y, new Color(colorC.getRed(), colorC.getGreen(), colorC.getBlue(), maskColor.getOpacity()));
						}
						
					}
				}
			}
			
			try {				
				File dump = new File(Main.USERPATH+currentPetType+"/colorPet.png");
				ImageIO.write(SwingFXUtils.fromFXImage(petColor, null), "png", dump); //TODO
				
				File dumpfat = new File(Main.USERPATH+currentPetType+"/colorPetFat.png");
				ImageIO.write(SwingFXUtils.fromFXImage(petColorFat, null), "png", dumpfat); //TODO 
				
				File dumpthin = new File(Main.USERPATH+currentPetType+"/colorPetThin.png");
				ImageIO.write(SwingFXUtils.fromFXImage(petColorThin, null), "png", dumpthin); //TODO 
				//System.err.println(dump.getAbsolutePath());
			} catch(Exception e) {
				System.err.println(e);
			}
			
			terminated.setValue(true);
		}
	};
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action effectué quand le thread est finie
	 * déclencher -> c.Game
	 */
	ChangeListener<Boolean> terminatedAction = new ChangeListener<Boolean>() {
		public void changed(ObservableValue<? extends Boolean> obs, Boolean vold, Boolean vnew) {
			if ( vnew ) {
				generatePet();
				updateDraw();
			}
		}
	};
	
	/**
	 * effectué tout les 1/3 secondes.
	 * met à jour le canvas
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
	 * valider couleur choisie
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> colorOk = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			if ( currentButton.equals(butColorA) ) {
				colorA = colorpicker.getValue();
			}
			else if ( currentButton.equals(butColorB) ) {
				colorB = colorpicker.getValue();
			}
			else if ( currentButton.equals(butColorC) ) {
				colorC = colorpicker.getValue();
			}

			currentButton.setBackground(new Background(new BackgroundFill( colorpicker.getValue(), null, null) ) );
			popup.hide();
			updatePreview();
		}
	};
	
	/**
	 * fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> actionColorA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butColorA;
			popup.show(butColorA, App.getX()+butColorA.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butColorA.getLocalToSceneTransform().getTy());
		}
	};
	
	/**
	 * fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> actionColorB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butColorB;
			popup.show(butColorB, App.getX()+butColorB.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butColorB.getLocalToSceneTransform().getTy());
		}
	};
	
	/**
	 * fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> actionColorC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butColorC;
			popup.show(butColorC, App.getX()+butColorC.getLocalToSceneTransform().getTx(), 
									 App.getY()+butColorC.getLocalToSceneTransform().getTy());
		}
	};
	
	/**
	 * change le pet par un chat et met a jour la preview
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//preview.setImage(new Image("res/test_cat.png"));
			currentPetType="cat";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"cat/Coloriage_Chat_Normal.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			
			maskPetColorFat = new Image(Main.GAMEIMAGEPATH+"cat/Coloriage_Chat_Gros.png");
			petColorFat = new WritableImage((int)maskPetColorFat.getWidth(), (int)maskPetColorFat.getHeight());
			
			maskPetColorThin = new Image(Main.GAMEIMAGEPATH+"cat/Coloriage_Chat_Maigre.png");
			petColorThin = new WritableImage((int)maskPetColorThin.getWidth(), (int)maskPetColorThin.getHeight());
			updatePreview();
		}
	};
	
	/**
	 * change le pet par un chien et met a jour la preview
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//preview.setImage(new Image("res/test_dog.png"));
			currentPetType="dog";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Normal.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			
			maskPetColorFat = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Gros.png");
			petColorFat = new WritableImage((int)maskPetColorFat.getWidth(), (int)maskPetColorFat.getHeight());
			
			maskPetColorThin = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Maigre.png");
			petColorThin = new WritableImage((int)maskPetColorThin.getWidth(), (int)maskPetColorThin.getHeight());
			updatePreview();
		}
	};
	
	/**
	 * change le pet par un robot et met a jour la preview
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			currentPetType="robot";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"robot/Coloriage_Robot.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			updatePreview();
		}
	};
	
	/**
	 * change le pet par un lapin et met a jour la preview
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeD = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//preview.setImage(new Image("res/test_dog.png"));
			currentPetType="rabbit";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"rabbit/Coloriage_Lapin_Normal.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			
			maskPetColorFat = new Image(Main.GAMEIMAGEPATH+"rabbit/Coloriage_Lapin_Gros.png");
			petColorFat = new WritableImage((int)maskPetColorFat.getWidth(), (int)maskPetColorFat.getHeight());
			
			maskPetColorThin = new Image(Main.GAMEIMAGEPATH+"rabbit/Coloriage_Lapin_Maigre.png");
			petColorThin = new WritableImage((int)maskPetColorThin.getWidth(), (int)maskPetColorThin.getHeight());
			updatePreview();
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public CustomPet() {
		
		File repUser = new File(Main.USERPATH);
		
		if ( !repUser.exists() ) {
			repUser.mkdir();
		}
		
		repD = new File(Main.USERPATH+"lapin");
		if ( !repD.exists() ) {
			repD.mkdir();
		}
		repC = new File(Main.USERPATH+"robot");
		if ( !repC.exists() ) {
			repC.mkdir();
		}
		repB = new File(Main.USERPATH+"dog");
		if ( !repB.exists() ) {
			repB.mkdir();
		}
		repA = new File(Main.USERPATH+"cat");
		if ( !repA.exists() ) {
			repA.mkdir();
		}
		
		preview = new Canvas(256, 256);
		colorpicker = new ColorPicker();
		
		popup = new Popup();
		
		labelPetType = new Label("#Choose your pet#");
		
		petType = new VBox(15);
		butPetTypeA = new Button("button-cat");
		butPetTypeB = new Button("button-dog");
		butPetTypeC = new Button("button-robot");
		butPetTypeD = new Button("button-rabbit");
		
		labelBackPalet = new Label("#Choose your first colors#");
		
		backPalet = new HBox(2);
		butColorA = new Button();
		butColorB = new Button();
		butColorC = new Button();
		
		TopCenterBottom = new VBox(15);
		LeftCenterRight = new HBox(15);
		leftSide = new VBox(15);
		rightSide = new VBox(15);
		validate = new Button("validate");
		
		colorpicker.setOnAction(colorOk);
		
		butColorA.setOnAction(actionColorA);
		butColorB.setOnAction(actionColorB);
		butColorC.setOnAction(actionColorC);
				
		butPetTypeA.setOnAction(petTypeA);
		butPetTypeB.setOnAction(petTypeB);
		butPetTypeC.setOnAction(petTypeC);
		butPetTypeD.setOnAction(petTypeD);
		
		popup.getContent().add(colorpicker);
		petType.getChildren().addAll(butPetTypeA, butPetTypeB, butPetTypeC); //butPetTypeD
		backPalet.getChildren().addAll(butColorA, butColorB, butColorC);
		
		leftSide.getChildren().addAll(labelBackPalet, backPalet);
		rightSide.getChildren().addAll(labelPetType, petType);
		
		LeftCenterRight.getChildren().addAll(leftSide, preview, rightSide);
		TopCenterBottom.getChildren().addAll(LeftCenterRight, validate);
		
		this.getChildren().addAll(TopCenterBottom);
		colorpicker.hide();
		
		colorA = Color.BEIGE;
		colorB = Color.DARKORANGE;
		colorC = Color.CHOCOLATE;

		currentPetType="dog";
		maskPetColor = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Normal.png");
		maskPetColorFat = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Gros.png");
		maskPetColorThin = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Maigre.png");
		petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
		petColorFat = new WritableImage((int)maskPetColorFat.getWidth(), (int)maskPetColorFat.getHeight());
		petColorThin = new WritableImage((int)maskPetColorThin.getWidth(), (int)maskPetColorThin.getHeight());

		drawLoop.start();
		terminated.addListener(terminatedAction);
		
		generatePet();
		updateStyle();
		updateText();
	}
	
	/**
	 * définit l'action pour le bouton valider customisation
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionValidate(EventHandler<ActionEvent> e) {
		validate.setOnAction(e);
	}
	
	/**
	 * obtient le type du pet voulue par l'utilisateur
	 * @return l'identifiant du pet
	 */
	public String getPetType() {
		return currentPetType;
	}
	
	/**
	 * écrit l'image des nouvelles couleurs
	 */
	public void updateColorImage() {
		
		if ( terminated.getValue() ) {
			terminated.setValue(false);
			Platform.runLater(thread);
		}
	}
	
	/**
	 * genere le pet en chargant les images lui correspondant
	 */
	public void generatePet() {
		
		if ( previewPet != null ) {
			previewPet.clean();
			System.gc();
		}

		switch(currentPetType) {

			case "cat":
				previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Chat_Normal.png", 
									 "file:"+repA.getPath()+"/colorPet.png");
				
				previewPet.addAnime("heureux", new int[]{0, 1, 2}, new int[]{0, 1, 2});
				break;
			case "dog":
				previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Chien_Normal.png", 
									 "file:"+repB.getPath()+"/colorPet.png");
				
				previewPet.addAnime("heureux", new int[]{0, 1, 2, 3}, new int[]{0, 1, 2, 3});
				break;
			case "robot":
				previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Robot.png", 
									 "file:"+repC.getPath()+"/colorPet.png");
				
				previewPet.addAnime("heureux", new int[]{0, 1}, new int[]{0, 1});	
				break;
		}
		
		previewPet.setAnime("heureux");
		previewPet.play();
		previewPet.changeTypeColor(2);
	}
	
	/**
	 * met a jour la preview
	 */
	public void updatePreview() {

			updateColorImage();
			generatePet();
			updateDraw();
	}
	
	/**
	 * redessine tout
	 */
	public void updateDraw() {
		preview.getGraphicsContext2D().clearRect(0, 0, 256, 256);
		
		preview.getGraphicsContext2D().drawImage(
				
				previewPet.getColorSprite().getSheet(),
				previewPet.getColorSprite().getSrcX(), previewPet.getColorSprite().getSrcY(),
				previewPet.getColorSprite().getSrcW(), previewPet.getColorSprite().getSrcH(),
				0, 0,
				256, 256
		);
		
		preview.getGraphicsContext2D().drawImage(
				
				previewPet.getSheet(), 
				previewPet.getSrcX(), previewPet.getSrcY(), 
			    previewPet.getSrcW(), previewPet.getSrcH(),
			    0, 0,
			    256, 256
		);
		
		if( !terminated.getValue()  ) {
			preview.getGraphicsContext2D().strokeText("LOADING...", 90, 256);
		}
	}
	
	@Override
	public void updateStyle() {

		petType.setAlignment(Pos.CENTER);
		leftSide.setAlignment(Pos.CENTER);
		rightSide.setAlignment(Pos.CENTER);
		TopCenterBottom.setAlignment(Pos.CENTER);
		LeftCenterRight.setAlignment(Pos.CENTER);
		
		labelPetType.setAlignment(Pos.BASELINE_LEFT);
		labelBackPalet.setAlignment(Pos.BASELINE_LEFT);
		
		labelPetType.setTextAlignment(TextAlignment.LEFT);
		labelBackPalet.setTextAlignment(TextAlignment.LEFT);
		
		petType.setSpacing(15);
	}
	
	//TODO
	@Override
	public void updateText() {
		labelPetType.setText(App.getString("text-choice-pet"));
		butPetTypeA.setText(App.getString("button-cat"));
		butPetTypeB.setText(App.getString("button-dog"));
		butPetTypeC.setText(App.getString("button-robot"));
		butPetTypeD.setText(App.getString("button-rabbit"));
		validate.setText(App.getString("button-validate"));
		labelBackPalet.setText(App.getString("text-choice-color"));
	}
	
	@Override
	public void clean() {
		drawLoop.stop();
		preview = null;
		colorpicker = null;
		popup = null;
		
		labelPetType = null;
		
		petType = null;
		butPetTypeA = null;
		butPetTypeB = null;
		butPetTypeC = null;
		
		labelBackPalet = null;
		
		backPalet = null;
		butColorA = null;
		butColorB = null;
		butColorC = null;
		
		TopCenterBottom = null;
		LeftCenterRight = null;
		leftSide = null;
		rightSide = null;
		validate = null;
		
		currentButton = null;
		currentPetType = null;
	}
}
