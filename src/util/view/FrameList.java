package util.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

public class FrameList extends VBox {
	
	private HBox top;
	private Button buttonAdd;
	private Button buttonRemove;
	private ListView<Frame> list;
	private ObservableList<Frame> obs_list;
	
	public FrameList() {
		top = new HBox(10);
		buttonAdd = new Button("ajouter");
		buttonRemove = new Button("supprimer");
		
		obs_list = FXCollections.observableArrayList();
		list = new ListView<Frame>(obs_list);
		
		list.setCellFactory( param -> new Frame.FrameCell() );
		
		top.getChildren().addAll(buttonAdd, buttonRemove);
		this.getChildren().addAll(top, list);
	}
	
	public Frame[] getAnimes(){
		Frame[] l = new Frame[list.getItems().size()];
		list.getItems().toArray(l);
		return l;
	}
	
	public void addItem(Frame f) {
		list.getItems().add(f);
		list.refresh();
	}
	public void addItems(Frame ... frames) {
		for (Frame f : frames) {
			list.getItems().add(f);
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
		return list.getSelectionModel().getSelectedItem().getId();
	}
	public int getSelectedIndex() {
		return list.getSelectionModel().getSelectedIndex();
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
