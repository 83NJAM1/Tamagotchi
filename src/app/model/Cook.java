package app.model;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Cook {
	
	private boolean ingredient0;
	private boolean ingredient1;
	private boolean ingredient2;
	private boolean ingredient3;
	private boolean ingredient4;
	private boolean ingredient5;
	private boolean ingredient6;
	private boolean ingredient7;
	
	private final int MAX_INGREDIENT_NUMBER=3;
	
	public Cook() {
		reset();
	}
	
	public String getDish(String dish) {
		String s="./res/Cook/Recettes/"+dish+
				(ingredient0?"0":"")+
				(ingredient1?"1":"")+
				(ingredient2?"2":"")+
				(ingredient3?"3":"")+
				(ingredient4?"4":"")+
				(ingredient5?"5":"")+
				(ingredient6?"6":"")+
				(ingredient7?"7":"")+
				".jpg";
		
        if(Files.exists(Paths.get(s)))
        	return s;
        else 
        	return "./res/Cook/Recettes/"+dish+".jpg";
	}
	
	public void reset() {
		ingredient0=false;
		ingredient1=false;
		ingredient2=false;
		ingredient3=false;
		ingredient4=false;
		ingredient5=false;
		ingredient6=false;
		ingredient7=false;
	}
	
	public void toogleIngredient0() {
		if(ingredient0 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient0=!ingredient0;
	}
	
	public void toogleIngredient1() {
		if(ingredient1 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient1=!ingredient1;
	}
	
	public void toogleIngredient2() {
		if(ingredient2 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient2=!ingredient2;
	}
	
	public void toogleIngredient3() {
		if(ingredient3 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient3=!ingredient3;
	}
	
	public void toogleIngredient4() {
		if(ingredient4 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient4=!ingredient4;
	}
	
	public void toogleIngredient5() {
		if(ingredient5 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient5=!ingredient5;
	}
	
	public void toogleIngredient6() {
		if(ingredient6 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient6=!ingredient6;
	}
	
	public void toogleIngredient7() {
		if(ingredient7 || getIngredientNumber()<MAX_INGREDIENT_NUMBER)
			ingredient7=!ingredient7;
	}
	
	public boolean getIngredient0() {
		return ingredient0;
	}
	
	public boolean getIngredient1() {
		return ingredient1;
	}
	
	public boolean getIngredient2() {
		return ingredient2;
	}
	
	public boolean getIngredient3() {
		return ingredient3;
	}
	
	public boolean getIngredient4() {
		return ingredient4;
	}
	
	public boolean getIngredient5() {
		return ingredient5;
	}
	
	public boolean getIngredient6() {
		return ingredient6;
	}
	
	public boolean getIngredient7() {
		return ingredient7;
	}
	
	public int getIngredientNumber() {
		return (ingredient0?1:0)
				+(ingredient1?1:0)
				+(ingredient2?1:0)
				+(ingredient3?1:0)
				+(ingredient4?1:0)
				+(ingredient5?1:0)
				+(ingredient6?1:0)
				+(ingredient7?1:0);
				
					
	}
	
}