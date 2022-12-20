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

	public static ResourceBundle language;
	public static NumberFormat languageNumber;
	
	public static Double x_window;
	public static Double y_window;
	
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
	public final static String version = "0.0.0";
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent pour changer les dimensions de la fenêtre
	 * déclancheur -> v.Option
	 */
	private EventHandler<ActionEvent> choose_dim = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( mainController.getMenu().getView().getOption().getChoosenDim() == 0) {
				stage.setWidth(640+stageWidthDiff);
				stage.setHeight(360+stageHeightDiff);
				mainController.getMenu().getOption().setWindowWidth(640);
				mainController.getMenu().getOption().setWindowHeight(360);
				mainController.changeGameDim(0);
			}
			else {
				stage.setWidth(1280+stageWidthDiff);
				stage.setHeight(720+stageHeightDiff);
				mainController.getMenu().getOption().setWindowWidth(1280);
				mainController.getMenu().getOption().setWindowHeight(720);
				mainController.changeGameDim(1);
			}

			mainController.getMenu().getOption().save();
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
			System.out.println(language.getString("bye"));
		}
	};
 
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> v.Option
	 */
	ChangeListener<Number> change_x = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			x_window = vnew.doubleValue();
		}
	};
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> v.Option
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
	private EventHandler<ActionEvent> load_file = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			String pathsave = mainController.getMenu().getView().getLoad().getChoosenSave();
			mainController.saveGame();
			stage.close();
			mainController.getMenu().getView().closeLoad();
			mainController.exit();
			stage.setScene(null);
			mainController=null;
			System.gc();
			
			load(pathsave);
		}
	};
	
	//############################ METHODES #####################################
	
	@Override
	public void start(Stage stage) {
		
		// enregistre la référence pour pouvoir faire des actions dessus
		this.stage = stage;
		
		stage.setOnCloseRequest(close_app);
		
		stage.xProperty().addListener(change_x);
		stage.yProperty().addListener(change_y);
		
		language = ResourceBundle.getBundle("language");
		languageNumber = NumberFormat.getInstance(Locale.FRENCH);
		
		load(null);
		
        stageWidthDiff = stage.getWidth() - mainController.getMenu().getOption().getWindowWidth();
        stageHeightDiff = stage.getHeight() - mainController.getMenu().getOption().getWindowHeight();
		x_window = stage.getX();
		y_window = stage.getY();
		
        System.out.println(language.getString("wellcome"));
	}
	
	public void load(String pathsave) {
		mainController = new Main(pathsave);
		mainController.getMenu().getView().getOption().setDimensionAction(choose_dim);
		mainController.getMenu().getView().getLoad().setValidateAction(load_file);
		
		Scene scene = new Scene( mainController.getView(), 
								 mainController.getMenu().getOption().getWindowWidth()  , 
								 mainController.getMenu().getOption().getWindowHeight() );
		stage.setScene(scene);
        stage.show();
	}
	
	public double getX() {
		return x_window;
	}
	public double getY() {
		return x_window;
	}
	
    public static void main(String[] args) {
    	System.out.println("v-"+version+Locale.FRENCH);
        launch();
    }
}