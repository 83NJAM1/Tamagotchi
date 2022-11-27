package app;

//javasdk import
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.NumberFormat;

//javafx import
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
//app import
import app.controller.Main;

/**
 * Track: Dogs and Cats
 * Music by https://www.fiftysounds.com 
 * Track: Low Battery
 * Music by https://www.fiftysounds.com 
 */

/**
 * 
 * @author ben
 * Fenetre de l'application gerer par JavaFX
 */
public class App extends Application{
	
	//########################### ATTRIBUTS #####################################
	
	private Main mainController;
	private Stage stage;
	private Double stageWidthDiff;
	private Double stageHeightDiff;

	public static ResourceBundle language;
	public static NumberFormat languageNumber;
	
	/*
	TODO : Meurt au bout d’un certain temps
	WIP  : Etat physique (faim, soif,poids)
	WIP  : Chronomètre de la durée de vie du tamagotchi
	WIP  : Système de sauvegarde/chargement (Automatique)
	TODO : Utilisable sur une montre
	WIP  : Etat moral (météo/hygiène)
	TODO : Apparences (Chien, chat, lapin, robot)
	WIP  : Plusieurs salles (Jardin, salon, cuisine, salle de bain)
	TODO : notifications (besoins du tamagotchi)
	TODO : Météo changeante dans le jardin
	TODO : Mange uniquement dans la cuisine
	TODO : Fait du sport que dehors
	TODO : Ajout du mode multijoueur
	TODO : Jouer des mini jeux pour/avec le compagnon
	TODO : Participer à des activités
	TODO : Cuisiner pour son compagnon
	TODO : Evènements affectants les attributs (Rêves, cauchemars affectent le moral)
	TODO : Notification lors de la sauvegarde 
	*/
	public final static String version = "0.0.0";
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent pour changer les dimensions de la fenêtre
	 * déclancheur -> c.Menu -> v.Menu -> v.Option
	 */
	private EventHandler<ActionEvent> choose_dim = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( mainController.getMenu().getView().getOption().getChoosenDim() == 0) {
				stage.setWidth(640+stageWidthDiff);
				stage.setHeight(360+stageHeightDiff);
			}
			else {
				stage.setWidth(1280+stageWidthDiff);
				stage.setHeight(720+stageHeightDiff);
			}
		}
	};
	
	/**
	 * WindowEvent pour sauvegarder si on ferme l'application
	 * déclancheur -> c.Main -> m.Save
	 */
	private EventHandler<WindowEvent> close_app = new EventHandler<WindowEvent>() {
		public void handle(WindowEvent e) {
			mainController.exit();
			System.out.println("Save done");
			System.out.println(language.getString("bye"));
		}
	};

	/**
	 * ActionEvent effectué quand t-on veut charger une partie
	 * déclencheur -> c.Menu -> v.Menu -> v.Load
	 */
	private EventHandler<ActionEvent> load_file = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			System.out.println("Loading... : " + mainController.getMenu().getView().getLoad().getChoosenSave());
			stage.close();
			mainController.getMenu().getView().closeLoad();
			mainController.exit();
			stage.setScene(null);
			mainController=null;
			System.gc();
			
			load();
		}
	};
	
	//############################ METHODES #####################################
	
	public void start(Stage stage) {
		
		// enregistre la référence pour pouvoir fair des actions dessus
		this.stage = stage;
		
		stage.setOnCloseRequest(close_app);
		
		language = ResourceBundle.getBundle("language");
		languageNumber = NumberFormat.getInstance(Locale.FRENCH);
		
		System.out.println(language.getString("wellcome"));
		
		load();
        stageWidthDiff = stage.getWidth() - 640;
        stageHeightDiff = stage.getHeight() - 360;
	}
	
	public void load() {
		mainController = new Main();
		mainController.getMenu().getView().getOption().setDimensionAction(choose_dim);
		mainController.getMenu().getView().getLoad().setValidateAction(load_file);
		
		Scene scene = new Scene( mainController.getView(), 640, 360);
		stage.setScene(scene);
        stage.show();
	}
	
    public static void main(String[] args) {
        launch();
    }
}