package test;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.geometry.Pos;

public class MyLayout extends StackPane{
	
	private BorderPane border;
	private HBox top;
	private HBox bottom;
	private VBox left;
	private VBox right;
	private StackPane center;
	
	public MyLayout() {		
    	border = new BorderPane();
    	top = new HBox(16);
    	bottom = new HBox(16);
    	left = new VBox(16);
    	right = new VBox(16);
    	center = new StackPane();
    	
    	border.setTop(top);
    	border.setBottom(bottom);
    	border.setLeft(left);
    	border.setRight(right);
    	border.setCenter(center);
    	
    	top.setAlignment(Pos.CENTER);
    	bottom.setAlignment(Pos.CENTER);
    	
    	BorderPane.setMargin(top, new Insets(8,0,8,0));
    	BorderPane.setMargin(bottom, new Insets(8,0,8,0));
    	BorderPane.setMargin(left, new Insets(8,8,8,8));
    	BorderPane.setMargin(right, new Insets(8,8,8,8));
    	
    	this.getChildren().add(border);
	}
	
	public void addTop(Node object) {
		top.getChildren().add(object);
	}
	public void addBottom(Node object) {
		bottom.getChildren().add(object);
	}
	public void addLeft(Node object) {
		left.getChildren().add(object);
	}
	public void addRight(Node object) {
		right.getChildren().add(object);
	}
	public void addCenter(Node object) {
		center.getChildren().add(object);
	}
	
	public double getTopHeight() {
		return top.getHeight();
	}
}
