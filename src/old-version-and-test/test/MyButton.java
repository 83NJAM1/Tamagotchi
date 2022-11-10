package test;

import java.util.Vector;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class MyButton extends Button {
	
	EventHandler<ActionEvent> action;
	Vector<Node> target;
	
	public MyButton(String name) {
		super(name);
		
		target = new Vector<Node>();
	}
	
	public void setActionFor(Node ... objects) {
		for ( Node object : objects ) {
			target.add(object);
		} 
	}
}
