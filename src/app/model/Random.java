package app.model;

import java.util.*;

public class Random {
	
	private static final java.util.Random RANDOM = new java.util.Random();
	
	private static final List<Weather> weathers = Arrays.asList(Weather.storm,Weather.raining,Weather.cloudy,Weather.sunny,Weather.sunny);
	
	public static int nextInt(int i) {
		return RANDOM.nextInt(i);
	}

	public static boolean nextBool() {
		return RANDOM.nextBoolean();
	}
	
	public static String getSpray() {
		return "#"+(nextBool()?"00":"ff")+(nextBool()?"00":"ff")+(nextBool()?"00":"ff");
	}

	public static Weather getWeather() {
		return weathers.get(Random.nextInt(weathers.size()));
	}
	
	public static List<Ingredient> getFridge(){
		List<Ingredient> fridge = Arrays.asList(
				Ingredient.ananas,Ingredient.ananas,
				Ingredient.fraise,Ingredient.fraise,
				Ingredient.peche,Ingredient.peche,
				
				Ingredient.beaf,Ingredient.beaf,Ingredient.beaf,
				Ingredient.fish,Ingredient.fish,Ingredient.fish,
				
				Ingredient.tomate,Ingredient.tomate,Ingredient.tomate,
				Ingredient.carrot,Ingredient.carrot,
				
				Ingredient.cheese,Ingredient.cheese,
				Ingredient.chocolat,Ingredient.chocolat
				);
		Collections.shuffle(fridge);
		return fridge;
	}

}
