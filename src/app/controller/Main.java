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
import app.Componable;
import app.Localisable;

/**
 * 
 * @author ben
 * Controller principale encapsulant tout
 * il fait le lien avec le thread javafx
 */
public class Main implements Componable, Localisable {
	 
	//########################### ATTRIBUTS #####################################
	
	// l'ensemble des interfaces
	private app.view.Main mainView;
	
	// gère le menu
	private Menu menuController;
	
	// gère le jeu
	private Game gameController;
	
	// medias
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
			playGameoverMusic();
		}
	};
	
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> v.Option
	 */
	ChangeListener<Number> change_volume_value = new ChangeListener<Number>() {
		public void changed(ObservableValue<? extends Number> obs, Number vold, Number vnew) {
			menuController.getModelOption().setVolume(vnew.doubleValue());
			mediaplayer.setVolume(vnew.doubleValue());
			
			menuController.saveOption();
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
			
			// enregistrer la localisation dans les configurations
			menuController.getModelOption().setLanguage(loc.toString());
			saveOption();
			
			// appliquer la localisation pour l'application
			App.setLocale(loc);
			updateText();
			
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
			gameController = new Game(mainView.getChildCustomPet().getPetType(), "test", "save.tmg");
			mainView.init(gameController.getView());
			
			//NOTE : ne pas faire de mise a jour du texte ici, cela cause un bug
			
			changeGameDefinition(menuController.getView().getChildOption().getChoosenDefinitionIndex());
			gameController.gameover.addListener(change_gameover_value);
		}
	};
	
	//############################ METHODES #####################################

	/**
	 * Constructeur
	 * @param saveName le nom de la sauvegarde à charger. Peut être null
	 */
	public Main(String saveName) {
		
		// instancier le menuController et initialiser les options
		menuController = new Menu();
		menuController.loadOption();
		
		// nom de la dernière sauvegarde enregistré
		String lastSaveName = menuController.getModelOption().getLastSave();
		
		// afficher le jeu si charge/continue une partie
		// sinon afficher creation de pet lors d'une nouvelle partie
		initView(lastSaveName, saveName);
		
		// charger les fichier de musique en mémoire
		musicBackground = loadMediaFile("bin/res/musiques/DogsAndCats", "mp3");
		musicGameover = loadMediaFile("bin/res/musiques/LowBattery", "mp3");
		
		// jouer le muisque d'arrière plan
		playBackgroundMusic();
		
		// association des actions aux objets
		menuController.getView().getChildOption().setActionChoiceBoxLanguage(choose_lang);
		menuController.getView().getChildOption().setActionSliderVolume(change_volume_value);
	}
	
	/**
	 * initialise la vue principale
	 * @param lastSaveName le nom de la dernière sauvegarde enregistré. Peut être null
	 * @param saveName le nom de la sauvegarde à charger. Peut être null
	 */
	public void initView(String lastSaveName, String saveName) {
		
		// creer une nouvelle partie en écrasant la partie en cours
		if ( saveName != null && saveName.equals("new") && lastSaveName != null ) {
			System.out.println("NEW GAME: " + lastSaveName);
			mainView = new app.view.Main(menuController.getView());
			mainView.getChildCustomPet().setActionValidate(new_game_generation);
		}
		// charge la sauvegarde selectionnée
		else if ( saveName != null ) {
			System.out.println("LOAD: " + saveName);
			menuController.getModelOption().setLastSave(saveName);
			menuController.saveOption();
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
			menuController.saveOption();
			mainView = new app.view.Main(menuController.getView());
			mainView.getChildCustomPet().setActionValidate(new_game_generation);
		}
		
		if ( gameController != null ) {
			updateText();
			changeGameDefinition(menuController.getView().getChildOption().getChoosenDefinitionIndex());
			gameController.gameover.addListener(change_gameover_value);
		}
	}
	
	/*
	 * Change la définition du cadre pour le rendu du jeu
	 */
	public void changeGameDefinition(int numChoice) {
		gameController.getView().changeDefinition(numChoice);
	}
	
	/**
	 * charge un media sous un format supporté par le système.
	 * si erreur, recharge via appel récursife jusqu'à plus de possibilité.
	 * @param relativeNameFileNOEXT le nom relatif du fichier sans son extension
	 * @param ext l'extension prioritaire
	 * @return le media si aucune erreur
	 */
	public Media loadMediaFile(String relativeNameFileNOEXT, String ext) {

		Media result = null;
		
		try {
			if (ext.equals("mp3"))
				result = new Media(Paths.get(relativeNameFileNOEXT+".mp3").toUri().toString());
			else if (ext.equals("wav"))
				result = new Media(Paths.get(relativeNameFileNOEXT+".wav").toUri().toString());
			
		} catch(MediaException e) {
			if (ext.equals("mp3")) {
				System.err.println(e + "\nRetry with .wav");
				result = loadMediaFile(relativeNameFileNOEXT, "wav");
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
	 * reinstancie le mediaplayer et joue la musique du jeu.
	 * affiche les metadatas via un autre thread.
	 */
	public void playBackgroundMusic() {
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
		mediaplayer.setVolume(menuController.getModelOption().getVolume());

	}
	
	/**
	 * reinstancie le mediaplayer et joue la musique du gomeover.
	 * affiche les metadatas via un autre thread.
	 */
	public void playGameoverMusic() {
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
		mediaplayer.setVolume(menuController.getModelOption().getVolume());
	}
	
	/**
	 * sauvegarde les données de jeu
	 */
	public void saveGame() {
		if ( gameController != null ) {
			gameController.save();
			System.out.println("Save done");
		}
	}
	
	/**
	 * sauvegarde les données de configuration
	 */
	public void saveOption() {
		menuController.saveOption();
	}
	
	/**
	 * obtient le controller enfant Menu
	 * @return Menu, le controller
	 */
	public Menu getControllerMenu() {
		return menuController;
	}
	
	/**
	 * obtient le controller enfant Game
	 * @return Game, le controller
	 */
	public Game getControllerGame() {
		return gameController;
	}
	
	/**
	 * obtient la vue principale
	 * @return Main, la vue
	 */
	public app.view.Main getView() {
		return mainView;
	}
	
	@Override
	public void updateText() {
		menuController.updateText();
		gameController.updateText();
	}
	
	@Override
	public void exit() {

		if ( gameController != null ) {
			gameController.exit();
			gameController = null;
		}
		
		menuController.exit();
		menuController = null;
		
		mainView.exit();
		mainView = null;
		
		mediaplayer.stop();
		mediaplayer=null;
		
		choose_lang = null;
		musicBackground=null;
		musicGameover=null;
	}
}
