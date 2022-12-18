package app.controller;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

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
	
	private app.view.Main view;
	
	private Menu menu;
	private Game game;
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
			menu.setVolume(vnew.doubleValue());
			mediaplayer.setVolume(vnew.doubleValue());
			
			menu.getOption().save();
		}
	};
	
	/**
	 * ActionEvent effectué quand t-on change la langue via la vue Option
	 * déclencher -> v.Option
	 */
	private EventHandler<ActionEvent> choose_lang = new EventHandler<ActionEvent>() {
		public void handle(ActionEvent e) {
			
			Locale loc;
			if ( menu.getView().getOption().getChoosenLang() == 0) {
				loc = Locale.FRENCH;
			}
			else {
				loc = Locale.ENGLISH;
			}
			
			App.language = ResourceBundle.getBundle("language", loc);
			App.languageNumber = NumberFormat.getNumberInstance(loc);
			
			updateText();
			menu.getOption().setLanguage(loc.toString());
			menu.getOption().save();
			
			System.out.println("Locale:" + loc.getDisplayLanguage());
		}
	};
	
	//############################ METHODES #####################################

	public Main(String pathsave) {
		
		// instancie le menu et initialise les options
		menu = new Menu();
		menu.loadOption();
		
		String lastSave = menu.getOption().getLastSave();
		
		// creer une nouvelle partie en écrasant la partie en cours
		if ( pathsave != null && pathsave.equals("new") && lastSave != null ) {
			System.out.println("NEW GAME: " + lastSave);
			// crée une nouvelle partie, la customisation devrai être demandé ici
			game = new Game("cat", "test", lastSave);
		}
		// charge la sauvegarde selectionnée
		else if ( pathsave != null ) {
			System.out.println("LOAD: " + pathsave);
			menu.getOption().setLastSave(pathsave);
			menu.getOption().save();
			game = new Game(pathsave);
		}
		// charge la dernière sauvegarde utilisée
		else if ( lastSave != null ) {
			System.out.println("CONTINUE: " + lastSave);
			game = new Game(lastSave);
		}
		// crée un nouveau fichier de sauvgarde
		else {
			System.out.println("FIRST GAME: save.tmg");
			menu.getOption().setLastSave("save.tmg");
			menu.getOption().save();
			// crée une nouvelle partie, la customisation devrai être demandé ici
			game = new Game("dog", "test", "save.tmg");
		}
		
		// suite instantiation
		view = new app.view.Main( game.getView(), menu.getView() );
		musicBackground = loadMediaFile("bin/res/musiques/DogsAndCats", "mp3");
		musicGameover = loadMediaFile("bin/res/musiques/LowBattery", "mp3");
		
		// suite initialisation
		setBackgroundMusic();
		updateText();
		changeGameDim(menu.getChoosenDim());
		
		// assignation action
		game.gameover.addListener(change_gameover_value);
		
		// construction
		menu.getView().getOption().setLangAction(choose_lang);
		menu.getView().getOption().setVolumeAction(change_volume_value);
	}
	
	/**
	 * Met à jour le texte de tous les élements
	 */
	public void updateText() {
		menu.getView().updateText();
		game.getView().updateText();
		game.getPet().updateText();
	}
	
	/*
	 * Change les dimensions de rendu du jeu
	 */
	public void changeGameDim(int numChoice) {
		game.getView().changeDimension(numChoice);
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
		mediaplayer.setVolume(menu.getVolume());

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
		mediaplayer.setVolume(menu.getVolume());
	}
	
	/*
	 * Sauvegarde les données de jeu
	 */
	public void saveGame() {
		game.save();
		System.out.println("Save done");
	}
	
	/**
	 * Essaie d'aider au mieu le Garbage Collector
	 */
	public void exit() {
		mediaplayer.stop();
		game.exit();
		menu.exit();
		view = null;
		menu = null;
		game = null;
		choose_lang = null;
		mediaplayer=null;
		musicBackground=null;
		musicGameover=null;
	}
	
	public app.view.Main getView() {
		return view;
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Game getGame() {
		return game;
	}
	
}
