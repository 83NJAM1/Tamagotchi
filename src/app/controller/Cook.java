package app.controller;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class Cook {
	
	private app.model.Cook model;
	private app.view.Cook view;
	
	private boolean done;
	
	public Cook(app.model.Cook model, app.view.Cook view) {
		this.model=model;
		this.view=view;
		
		view.setActionIngerdient0(toogleIngredient0);
		view.setActionIngerdient1(toogleIngredient1);
		view.setActionIngerdient2(toogleIngredient2);
		view.setActionIngerdient3(toogleIngredient3);
		view.setActionIngerdient4(toogleIngredient4);
		view.setActionIngerdient5(toogleIngredient5);
		view.setActionIngerdient6(toogleIngredient6);
		view.setActionIngerdient7(toogleIngredient7);
		view.setActionCook(cook);

	}
	
	private EventHandler<MouseEvent> toogleIngredient0 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient0();
			draw();
		}
	};
	
	private EventHandler<MouseEvent> toogleIngredient1 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient1();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient2 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient2();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient3 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient3();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient4 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient4();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient5 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient5();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient6 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient6();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> toogleIngredient7 = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			model.toogleIngredient7();
			draw();
			}
	};
	
	private EventHandler<MouseEvent> cook = new EventHandler<MouseEvent>() {
		public void handle(MouseEvent e) {
			double x=e.getSceneX();
			double y=e.getSceneY();
			double h=view.getHeight();
			double w=view.getWidth();
		    double AB = Math.sqrt(Math.pow(0,2)+ Math.pow(h/2,2));    
		    double BC = Math.sqrt(Math.pow(w/2-x,2)+ Math.pow(h/2-y,2)); 
		    double AC = Math.sqrt(Math.pow(x-w/2,2)+ Math.pow(y,2));
		    
		    if(Math.acos((BC*BC+AB*AB-AC*AC)/(2*BC*AB))* 180 / Math.PI>120)
		    	makeDish(model.getDish("2"));
		    else if(x>w/2)
		    	makeDish(model.getDish("1"));
		    else
		    	makeDish(model.getDish("0"));
		    done=true;
		    draw();
			}
	};
	
	public void makeDish(String s) {
		view.changeResult(Game.GAMEIMAGEPATH+"recettes/"+s+".jpg");
	}
	
	public void start() {
		done=false;
		model.reset();
		view.updateText();
		draw();
		view.start();
	}
	
	public void draw() {
		view.draw(
				model.getIngredient0(),
				model.getIngredient1(),
				model.getIngredient2(),
				model.getIngredient3(),
				model.getIngredient4(),
				model.getIngredient5(),
				model.getIngredient6(),
				model.getIngredient7(),
				done
				);
	}
	
}