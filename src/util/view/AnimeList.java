package util.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.control.ListCell;

public class AnimeList extends VBox {

	private HBox top;
	private Button buttonAdd;
	private Button buttonRemove;
	private ListView<Anime> list;
	private ObservableList<Anime> obs_list;
	
	public AnimeList() {
		top = new HBox(10);
		buttonAdd = new Button("ajouter");
		buttonRemove = new Button("supprimer");
		
		obs_list = FXCollections.observableArrayList();
		list = new ListView<Anime>(obs_list);
		
		Callback<ListView<Anime>, ListCell<Anime>> cb = new Callback<ListView<Anime>, ListCell<Anime>>() {
			public ListCell<Anime> call(ListView<Anime> value){
				return new Anime.AnimeCell();
			}
		}; // equivalent à ( cb -> new Anime.AnimeCell() ) 
		list.setCellFactory( cb );
		
		top.getChildren().addAll(buttonAdd, buttonRemove);
		this.getChildren().addAll(top, list);
	}

	public Anime[] getAnimes(){
		Anime[] l = new Anime[list.getItems().size()];
		list.getItems().toArray(l);
		return l;
	}
	
	public void addItem(Anime a) {
		list.getItems().add(a);
		list.refresh();
	}
	public void addItems(Anime ... animes) {
		for (Anime a : animes) {
			list.getItems().add(a);
		}
	}
	public void removeItem(int i) {
		if ( list.getItems().size() > 0) {
			if (i >= 0 && i < list.getItems().size()) {
				list.getItems().remove(i);
			}
			else if(i<0) {
				removeItem();
			}
			else {
				list.getItems().remove(obs_list.size()-1);
			}
			list.refresh();
		}
	}
	public void removeItem() {
		list.getItems().remove(list.getSelectionModel().getSelectedItem());
	}
	public String getSelectedItem() {
		return list.getSelectionModel().getSelectedItem().getNameId();
	}
	public void setOnAdd(EventHandler<ActionEvent> a) {
		buttonAdd.setOnAction(a);
	}
	public void setOnRemove(EventHandler<ActionEvent> a) {
		buttonRemove.setOnAction(a);
	}
	public void setOnClick(EventHandler<MouseEvent> e) {
		list.setOnMouseClicked(e);
	}
}
