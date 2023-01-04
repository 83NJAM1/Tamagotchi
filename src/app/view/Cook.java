package app.view;

import javafx.scene.layout.StackPane;
import javafx.scene.input.MouseEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import app.App;

public class Cook extends StackPane {
	
	private double width;
	private double height;
	
	private ImageView zone;
	private ImageView recipe;
	
	private ImageView ingredient0;
	private ImageView ingredient1;
	private ImageView ingredient2;
	private ImageView ingredient3;
	private ImageView ingredient4;
	private ImageView ingredient5;
	private ImageView ingredient6;
	private ImageView ingredient7;

	private ImageView circle0;
	private ImageView circle1;
	private ImageView circle2;
	private ImageView circle3;
	private ImageView circle4;
	private ImageView circle5;
	private ImageView circle6;
	private ImageView circle7;
	
	private ImageView result;
	private ImageView light;
	
	private Button butShare;
	private Button butEat;


	public Cook() {
		this.toFront();
		
		zone=new ImageView(new Image(Main.IUIMAGEPATH+"cook/fond.png"));
		recipe=new ImageView(new Image(Main.IUIMAGEPATH+"cook/boutons.png"));
		
		ingredient0=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/0.png"));
		ingredient1=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/1.png"));
		ingredient2=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/2.png"));
		ingredient3=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/3.png"));
		ingredient4=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/4.png"));
		ingredient5=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/5.png"));
		ingredient6=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/6.png"));
		ingredient7=new ImageView(new Image(Main.GAMEIMAGEPATH+"ingredients/7.png"));
		
		circle0=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle1=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle2=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle3=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle4=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle5=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle6=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		circle7=new ImageView(new Image(Main.IUIMAGEPATH+"cook/cercle.png"));
		
		result=new ImageView(new Image(Main.GAMEIMAGEPATH+"recettes/0.jpg"));
		light=new ImageView(new Image(Main.IUIMAGEPATH+"cook/glow.png"));
		
		butShare = new Button();
		butEat = new Button();
		
		  this.setAlignment(Pos.TOP_LEFT);

	        this.getChildren().addAll(zone,recipe,ingredient0
	        		,ingredient1,ingredient2,ingredient3,ingredient4,ingredient5
	        		,ingredient6,ingredient7,circle0,circle1,circle2,circle3
	        		,circle4,circle5,circle6,circle7,result
	        		,light,butShare,butEat);

		
		this.setVisible(false);
		

		
		butEat.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        end(0);
		    }
		});
		
		butShare.setOnMouseClicked(new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent event) {
		        end(1);
		    }
		});
		
		
	}
	
	public void setActionIngerdient0(EventHandler<MouseEvent> e) {
		ingredient0.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient1(EventHandler<MouseEvent> e) {
		ingredient1.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient2(EventHandler<MouseEvent> e) {
		ingredient2.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient3(EventHandler<MouseEvent> e) {
		ingredient3.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient4(EventHandler<MouseEvent> e) {
		ingredient4.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient5(EventHandler<MouseEvent> e) {
		ingredient5.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient6(EventHandler<MouseEvent> e) {
		ingredient6.setOnMouseClicked(e);
	}
	
	public void setActionIngerdient7(EventHandler<MouseEvent> e) {
		ingredient7.setOnMouseClicked(e);
	}
	
	public void setActionCook(EventHandler<MouseEvent> e) {
		recipe.setOnMouseClicked(e);
	}
	
	public void setActionEat(EventHandler<ActionEvent> e) {
		butEat.setOnAction(e);
	}
	
	public void setActionShare(EventHandler<ActionEvent> e) {
		butShare.setOnAction(e);
	}
	
	public void start() {
		this.setVisible(true);
	}
	
	public void end(int code) {
		this.setVisible(false);
	}
	
	public void setDimension(double width, double height) {
		this.width=width;
		this.height=height;
		this.setStyle(
				"-fx-pref-width: "+Double.toString(width)+"px;"
				+ "-fx-pref-height: "+Double.toString(height)+"px;"
				+ "-fx-max-width: "+Double.toString(width)+"px;"
				+ "-fx-max-height: "+Double.toString(height)+"px;");
				
		double centerX=width/2.;
		double centerY=height/2.;
		
		placement(zone,100,centerX,centerY);
		zone.setStyle("-fx-opacity: 0.8");
		placement(recipe,12,centerX,centerY);
		

		double ingredientRadius = 7;
		double buttonsRadius = width*15/100.;
		double buttonsRadius2 = buttonsRadius*0.70710678118;
		
		placement(ingredient0,ingredientRadius,centerX,centerY-buttonsRadius);
		placement(ingredient1,ingredientRadius,centerX+buttonsRadius2,centerY-buttonsRadius2);
		placement(ingredient2,ingredientRadius,centerX+buttonsRadius,centerY);
		placement(ingredient3,ingredientRadius,centerX+buttonsRadius2,centerY+buttonsRadius2);
		placement(ingredient4,ingredientRadius,centerX,centerY+buttonsRadius);
		placement(ingredient5,ingredientRadius,centerX-buttonsRadius2,centerY+buttonsRadius2);
		placement(ingredient6,ingredientRadius,centerX-buttonsRadius,centerY);
		placement(ingredient7,ingredientRadius,centerX-buttonsRadius2,centerY-buttonsRadius2);
		
		double circleRadius = ingredientRadius*1.4;
		
		placement(circle0,circleRadius,centerX,centerY-buttonsRadius);
		circle0.setStyle("-fx-rotate: 30");
		placement(circle1,circleRadius,centerX+buttonsRadius2,centerY-buttonsRadius2);
		circle1.setStyle("-fx-rotate: 70");
		placement(circle2,circleRadius,centerX+buttonsRadius,centerY);
		circle2.setStyle("-fx-rotate: 120");
		placement(circle3,circleRadius,centerX+buttonsRadius2,centerY+buttonsRadius2);
		circle3.setStyle("-fx-rotate: 40");
		placement(circle4,circleRadius,centerX,centerY+buttonsRadius);
		circle4.setStyle("-fx-rotate: 0");
		placement(circle5,circleRadius,centerX-buttonsRadius2,centerY+buttonsRadius2);
		circle5.setStyle("-fx-rotate: 80");
		placement(circle6,circleRadius,centerX-buttonsRadius,centerY);
		circle6.setStyle("-fx-rotate: 190");
		placement(circle7,circleRadius,centerX-buttonsRadius2,centerY-buttonsRadius2);
		circle7.setStyle("-fx-rotate: 230");
		
		placement(light,100,centerX,centerY);
		
		placement(result,40,centerX,centerY);
		result.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.8), 50, 0, 0, 0);");
		
		butShare.setTranslateX(centerX-width/2.5);
		butShare.setTranslateY(centerY);
		butShare.toFront();
		
		butEat.setTranslateX(centerX+width/4);
		butEat.setTranslateY(centerY);
		butEat.toFront();
	
	}
	
	private void placement(ImageView image, double dim, double left, double top) {
		image.setPreserveRatio(true);
		image.setFitWidth(dim*width/100);
		//image.setFitHeight(dim*width/100);
		if(dim!=100) {
			image.setTranslateX(left-dim*width/200);
			image.setTranslateY(top-dim*width/200);
		}
		image.toFront();
        
	}

	public void draw(
			boolean ingredient0,
			boolean ingredient1,
			boolean ingredient2,
			boolean ingredient3,
			boolean ingredient4,
			boolean ingredient5,
			boolean ingredient6,
			boolean ingredient7,
			boolean done) {
		
		circle0.setVisible(ingredient0);
		circle1.setVisible(ingredient1);
		circle2.setVisible(ingredient2);
		circle3.setVisible(ingredient3);
		circle4.setVisible(ingredient4);
		circle5.setVisible(ingredient5);
		circle6.setVisible(ingredient6);
		circle7.setVisible(ingredient7);
		
		result.setVisible(done);
		light.setVisible(done);
		butShare.setVisible(done);
		butEat.setVisible(done);
	}
	
	public void changeResult(String imagePath, String altPath) {
		Image r;
		
		try {
			r = (new Image(imagePath));
			result.setImage(r);
		} catch (Exception e ) {
			r = (new Image(altPath));
			result.setImage(r);
			System.err.println(e);
		}
	}
	
	public void updateText() {
		butShare.setText(App.getString("button-share"));
		butEat.setText(App.getString("button-eat"));
	}
}