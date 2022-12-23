package app.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

import app.App;
import app.Reinstanciable;
import app.TextDisplayable;

/**
 * 
 * @author ben
 * view.CustomPet est de type AnchorPane
 * car le choix de design implique qu'il soit composé de sous vue
 */
public class CustomPet extends StackPane implements Reinstanciable, TextDisplayable {

	//########################### ATTRIBUTS #####################################
	
	// popup
	private ColorPicker colorpicker;
	private Popup popup;
	
	// partie centrale
	private ImageView preview;
	
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
	private Button butBckColorB;
	private Button butBckColorC;
	
	// partie gauche-bas
	private Label labelFrontPalet;
	private HBox frontPalet;
	private Button butFrtColorA;
	private Button butFrtColorB;
	private Button butFrtColorC;
	
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
	 * valider couleur choisie
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> colorOk = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton.setBackground(new Background(new BackgroundFill( colorpicker.getValue(), null, null) ) );
			popup.hide();
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
			preview.setImage(new Image("res/test_cat.png"));
			currentPetType="cat";
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			preview.setImage(new Image("res/test_dog.png"));
			currentPetType="dog";
		}
	};
	/**
	 * fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			preview.setImage(new Image("res/test_robot.png"));
			currentPetType="robot";
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public CustomPet() {
		
		preview = new ImageView(new Image("res/test_dog.png"));
		preview.setPreserveRatio(true);
		preview.setFitWidth(256);
		colorpicker = new ColorPicker();
		
		popup = new Popup();
		
		labelPetType = new Label("Choose your pet");
		
		petType = new VBox(15);
		butPetTypeA = new Button("cat");
		butPetTypeB = new Button("dog");
		butPetTypeC = new Button("robot");
		
		labelBackPalet = new Label("Choose your first colors");
		
		backPalet = new HBox(2);
		butBckColorA = new Button();
		butBckColorB = new Button();
		butBckColorC = new Button();
		
		labelFrontPalet = new Label("Choose your second colors");
		
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
	
	@Override
	public void updateText() {
	}
	
	@Override
	public void exit() {
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
