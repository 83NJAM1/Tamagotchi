package app.view;

import java.io.File;

import javafx.animation.AnimationTimer;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.effect.ImageInput;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.canvas.Canvas;

import javax.imageio.ImageIO;

import app.App;
import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * view.CustomPet est de type AnchorPane
 * car le choix de design implique qu'il soit composé de sous vue
 */
public class CustomPet extends StackPane implements Componable, Localisable {

	//########################### ATTRIBUTS #####################################
	
	// popup
	private ColorPicker colorpicker;
	private Popup popup;
	
	// partie centrale
	private Canvas preview;
	private Image maskPetColor;
	private Pet previewPet;
	private WritableImage petColor;
	
	// partie droite
	private Label labelPetType;
	private VBox petType;
	private Button butPetTypeA;
	private Button butPetTypeB;
	private Button butPetTypeC;
	
	// partie gauche-haut
	private Label labelBackPalet;
	private HBox backPalet;
	private Button butBckColorA;
	private Color  colorA;
	private Button butBckColorB;
	private Color  colorB;
	private Button butBckColorC;
	private Color  colorC;
	
	// partie gauche-bas
	private Label labelFrontPalet;
	private HBox frontPalet;
	private Button butFrtColorA;
	private Color  colorD;
	private Button butFrtColorB;
	private Color  colorE;
	private Button butFrtColorC;
	private Color  colorF;
	
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
	
	//######################### EVENT-ACTION ####################################
	
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
			
