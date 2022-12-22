package app;

//javasdk import
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.NumberFormat;

//javafx import
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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

	private static ResourceBundle language;
	private static NumberFormat languageNumber;
	
	private static Double x_window;
	private static Double y_window;
	
	/* 
	WIP  : Meurt au bout d’un certain temps
	WIP  : Etat physique (faim, soif,poids)
	WIP  : Chronomètre de la durée de vie du tamagotchi
	WIP  : Système de sauvegarde/chargement (Automatique)
	TODO : Utilisable sur une montre
	WIP  : Etat moral (météo/hygiène)
	WIP  : Apparences (Chien, chat, lapin, robot)
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
	private final static String version = "0.0.7";
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent pour changer la definition de la fenêtre
	 * déclancheur -> v.Option
	 */
	private EventHandler<ActionEvent> choose_definition = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( mainController.getChildMenu().getView().getChildOption().getChoosenDefinitionIndex() == 0) {
				stage.setWidth(640+stageWidthDiff);
				stage.setHeight(360+stageHeightDiff);
				mainController.getChildMenu().getModelOption().setWindowWidth(640);
				mainController.getChildMenu().getModelOption().setWindowHeight(360);
				mainController.changeGameDim(0);
			}
			else {
				stage.setWidth(1280+stageWidthDiff);
				stage.setHeight(720+stageHeightDiff);
				mainController.getChildMenu().getModelOption().setWindowWidth(1280);
				mainController.getChildMenu().getModelOption().setWindowHeight(720);
				mainController.changeGameDim(1);
			}

			mainController.getChildMenu().getModelOption().save();
		}
	};
	
	/**
	 * WindowEvent pour sauvegarder si on ferme l'application
	 * déclancheur -> this
	 */
	private EventHandler<WindowEvent> close_app = new EventHandler<WindowEvent>() {
		public void handle(WindowEvent e) {
			mainController.saveGame();
			mainController.exit();
			System.out.println(getString("bye"));
		}
	};
 
	/**
	 * Action effectué quand t-on modifie la position x de la fenêtre
	 * déclencher -> this
	 */
	ChangeListener<Number> change_x = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			x_window = vnew.doubleValue();
		}
	};
	
	/**
	 * Action effectué quand t-on modifie la position y de la fenêtre
	 * déclencher -> this
	 */
	ChangeListener<Number> change_y = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			y_window = vnew.doubleValue();
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on veut charger une partie
	 * déclencheur -> c.Menu -> v.Menu -> v.Load
	 */
	private EventHandler<ActionEvent> load_game = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			//sauvegarde les données
			String saveName = mainController.getChildMenu().getView().getChildLoad().getChoosenSave();
			mainController.saveGame();
			
			//detruit tout
			stage.close();
			mainController.getChildMenu().getView().closeLoad();
			mainController.exit();
			stage.setScene(null);
			mainController=null;
			System.gc();
			
			//reconstruit
			initMain(saveName);
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on veut recommencer une partie
	 * déclencheur -> c.Menu -> v.Menu -> v.Load
	 */
	private EventHandler<ActionEvent> new_game = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			//sauvegarde les données
			mainController.saveGame();
			
			//detruit tout
			stage.close();
			mainController.getChildMenu().getView().closeLoad();
			mainController.exit();
			stage.setScene(null);
			mainController=null;
			System.gc();
			
			//reconstruit
			initMain("new");
		}
	};
	
	//############################ METHODES #####################################
	
	@Override
	public void start(Stage stage) {
		
		// enregistre la référence pour pouvoir faire des actions dessus 
		// lors d'un chargement ou d'une nouvelle partie
		this.stage = stage;
		
		// associe les actions pour l'application
		stage.setOnCloseRequest(close_app);
		stage.xProperty().addListener(change_x);
		stage.yProperty().addListener(change_y);
		
		// definit la langue par default
		language = ResourceBundle.getBundle("language");
		languageNumber = NumberFormat.getInstance(Locale.FRENCH);
		
		// initialise le main sans spécifier de sauvegarde 
		initMain(null);
		
		// donnée traité ultérieurement
        stageWidthDiff = stage.getWidth() - mainController.getChildMenu().getModelOption().getWindowWidth();
        stageHeightDiff = stage.getHeight() - mainController.getChildMenu().getModelOption().getWindowHeight();
		x_window = stage.getX();
		y_window = stage.getY();
		
		// message de lancement
        System.out.println(language.getString("wellcome"));
	}
	
	private void initMain(String saveName) {
		
		// initialise la vue principale incluant la vue du jeu
		mainController = new Main(saveName);
		mainController.getChildMenu().getView().getChildOption().setActionChoiceBoxDefinition(choose_definition);
		mainController.getChildMenu().getView().getChildLoad().setActionButtonValidate(load_game);
		mainController.getChildMenu().getView().setActionButtonNew(new_game);
		
		// initialise la scene utilisé pour affocher la vue principale
		Scene scene = new Scene( mainController.getView(), 
								 mainController.getChildMenu().getModelOption().getWindowWidth()  , 
								 mainController.getChildMenu().getModelOption().getWindowHeight() );
		
		// la scene a utilisé pour l'application
		stage.setScene(scene);
        stage.show();
	}
	
	public static double getX() {
		return x_window;
	}
	public static double getY() {
		return y_window;
	}
	public static void setLocale(Locale local) {
		App.language = ResourceBundle.getBundle("language", local);
		App.languageNumber = NumberFormat.getNumberInstance(local);
	}
	public static String getString(String key) {
		return language.getString(key);
	}
	public static String getString(Double number) {
		return languageNumber.format(number);
	}
	public static Locale getLocale() {
		return language.getLocale();
	}
	public static String getVersion() {
		return version;
	}
	
    public static void main(String[] args) {
    	System.out.println("v"+getVersion());
        launch();
    }
}