package util.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AnimeList {
	private util.view.AnimeList view;
	
	private ObservableList<Anime> obs_list;
	private TextInputDialog input;
	
	private EventHandler<DialogEvent> validateAdd = new EventHandler<DialogEvent>() {
		
		public void handle(DialogEvent e) {
			String result = input.getResult();
			if(result != null && !result.isEmpty()) {
				addItem(new Anime(result));
			}
		}
	};
	private EventHandler<ActionEvent> actionAdd = new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent e) {
			input = new TextInputDialog();
			input.setHeaderText("Entrer le nom de la nouvelle animation:");
			input.setTitle("Ajouter anime");
			input.setGraphic(null);
			input.show();
			input.setOnCloseRequest(validateAdd);
		}
	};
	private EventHandler<ActionEvent> actionRemove = new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent e) {
			removeItem(-1);
		}
	};
	
	public AnimeList() {
		view = new util.view.AnimeList();
		obs_list = FXCollections.observableArrayList();
		view.setOnAdd(actionAdd);
		view.setOnRemove(actionRemove);
	}
	
	public util.view.AnimeList getView() {
		return view;
	}
	public Anime[] getAnimes(){
		Anime[] l = new Anime[obs_list.size()];
		obs_list.toArray(l);
		return l;
	}
	public void addItem(Anime a) {
		obs_list.add(a);
		view.addItem(a.getView());
	}
	public void addItems(Anime ... animes) {
		util.view.Anime[] views = new util.view.Anime[animes.length];
		for (int i=0; i<views.length; i++) {
			obs_list.add(animes[i]);
			views[i] = animes[i].getView();
		}
		view.addItems(views);
	}
	public void removeItem(int i) {
		if ( obs_list.size() > 0) {
			if (i >= 0 && i < obs_list.size()) {
				obs_list.remove(i);
			}
			else {
				obs_list.remove(obs_list.size()-1);
			}
		}
		view.removeItem(i);
	}
	public void setOnClick(EventHandler<MouseEvent> a) {
		view.setOnClick(a);
	}
}