			if ( currentButton.equals(butBckColorA) ) {
				colorA = colorpicker.getValue();
			}
			else if ( currentButton.equals(butBckColorB) ) {
				colorB = colorpicker.getValue();
			}
			else if ( currentButton.equals(butBckColorC) ) {
				colorC = colorpicker.getValue();
			}
			else if ( currentButton.equals(butFrtColorA) ) {
				colorD = colorpicker.getValue();
			}
			else if ( currentButton.equals(butFrtColorB) ) {
				colorE = colorpicker.getValue();
			}
			else if ( currentButton.equals(butFrtColorC) ) {
				colorF = colorpicker.getValue();
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
	private EventHandler<ActionEvent> bckColorA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorA;
			popup.show(butBckColorA, App.getX()+butBckColorA.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butBckColorA.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> bckColorB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorB;
			popup.show(butBckColorB, App.getX()+butBckColorB.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butBckColorB.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> bckColorC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorC;
			popup.show(butBckColorC, App.getX()+butBckColorC.getLocalToSceneTransform().getTx(), 
									 App.getY()+butBckColorC.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorA;
			popup.show(butFrtColorA, App.getX()+butFrtColorA.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butFrtColorA.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorB;
			popup.show(butFrtColorB, App.getX()+butFrtColorB.getLocalToSceneTransform().getTx(), 
					 				 App.getY()+butFrtColorB.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorC;
			popup.show(butFrtColorC, App.getX()+butFrtColorC.getLocalToSceneTransform().getTx(), 
									 App.getY()+butFrtColorC.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//preview.setImage(new Image("res/test_cat.png"));
			currentPetType="cat";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"cat/Coloriage_Chat_Normal.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			updatePreview();
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			//preview.setImage(new Image("res/test_dog.png"));
			currentPetType="dog";
			
			//image generation
			maskPetColor = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Normal.png");
			petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
			updatePreview();
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorC
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
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public CustomPet() {
		
		//preview = new ImageView(new Image("res/test_dog.png"));
		//preview.setPreserveRatio(true);
		//preview.setFitWidth(256);
		preview = new Canvas(256, 256);
		colorpicker = new ColorPicker();
		
		popup = new Popup();
		
		labelPetType = new Label("#Choose your pet#");
		
		petType = new VBox(15);
		butPetTypeA = new Button("button-cat");
		butPetTypeB = new Button("button-dog");
		butPetTypeC = new Button("button-robot");
		
		labelBackPalet = new Label("#Choose your first colors#");
		
		backPalet = new HBox(2);
		butBckColorA = new Button();
		butBckColorB = new Button();
		butBckColorC = new Button();
		
		labelFrontPalet = new Label("#Choose your second colors#");
		
		frontPalet = new HBox(2);
		butFrtColorA = new Button();
		butFrtColorB = new Button();
		butFrtColorC = new Button();
		
		TopCenterBottom = new VBox(15);
		LeftCenterRight = new HBox(15);
		leftSide = new VBox(15);
		rightSide = new VBox(15);
		validate = new Button("validate");
		
		colorpicker.setOnAction(colorOk);
		
		butBckColorA.setOnAction(bckColorA);
		butBckColorB.setOnAction(bckColorB);
		butBckColorC.setOnAction(bckColorC);
		
		butFrtColorA.setOnAction(frtColorA);
		butFrtColorB.setOnAction(frtColorB);
		butFrtColorC.setOnAction(frtColorC);
		
		butPetTypeA.setOnAction(petTypeA);
		butPetTypeB.setOnAction(petTypeB);
		butPetTypeC.setOnAction(petTypeC);
		
		popup.getContent().add(colorpicker);
		petType.getChildren().addAll(butPetTypeA, butPetTypeB, butPetTypeC);
		backPalet.getChildren().addAll(butBckColorA, butBckColorB, butBckColorC);
		frontPalet.getChildren().addAll(butFrtColorA, butFrtColorB, butFrtColorC);
		
		leftSide.getChildren().addAll(labelBackPalet, backPalet, labelFrontPalet, frontPalet);
		rightSide.getChildren().addAll(labelPetType, petType);
		
		LeftCenterRight.getChildren().addAll(leftSide, preview, rightSide);
		TopCenterBottom.getChildren().addAll(LeftCenterRight, validate);
		
		this.getChildren().addAll(TopCenterBottom);
		colorpicker.hide();
		
		updateStype();
		currentPetType="dog";
		maskPetColor = new Image(Main.GAMEIMAGEPATH+"dog/Coloriage_Chien_Normal.png");
		petColor = new WritableImage((int)maskPetColor.getWidth(), (int)maskPetColor.getHeight());
		generatePet();
		drawLoop.start();
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
	
	public void updateStype() {

		petType.setAlignment(Pos.CENTER);
		leftSide.setAlignment(Pos.CENTER);
		rightSide.setAlignment(Pos.CENTER);
		TopCenterBottom.setAlignment(Pos.CENTER);
		LeftCenterRight.setAlignment(Pos.CENTER);
		
		labelPetType.setAlignment(Pos.BASELINE_LEFT);
		labelBackPalet.setAlignment(Pos.BASELINE_LEFT);
		labelFrontPalet.setAlignment(Pos.BASELINE_LEFT);
		
		labelPetType.setTextAlignment(TextAlignment.LEFT);
		labelBackPalet.setTextAlignment(TextAlignment.LEFT);
		labelFrontPalet.setTextAlignment(TextAlignment.LEFT);

		/*Border border = new Border( new BorderStroke(Color.BLACK, BorderStrokeStyle.DASHED, CornerRadii.EMPTY, BorderWidths.DEFAULT) );
		
		petType.setBorder(border);
		leftSide.setBorder(border);
		rightSide.setBorder(border);*/
	}
	
	public void updateColorImage() {
		
		for( int y=0; y < (int)maskPetColor.getHeight(); y++ ) {
			for ( int x=0; x < (int)maskPetColor.getWidth(); x++ ) {
				
				Color maskColor = maskPetColor.getPixelReader().getColor(x, y);
				//petColor.getPixelWriter().setColor(x, y, maskColor);
				
				if ( maskColor.getOpacity() > 0 ) {
					
					// A
					if( colorA != null && maskColor.getBlue() == 1.0 && maskColor.getRed() == 1.0 && maskColor.getGreen() == 1.0) {
						petColor.getPixelWriter().setColor(x, y, new Color(colorA.getRed(), colorA.getGreen(), colorA.getBlue(), maskColor.getOpacity()));
					}
					// B
					else if( colorB != null && maskColor.getBlue() == 0 && maskColor.getRed() == 0 && maskColor.getGreen() == 0) {
						petColor.getPixelWriter().setColor(x, y, new Color(colorB.getRed(), colorB.getGreen(), colorB.getBlue(), maskColor.getOpacity()));
					}
					// C
					else if( colorC != null && maskColor.getBlue() >= 0.9 && maskColor.getRed() <= 0.1 && maskColor.getGreen() <= 0.1 ) { 
						petColor.getPixelWriter().setColor(x, y, new Color(colorC.getRed(), colorC.getGreen(), colorC.getBlue(), maskColor.getOpacity()));
					}
					// D
					else if( colorD != null && maskColor.getBlue() <= 0.1 && maskColor.getRed() >= 0.9 && maskColor.getGreen() <= 0.1 ) {
						petColor.getPixelWriter().setColor(x, y, new Color(colorD.getRed(), colorD.getGreen(), colorD.getBlue(), maskColor.getOpacity()));
					}
					// E
					else if( colorE != null && maskColor.getBlue() <= 0.1 && maskColor.getRed() <= 0.1 && maskColor.getGreen() >= 0.9 ) {
						petColor.getPixelWriter().setColor(x, y, new Color(colorE.getRed(), colorE.getGreen(), colorE.getBlue(), maskColor.getOpacity()));
					}
				}
			}
		}
		
		try {
			ImageIO.write(SwingFXUtils.fromFXImage(petColor, null), "png", new File("bin/"+Main.GAMEIMAGEPATH+currentPetType+"/colorPet.png"));
		} catch(Exception e) {
			System.err.println(e);
		}
		
	}
	
	public void generatePet() {
		
		if ( previewPet != null )
			previewPet.exit();
		
		switch(currentPetType) {
		case "cat":
			previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Chat_Normal.png", 
								 Main.GAMEIMAGEPATH+currentPetType+"/colorPet.png");
			previewPet.addAnime("heureux", new int[]{0, 1, 2}, new int[]{0, 1, 2});
			previewPet.addAnime("mort", new int[]{0}, new int[]{14});
			break;
		case "dog":
			previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Chien_Normal.png", 
					 			 Main.GAMEIMAGEPATH+currentPetType+"/colorPet.png");
			previewPet.addAnime("heureux", new int[]{0, 1, 2, 3}, new int[]{0, 1, 2, 3});
			previewPet.addAnime("mort", new int[]{0}, new int[]{23});
			break;
		case "robot":
			previewPet = new Pet(Main.GAMEIMAGEPATH+currentPetType+"/Animation_Robot.png", 
					 			 Main.GAMEIMAGEPATH+currentPetType+"/colorPet.png");
			
			previewPet.addAnime("heureux", new int[]{0, 1}, new int[]{0, 1});
			previewPet.addAnime("mort", new int[]{4}, new int[]{7});

			break;
		}
		
		previewPet.setAnime("heureux");
		previewPet.play();
		previewPet.changeTypeColor(2);
	}
	
	public void updatePreview() {
		
		updateColorImage();
		generatePet();
		updateDraw();

	}
	
	public void updateDraw() {
		
		preview.getGraphicsContext2D().clearRect(0, 0, 256, 256);
		
		preview.getGraphicsContext2D().drawImage(previewPet.getColorSprite(),
				previewPet.getColorSprite().getSrcX(), previewPet.getColorSprite().getSrcY(),
				previewPet.getColorSprite().getSrcW(), previewPet.getColorSprite().getSrcH(),
				0, 0,
				256, 256);
		
		preview.getGraphicsContext2D().drawImage(previewPet, previewPet.getSrcX(), previewPet.getSrcY(), 
															 previewPet.getSrcW(), previewPet.getSrcH(),
															 0, 0,
															 256, 256);
	}
	
	@Override
	public void updateText() {
	}
	
	@Override
	public void exit() {
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
		butBckColorA = null;
		butBckColorB = null;
		butBckColorC = null;
		
		labelFrontPalet = null;
		
		frontPalet = null;
		butFrtColorA = null;
		butFrtColorB = null;
		butFrtColorC = null;
		
		TopCenterBottom = null;
		LeftCenterRight = null;
		leftSide = null;
		rightSide = null;
		validate = null;
		
		currentButton = null;
		currentPetType = null;
	}
}
