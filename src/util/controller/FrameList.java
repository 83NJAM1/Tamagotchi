package util.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.DialogEvent;
//import util.view.Frame;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;

public class FrameList {
	private util.view.FrameList view;
	
	private ObservableList<Frame> obs_list;
	
	public FrameList() {
		view = new util.view.FrameList();
		obs_list = FXCollections.observableArrayList();
	}
	
	public util.view.FrameList getView() {
		return view;
	}
	
	public Frame[] getAnimes(){
		Frame[] l = new Frame[obs_list.size()];
		obs_list.toArray(l);
		return l;
	}
	public Frame getFrame(int current) {
		if ( !obs_list.isEmpty() ) {
			return obs_list.get(current);
		}
		return null;
	}
	public void addItem(Frame f) {
		obs_list.add(f);
		view.addItem(f.getView());
	}
	public void addItems(Frame ... frames) {
		util.view.Frame[] views = new util.view.Frame[frames.length];
		for (int i=0; i<views.length; i++) {
			obs_list.add(frames[i]);
			views[i] = frames[i].getView();
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
	public void setOnAdd(EventHandler<ActionEvent> actionAdd) {
		view.setOnAdd(actionAdd);
	}
	public void setOnRemove(EventHandler<ActionEvent> actionRemove) {
		view.setOnRemove(actionRemove);
	}
	public int getSelectedIndex() {
		return view.getSelectedIndex();
	}
}
