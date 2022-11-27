package util.view;

import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Main extends AnchorPane{
	
	private VBox left;
	private HBox right;
	
	public Main() {
		left = new VBox();
		left.setStyle("-fx-background-color: grey;");
		right = new HBox();
		right.setStyle("-fx-background-color: pink;");
		getChildren().add(left);
		getChildren().add(right);
		
		AnchorPane.setLeftAnchor(left, 10.);
		AnchorPane.setTopAnchor(left, 10.);
		AnchorPane.setBottomAnchor(left, 10.);
		
		AnchorPane.setRightAnchor(right, 10.);
		AnchorPane.setTopAnchor(right, 10.);
		AnchorPane.setBottomAnchor(right, 10.);
	}
	
	public void update() {
		
		try {
			AnchorPane.setRightAnchor(left, sceneProperty().get().getWidth()*0.35);
			right.setPrefWidth(sceneProperty().get().getWidth()*0.35);
		}
		catch(NullPointerException e) {
			System.out.println(e);
		}

	}
	public VBox getLeft() {
		return left;	
	}
	public HBox getRight() {
		return right;	
	}
}
