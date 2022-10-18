package app.model;
import java.util.*;

public enum Recipe {
	ananas_carrot_pizza,
	beaf_fish_fish_burger;
	
	
	
public static Recipe getRecipe(Ingredient i1, Ingredient i2, Ingredient i3, Dish dish) {
	
	List<String> ingredients = new ArrayList<String>();
	if(i1!=null) ingredients.add(i1.name());
	if(i2!=null) ingredients.add(i2.name());
	if(i3!=null) ingredients.add(i3.name());
	
	Collections.sort(ingredients);
	
	String search = "";
	for(String s : ingredients) search+=s+"_";
	search+=dish.name();

	for(Recipe recipe : Recipe.values())
		if(recipe.name().equals(search))
			return recipe;
	
	return null;
	
}
	
	
}
