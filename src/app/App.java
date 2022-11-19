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

//app import
import app.controller.Main;

/**
 * 
 * @author ben
 * Fenetre de l'application gerer par JavaFX
 */
public class App extends Application{
	
	//########################### ATTRIBUTS #####################################
	
	private Main mainController;
	private Stage stage;
	
	//fichiers .properties requis accesible dans le ./res à mettre dans le dossier ./bin
	//soit ./bin/language.properties et ./bin/language_en.properties
	public static ResourceBundle language;
	public static NumberFormat languageNumber;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * ActionEvent pour changer les dimensions de la fenêtre
	 * déclancheur -> c.Menu -> v.Menu -> v.Option
	 */
	private EventHandler<ActionEvent> choose_dim = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( mainController.getMenu().getView().getOption().getChoosenDim() == 0) {
				stage.setWidth(640);
				stage.setHeight(360);
			}
			else {
				stage.setWidth(1280);
				stage.setHeight(720);
			}
		}
	};
	
	//############################ METHODES #####################################
	
	public void start(Stage stage) {
		
		// enregistre la référence pour pouvoir fair des actions dessus
		this.stage = stage;
		
		//fichiers .properties requis accesible dans le ./res à mettre dans le dossier ./bin
		//soit ./bin/language.properties et ./bin/language_en.properties
		language = ResourceBundle.getBundle("language");
		languageNumber = NumberFormat.getInstance(Locale.FRENCH);
		
		System.out.println(language.getString("wellcome"));
		
		mainController = new Main();
		mainController.getMenu().getView().getOption().setDimensionAction(choose_dim);
		
		Scene scene = new Scene( mainController.getView(), 640, 360);
		stage.setScene(scene);
        stage.show();
	}
	
    public static void main(String[] args) {
    	
        launch();
        System.out.println(language.getString("bye"));
    }
}