package util.controller;

import javafx.geometry.BoundingBox;
import javafx.scene.Node;

public class Frame {
	private util.view.Frame view;
	private util.model.Frame model;
	
	public Frame(BoundingBox src, BoundingBox dest, String path, long time) {
		model = new util.model.Frame(src, dest, path, time);
		view = new util.view.Frame(path, time);
	}
	
	public util.view.Frame getView() {
		return view;
	}
	public util.model.Frame getModel() {
		return model;
	}

}
