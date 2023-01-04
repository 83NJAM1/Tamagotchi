package app.view;
 
import java.io.File;
import java.io.FilenameFilter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import app.App;
import app.Cleanable;
import app.Localisable;
/**
 * 
 * @author ben
 * view.Load est de type StackPane
 */
public class Load extends StackPane implements Cleanable, Localisable {
	
	//########################### ATTRIBUTS #####################################
	
	public static final String SAVEPATH = Main.USERPATH+"saves/"; 
	
	private int currentIndex;
	private ObservableList<String> savesObsList;
	private ListView<String> savesList;
	private HBox savesContent;
	private VBox allContent;
	private Button butLeft;
	private Button butRight;
	private Button butValidate;
	private Button butCancel;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * change l'indice de selection vers la gauche
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_prev = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( currentIndex > 0) {
				savesList.scrollTo(--currentIndex);
				savesList.getSelectionModel().select(currentIndex);
			}
		}
	};
	
	/**
	 * change l'indice de selection vers la droite
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> click_next = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( currentIndex < savesList.getItems().size()-1) {
				savesList.scrollTo(++currentIndex);
				savesList.getSelectionModel().select(currentIndex);
			}
		}
	};
	
	/**
	 * change l'indice courant de selection à celle chosie par le clique
	 * déclencheur -> this
	 */
	private EventHandler<MouseEvent> click_elem = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			currentIndex=savesList.getSelectionModel().getSelectedIndex();
		}
	};
	
	//############################ METHODES #####################################
	
	/**
	 * constructeur
	 */
	public Load() {
		
		File saveDir = new File(SAVEPATH);
		
		if ( !saveDir.exists() ) {
			saveDir.mkdir();
		}
		
		//instanciation
		savesList = new ListView<String>();
		allContent = new VBox();
		savesContent = new HBox();
		butLeft = new Button();
		butRight = new Button();
		butValidate = new Button();
		butCancel = new Button();
		
		//initialisation
		updateFilesList();
		savesList.setItems(savesObsList);
		currentIndex = 0;
		updateText();
		updateStyle();
		
		//assignation action
		savesList.setOnMouseClicked(click_elem);
		butRight.setOnAction(click_next);
		butLeft.setOnAction(click_prev);
		
		//construction
		savesContent.getChildren().addAll(butLeft, savesList, butRight);
		allContent.getChildren().addAll(savesContent, butValidate, butCancel);
		this.getChildren().add(allContent);
	}
	
	/**
	 * Met à jour la liste des fichiers de sauvegardes
	 */	
	public void updateFilesList() {
		savesObsList = FXCollections.observableArrayList(new File(SAVEPATH).list( new FilenameFilter() { 
			public boolean accept(File dir, String name) {
				return name.matches(".*[.]tmg");
			}
		}));
		System.out.println("number of saves: " + savesObsList.size());
	}
		
	/**
	 * définit l'action pour le bouton annuler chargement
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionButtonCancel(EventHandler<ActionEvent> e) {
		butCancel.setOnAction(e);
	}
	
	/**
	 * définit l'action pour le bouton valider chargement
	 * @param e EventHandler qui gère l'action
	 */
	public void setActionButtonValidate(EventHandler<ActionEvent> e) {
		butValidate.setOnAction(e);
	}

	/**
	 * obtient le nom du fichier selectionné
	 * @return le nom du fichier
	 */
	public String getChoosenSave() {
		return savesList.getSelectionModel().getSelectedItem();
	}
	
	public void updateStyle() {
		//this.setBackground(new Background(new BackgroundFill( Color.BLUE, null, null) ) );
		savesList.setOrientation(Orientation.HORIZONTAL);
		savesList.setPrefSize(1280, 360);
		savesContent.setBackground(new Background(new BackgroundFill( Color.WHITESMOKE, null, null) ) );
		savesContent.setAlignment(Pos.CENTER);
		savesContent.setSpacing(15);
		allContent.setFillWidth(true);
		allContent.setAlignment(Pos.CENTER);
		allContent.setSpacing(15);
		butLeft.setMinSize(100, BASELINE_OFFSET_SAME_AS_HEIGHT);
		butRight.setMinSize(100, BASELINE_OFFSET_SAME_AS_HEIGHT);
	}
	
	@Override
	public void updateText() {
		butLeft.setText(App.getString("button-prev"));
		butRight.setText(App.getString("button-next"));
		butValidate.setText(App.getString("button-validate"));
		butCancel.setText(App.getString("button-cancel"));
	}
	
	@Override
	public void clean() {
		savesObsList.clear();
		savesObsList = null;
		savesList = null;
		savesContent = null;
		allContent = null;
		butLeft = null;
		butRight = null;
		butValidate = null;
		butCancel = null;
	}
}
