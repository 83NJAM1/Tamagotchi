package app;

//JDK
import java.util.Locale;
import java.util.ResourceBundle;
import java.text.NumberFormat;

//JFX
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

//APP
import app.controller.Main;

/**
 * 
 * @author ben
 * Fenetre de l'application gerer par JavaFX
 */
public class App extends Application{
	
	//########################### ATTRIBUTS #####################################
	
	private Stage stage;
	private Main mainController;
	
	// la taille des bordures du gestionnaire de fenêtre
	private Double stageWidthDiff;
	private Double stageHeightDiff;
	
	// gestion de la localisation
	private static ResourceBundle language;
	private static NumberFormat languageNumber;
	
	// position de la fenêtre 
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
			if ( mainController.getControllerMenu().getView().getChildOption().getChoosenDefinitionIndex() == 0) {
				stage.setWidth(640+stageWidthDiff);
				stage.setHeight(360+stageHeightDiff);
				mainController.getControllerMenu().getModelOption().setWindowWidth(640);
				mainController.getControllerMenu().getModelOption().setWindowHeight(360);
				mainController.changeGameDefinition(0);
			}
			else {
				stage.setWidth(1280+stageWidthDiff);
				stage.setHeight(720+stageHeightDiff);
				mainController.getControllerMenu().getModelOption().setWindowWidth(1280);
				mainController.getControllerMenu().getModelOption().setWindowHeight(720);
				mainController.changeGameDefinition(1);
			}

			mainController.saveOption();
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
			String saveName = mainController.getControllerMenu().getView().getChildLoad().getChoosenSave();
			mainController.saveGame();
			
			//detruit tout
			stage.close();
			mainController.getControllerMenu().getView().closeLoad();
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
			mainController.getControllerMenu().getView().closeLoad();
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
        stageWidthDiff = stage.getWidth() - mainController.getControllerMenu().getModelOption().getWindowWidth();
        stageHeightDiff = stage.getHeight() - mainController.getControllerMenu().getModelOption().getWindowHeight();
		x_window = stage.getX();
		y_window = stage.getY();
		
		// message de lancement
        System.out.println(language.getString("wellcome"));
	}
	
	/**
	 * initalise le controller principale ce qui implique l'intialisation de ses controller enfants
	 * @param saveName le nom de la sauvegarde à charger pour initialiser le jeu. Si null une nouvelle partie est créée.
	 */
	private void initMain(String saveName) {
		
		// initialise la vue principale incluant la vue du jeu
		mainController = new Main(saveName);
		mainController.getControllerMenu().getView().getChildOption().setActionChoiceBoxDefinition(choose_definition);
		mainController.getControllerMenu().getView().getChildLoad().setActionButtonValidate(load_game);
		mainController.getControllerMenu().getView().setActionButtonNew(new_game);
		
		// initialise la scene utilisé pour affocher la vue principale
		Scene scene = new Scene( mainController.getView(), 
								 mainController.getControllerMenu().getModelOption().getWindowWidth()  , 
								 mainController.getControllerMenu().getModelOption().getWindowHeight() );
		
		// la scene a utilisé pour l'application
		stage.setScene(scene);
		
		if ( x_window != null )
			stage.setX(x_window);
		
		if ( y_window != null )
			stage.setY(y_window);
		
        stage.show();
	}
	
	/**
	 * obtient la position x de la fenêtre
	 * @return x
	 */
	public static double getX() {
		return x_window;
	}
	/**
	 * obtient la position y de la fenêtre
	 * @return y
	 */
	public static double getY() {
		return y_window;
	}
	/**
	 * définit la langue à utiliser
	 * @param local définissant la région shouaité
	 */
	public static void setLocale(Locale local) {
		App.language = ResourceBundle.getBundle("language", local);
		App.languageNumber = NumberFormat.getNumberInstance(local);
	}
	/**
	 * obtient un texte localisé
	 * @param key la clé correspondant à la chaîne
	 * @return la chaîne dans la langue courament utilisé par l'application
	 */
	public static String getString(String key) {
		return language.getString(key);
	}
	/**
	 * obtient un nombre localisé
	 * @param number la valeur
	 * @return le nombre en chaîne de charactère dans la langue courament utilisé par l'application
	 */
	public static String getString(Double number) {
		return languageNumber.format(number);
	}
	/**
	 * obtient la localisation actuelement définit pour l'application
	 * @return l'objet de localisation
	 */
	public static Locale getLocale() {
		return language.getLocale();
	}
	/**
	 * obtient la version de l'application
	 * @return une chaîne de charactère définissant la version
	 */
	public static String getVersion() {
		return version;
	}
	/**
	 * point d'entré de l'application
	 */
    public static void main(String[] args) {
    	System.out.println("v"+getVersion());
        launch();
    }
}