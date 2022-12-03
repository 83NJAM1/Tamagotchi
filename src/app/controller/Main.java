package app.controller;

import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.media.Media;
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
	private app.model.Save save;
	
	private Menu menu;
	private Game game;
	private Media musicBackground;
	private Media musicGameover;
	private MediaPlayer mediaplayer;
	
	private BooleanProperty gameover;
	
	//######################### EVENT-ACTION ####################################
	
	/**
	 * Action effectué quand t-on perd la partie
	 * déclencher -> this -> v.Menu -< v.Option
	 */
	ChangeListener<Boolean> change_gameover_value = new ChangeListener<Boolean>() {
		public void changed(ObservableValue<? extends Boolean> obs, Boolean vold, Boolean vnew) {
			setGameoverMusic();
		}
	};
	
	/**
	 * Action effectué quand t-on modifie le volume via la vue Option
	 * déclencher -> this -> v.Menu -< v.Option
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
	 * déclencher -> this -> v.Menu -< v.Option
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
		if ( pathsave != null && pathsave.equals("new") ) {
			if ( lastSave != null ) {
				System.out.println("new game: " + lastSave);
				save = new app.model.Save(lastSave);
			}
			else {
				System.out.println("new game: save.tmg");
				menu.getOption().setLastSave("res/save.tmg");
				menu.getOption().save();
				save = new app.model.Save("res/save.tmg");
			}
			// crée une nouvelle partie, la customisation devrai être demandé ici
			game = new Game("cat", "test");
		}
		// charge la sauvegarde selectionnée
		else if ( pathsave != null ) {
			System.out.println("load: " + pathsave);
			menu.getOption().setLastSave("res/"+pathsave);
			menu.getOption().save();
			save = new app.model.Save("res/"+pathsave);
			save.load("res/"+pathsave);
			System.out.println("loading data:\n" + save.toString().indent(4));
			game = new Game(save.getPetType(), save.getRoomId());
			initGameWithSaveData();
		}
		// charge la dernière sauvegarde utilisée
		else if ( lastSave != null ) {
			System.out.println("continue: " + lastSave);
			save = new app.model.Save(lastSave);
			save.load(lastSave);
			System.out.println("loading data:\n" + save.toString().indent(4));
			game = new Game(save.getPetType(), save.getRoomId());
			initGameWithSaveData();
		}
		// crée un nouveau fichier de sauvgarde
		else {
			System.out.println("new game: save.tmg");
			menu.getOption().setLastSave("res/save.tmg");
			menu.getOption().save();
			save = new app.model.Save("res/save.tmg");
			// crée une nouvelle partie, la customisation devrai être demandé ici
			game = new Game("dog", "test");
		}
		save.setGameInstance(game.getModel());
		
		// suite instantiation
		view = new app.view.Main( game.getView(), menu.getView() );
		gameover = new SimpleBooleanProperty(this, "gameover", false);
		musicBackground = new Media(Paths.get("bin/res/musiques/DogsAndCats.mp3").toUri().toString());
		musicGameover = new Media(Paths.get("bin/res/musiques/LowBattery.mp3").toUri().toString());
		
		// suite initialisation
		setBackgroundMusic();
		updateText();
		changeGameDim(menu.getChoosenDim());
		
		// assignation action
		gameover.addListener(change_gameover_value);
		gameover.bind(game.gameover);
		
		// construction
		menu.getView().getOption().setLangAction(choose_lang);
		menu.getView().getOption().setVolumeAction(change_volume_value);
	}
	
	public void updateText() {
		menu.getView().updateText();
		game.getView().updateText();
		game.getPet().updateText();
	}
	
	public void changeGameDim(int numChoice) {
		game.getView().changeDimension(numChoice);
	}
	
	public void setBackgroundMusic() {
		mediaplayer = new MediaPlayer(musicBackground);
		mediaplayer.setStopTime(Duration.seconds(113));
		mediaplayer.setCycleCount(MediaPlayer.INDEFINITE);
		mediaplayer.play();
		mediaplayer.setVolume(menu.getVolume());
		System.out.println("Play: " + musicBackground.getSource());
	}
	
	public void setGameoverMusic() {
		mediaplayer.stop();
		mediaplayer = new MediaPlayer(musicGameover);
		mediaplayer.play();
		mediaplayer.setVolume(menu.getVolume());
		System.out.println("Play: " + musicGameover.getSource());
	}
	
	public void initGameWithSaveData() {
		game.getPet().getHunger().setValue(save.getStat("hunger"));
		game.getPet().getThirst().setValue(save.getStat("thirst"));
		game.getPet().getWeight().setValue(save.getStat("weight"));
		game.getPet().getHygiene().setValue(save.getStat("hygiene"));
		game.getPet().getMoral().setValue(save.getStat("moral"));
	}
	
	/*
	 * ecrit les données du jeu dans un fichier
	 */
	public void saveGame() {
		save.save();
		System.out.println("Save done");
	}
	
	/**
	 * essaie d'aider au mieu le Garbage Collector
	 */
	public void exit() {
		mediaplayer.stop();
		game.exit();
		menu.exit();
		view = null;
		save = null;
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
