package app.view;
 
import app.App;
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
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Popup;

/**
 * 
 * @author ben
 * view.CustomPet est de type AnchorPane
 * car le choix de design implique qu'il soit composé de sous vue
 */
public class CustomPet extends StackPane {

	//########################### ATTRIBUTS #####################################
	
	private ImageView preview;
	private ColorPicker colorpicker;
	private Popup popup;
	
	private Label labelPetType;
	
	private VBox petType;
	private Button butPetTypeA;
	private Button butPetTypeB;
	private Button butPetTypeC;
	
	private Label labelBackPalet;
	
	private HBox backPalet;
	private Button butBckColorA;
	private Button butBckColorB;
	private Button butBckColorC;
	
	private Label labelFrontPalet;
	
	private HBox frontPalet;
	private Button butFrtColorA;
	private Button butFrtColorB;
	private Button butFrtColorC;
	
	private VBox TopCenterBottom;
	private HBox LeftCenterRight;
	private VBox leftSide;
	private VBox rightSide;
	private Button validate;
	
	private Button currentButton;
	
	private String petId;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action valider couleur choisie
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> colorOk = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton.setBackground(new Background(new BackgroundFill( colorpicker.getValue(), null, null) ) );
			popup.hide();
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> bckColorA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorA;
			popup.show(butBckColorA, App.x_window+butBckColorA.getLocalToSceneTransform().getTx(), 
					 				 App.y_window+butBckColorA.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> bckColorB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorB;
			popup.show(butBckColorB, App.x_window+butBckColorB.getLocalToSceneTransform().getTx(), 
					 				 App.y_window+butBckColorB.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> bckColorC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butBckColorC;
			popup.show(butBckColorC, App.x_window+butBckColorC.getLocalToSceneTransform().getTx(), 
									 App.y_window+butBckColorC.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorA;
			popup.show(butFrtColorA, App.x_window+butFrtColorA.getLocalToSceneTransform().getTx(), 
					 				 App.y_window+butFrtColorA.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorB;
			popup.show(butFrtColorB, App.x_window+butFrtColorB.getLocalToSceneTransform().getTx(), 
					 				 App.y_window+butFrtColorB.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> frtColorC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			currentButton = butFrtColorC;
			popup.show(butFrtColorC, App.x_window+butFrtColorC.getLocalToSceneTransform().getTx(), 
									 App.y_window+butFrtColorC.getLocalToSceneTransform().getTy());
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorA
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeA = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			preview.setImage(new Image("res/test_cat.png"));
			petId="cat";
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorB
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeB = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			preview.setImage(new Image("res/test_dog.png"));
			petId="dog";
		}
	};
	/**
	 * Action fait apperaître le popup choix des couleurs pour backColorC
	 * déclencheur -> this
	 */ 
	private EventHandler<ActionEvent> petTypeC = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			preview.setImage(new Image("res/test_robot.png"));
			petId="robot";
		}
	};
	
	//############################ METHODES #####################################
	
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
		petId="dog";
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
	
	public void setActionValidate(EventHandler<ActionEvent> e) {
		validate.setOnAction(e);
	}
	
	public String getPetType() {
		return petId;
	}
}
