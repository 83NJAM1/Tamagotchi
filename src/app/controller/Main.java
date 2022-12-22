package app.controller;

import java.nio.file.Paths;
import java.util.Locale;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
import javafx.scene.media.MediaException;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import app.App;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main {
	 
	//########################### ATTRIBUTS #####################################
	
	private app.view.Main mainView;
	
	private Menu menuController;
	private Game gameController;
	private Media musicBackground;
	private Media musicGameover;
	private MediaPlayer mediaplayer;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action effectué quand t-on perd la partie
	 * déclencher -> c.Game
	 */
	ChangeListener<Boolean> change_gameover_value = new ChangeListener<Boolean>() {
		public void changed(ObservableValue<? extends Boolean> obs, Boolean vold, Boolean vnew) {
			setGameoverMusic();
		}
	};
	
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> v.Option
	 */
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			menuController.setVolume(vnew.doubleValue());
			mediaplayer.setVolume(vnew.doubleValue());
			
			menuController.getModelOption().save();
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on change la langue via la vue Option
	 * déclencher -> v.Option
	 */
	private EventHandler<ActionEvent> choose_lang = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			Locale loc;
			if ( menuController.getView().getChildOption().getChoosenLanguageIndex() == 0) {
				loc = Locale.FRENCH;
			}
			else {
				loc = Locale.ENGLISH;
			}
			
			App.setLocale(loc);
			
			updateText();
			menuController.getModelOption().setLanguage(loc.toString());
			menuController.getModelOption().save();
			
			System.out.println("Source -> : " + e.getSource().getClass().getName() );
			System.out.println("Locale:" + loc.getDisplayLanguage());
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on veut crer une nouvelle partie
	 * déclencheur -> this
	 */
	private EventHandler<ActionEvent> new_game_generation = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			if ( gameController != null ) {
				gameController.exit();
			}
			gameController = new Game(mainView.getCustomPet().getPetType(), "test", "save.tmg");
			mainView.initWithCustomPet(gameController.getView(), menuController.getView());
			
			//NOTE : ne pas faire de mise a jour du texte sinon bug
			
			changeGameDim(menuController.getView().getChildOption().getChoosenDefinitionIndex());
			gameController.gameover.addListener(change_gameover_value);
		}
	};
	//############################ METHODES #####################################

	public Main(String saveName) {
		
		// instancie le menuController et initialise les options
		menuController = new Menu();
		menuController.loadOption();
		
		String lastSaveName = menuController.getModelOption().getLastSave();
		
		initGame(lastSaveName, saveName);
		
		musicBackground = loadMediaFile("bin/res/musiques/DogsAndCats", "mp3");
		musicGameover = loadMediaFile("bin/res/musiques/LowBattery", "mp3");
		setBackgroundMusic();
		
		// construction
		menuController.getView().getChildOption().setActionChoiceBoxLanguage(choose_lang);
		menuController.getView().getChildOption().setActionSliderVolume(change_volume_value);
	}
	
	public void initGame(String lastSaveName, String saveName) {
		
		// creer une nouvelle partie en écrasant la partie en cours
		if ( saveName != null && saveName.equals("new") && lastSaveName != null ) {
			System.out.println("NEW GAME: " + lastSaveName);
			// crée une nouvelle partie, la customisation devrai être demandé ici
			mainView = new app.view.Main();
			mainView.getCustomPet().setActionValidate(new_game_generation);
		}
		// charge la sauvegarde selectionnée
		else if ( saveName != null ) {
			System.out.println("LOAD: " + saveName);
			menuController.getModelOption().setLastSave(saveName);
			menuController.getModelOption().save();
			gameController = new Game(saveName);
			mainView = new app.view.Main( gameController.getView(), menuController.getView() );
		}
		// charge la dernière sauvegarde utilisée
		else if ( lastSaveName != null ) {
			System.out.println("CONTINUE: " + lastSaveName);
			gameController = new Game(lastSaveName);
			mainView = new app.view.Main( gameController.getView(), menuController.getView() );
		}
		// crée un nouveau fichier de sauvgarde
		else {
			System.out.println("FIRST GAME: save.tmg");
			menuController.getModelOption().setLastSave("save.tmg");
			menuController.getModelOption().save();
			// crée une nouvelle partie, la customisation devrai être demandé ici
			mainView = new app.view.Main();
			mainView.getCustomPet().setActionValidate(new_game_generation);
		}
		
		if ( gameController != null ) {
			updateText();
			changeGameDim(menuController.getView().getChildOption().getChoosenDefinitionIndex());
			gameController.gameover.addListener(change_gameover_value);
		}
	}
	/**
	 * Met à jour le texte de tous les élements
	 */
	public void updateText() {
		menuController.getView().updateText();
		gameController.getView().updateText();
		gameController.getChildPet().updateText();
	}
	
	/*
	 * Change les dimensions de rendu du jeu
	 */
	public void changeGameDim(int numChoice) {
		gameController.getView().changeDefinition(numChoice);
	}
	
	/**
	 * Essaie de charger le media avec le format voulue sinon essaie avec d'autres formats
	 * @param pathfileNOEXT le chemin du fichier sans son extension
	 * @param ext l'extension voulue
	 * @return le media si aucune erreur
	 */
	public Media loadMediaFile(String pathfileNOEXT, String ext) {

		Media result = null;
		
		try {
			if (ext.equals("mp3"))
				result = new Media(Paths.get(pathfileNOEXT+".mp3").toUri().toString());
			else if (ext.equals("wav"))
				result = new Media(Paths.get(pathfileNOEXT+".wav").toUri().toString());
			
		} catch(MediaException e) {
			if (ext.equals("mp3")) {
				System.err.println(e + "\nRetry with .wav");
				result = loadMediaFile(pathfileNOEXT, "wav");
			}
			else if (ext.equals("wav")) {
				// si autre format faire un autre appel
				// sinon afficher erreur
				System.err.println(e);
				System.exit(1);
			}
			else {
				System.err.println(e);
				System.exit(1);
			}
		}
		
		return result;
	}
	
	/**
	 * Reinstancie le mediaplayer et joue la musique du jeu
	 * affiche aussi les metadatas du media de manière asynchrone (autre thread)
	 */
	public void setBackgroundMusic() {
		mediaplayer = new MediaPlayer(musicBackground);
		mediaplayer.setOnReady( new Runnable() {
	        @Override
	        public void run() {
	        	System.out.println("Play: " + musicBackground.getSource()); 
    			System.out.println(("       title: " + musicBackground.getMetadata().get("title") + "\n"
	        					   +"album artist: " + musicBackground.getMetadata().get("album artist") + "\n"
	        					   +"      artist: " + musicBackground.getMetadata().get("artist")).indent(2));
	        }
	    });
		mediaplayer.setStopTime(Duration.seconds(113));
		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaplayer.play();
		mediaplayer.setVolume(menuController.getVolume());

	}
	
	/**
	 * Reinstancie le mediaplayer et joue la musique du Gomeover
	 * affiche aussi les metadatas du media de manière asynchrone (autre thread)
	 */
	public void setGameoverMusic() {
		mediaplayer.stop();
		mediaplayer = new MediaPlayer(musicGameover);
		mediaplayer.setOnReady( new Runnable() {
	        @Override
	        public void run() {
	        	System.out.println("Play: " + musicGameover.getSource()); 
    			System.out.println(("      title : " + musicGameover.getMetadata().get("title") + "\n"
	        					   +"album artist: " + musicGameover.getMetadata().get("album artist") + "\n"
	        					   +"     artist : " + musicGameover.getMetadata().get("artist")).indent(2));
	        }
	    });
		mediaplayer.play();
		mediaplayer.setVolume(menuController.getVolume());
	}
	
	/*
	 * Sauvegarde les données de jeu
	 */
	public void saveGame() {
		if ( gameController != null ) {
			gameController.save();
			System.out.println("Save done");
		}
	}
	
	public app.view.Main getView() {
		return mainView;
	}
	
	public Menu getChildMenu() {
		return menuController;
	}
	
	public Game getChildGame() {
		return gameController;
	}
	
	/**
	 * Stop les threads 
	 * + Met tous a null, pour aider au mieu le Garbage Collector
	 */
	public void exit() {
		mainView.exit();
		mediaplayer.stop();
		if ( gameController != null ) {
			gameController.exit();
		}
		menuController.exit();
		mainView = null;
		menuController = null;
		gameController = null;
		choose_lang = null;
		mediaplayer=null;
		musicBackground=null;
		musicGameover=null;
	}
}
