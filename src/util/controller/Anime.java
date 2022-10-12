package util.controller;

import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.BoundingBox;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class Anime {
	private util.model.Anime model;
	private util.view.Anime view;
	
	private FrameList frames;
	private Alert input;
	
	private VBox rootdialog;
	private TextField path;
	private Label lpath; 
	private Spinner<Number> x;
	private Label lx;
	private Spinner<Number> y;
	private Label ly;
	private Spinner<Number> w;
	private Label lw;
	private Spinner<Number> h;
	private Label lh;
	private Spinner<Number> time;
	private Label ltime;
	
	private EventHandler<DialogEvent> validateAdd = new EventHandler<DialogEvent>() {
		
		public void handle(DialogEvent e) {
			ButtonType result = input.getResult();
			if(result != null && result == ButtonType.OK) {
				BoundingBox boxS = new BoundingBox(x.getValue().intValue(), y.getValue().intValue(),
												  w.getValue().intValue(), h.getValue().intValue());
				BoundingBox boxD = new BoundingBox( 0,  0,
						  						   64, 64);
				addFrame(new Frame(boxS, boxD, path.getText(), time.getValue().intValue()));
			}
		}
	};
	private EventHandler<ActionEvent> actionAdd = new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent e) {
			input = new Alert(AlertType.INFORMATION);
			input.setHeaderText("Entrer les valeurs de la nouvelle frame:");
			input.setTitle("Ajouter frame");
			input.setGraphic(rootdialog);
			input.show();
			input.setOnCloseRequest(validateAdd);
		}
	};
	
	private EventHandler<ActionEvent> actionRemove = new EventHandler<ActionEvent>() {
		
		public void handle(ActionEvent e) {
			removeFrame(frames.getSelectedIndex());
		}
	};
	
	public Anime(String id) {
		model = new util.model.Anime(id);
		view = new util.view.Anime(id);
		frames = new FrameList();
		//addFrame(new Frame(null, null, "./res/PET_TAMAGOCHI.png", 0));
		frames.setOnAdd(actionAdd);
		frames.setOnRemove(actionRemove);
		
		rootdialog = new VBox(10);
		path = new TextField();
		x = new Spinner<Number>(0, 512, 0);
		y = new Spinner<Number>(0, 512, 0);
		h = new Spinner<Number>(0, 512, 0);
		w = new Spinner<Number>(0, 512, 0);
		time = new Spinner<Number>(0, 512, 0);
		lpath = new Label("Path:");
		lx = new Label("X:");
		ly = new Label("Y:");
		lh = new Label("H:");
		lw = new Label("W:");
		ltime = new Label("Time:");
		rootdialog.getChildren().addAll(lpath, path, lx, x, ly, y, lh, h, lw, w, ltime, time);
	}
	
	public util.view.Anime getView() {
		return view;
	}
	
	public util.model.Anime getModel(){
		return model;
	}
	
	public String getNameId() {
		return model.getId();
	}
	
	public void addFrame(Frame f) {
		frames.addItem(f);
		model.addFrame(f.getModel());
		view.addFrame(f.getView());
	}
	public void removeFrame(int i) {
		frames.removeItem(i);
		model.removeFrame(i);
		view.removeFrame(i);
	}
	
	public void addFrame(Frame ... f) {
		frames.addItems(f);
		for ( Frame frame : f) {
			model.addFrame(frame.getModel());
			view.addFrame(frame.getView());
		}
	}
	
	public FrameList getFrames() {
		return frames;
	}
	
	public Frame getCurrentFrame() {
		return frames.getFrame(model.getCurrentFrame()); 
	}
	
	public Frame run(long time) {
		model.run();
		return frames.getFrame(model.getCurrentFrame());
	}
	
}
