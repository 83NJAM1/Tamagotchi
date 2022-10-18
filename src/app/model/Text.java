package app.model;

public enum Text {
	missing_text,
	
	doNothing,
	menu,
	
	play("Jouer","Play","Chong ching chong"),
	quit("Quitter","Close Game","Chong ching chong"),
	select_pet,
	remove_pet("Abandonner $n","Abandon $n","Chong ching chong"),
	start_creation,
	end_creation("Ok","Start","Chong ching chong"),
	
	health("Santé","Health","Chong ching chong"),
	energy("Energie","Energy","Chong ching chong"),
	moral("Bonheur","Happiness","Chong ching chong"),
	hunger("Faim","Hunger","Chong ching chong"),
	thirst("Soif","Thirst","Chong ching chong"),
	clean("Propreté","cleanliness","Chong ching chong"),
	weight("Poids","Weight","Chong ching chong"),
	wet("Mouillure","Wetness","Chong ching chong"),
	sick("Maladie","Sickness","Chong ching chong"),
	
	dog_basename("Mr. Waf","Mr. Woof","Chong ching chong"),
	cat_basename("Cupcake","Cupcake","Chong ching chong"),
	rabbit_basename("Fluffy","Fluffy","Chong ching chong"),
	robot_basename("Terminator Jr.","Terminator Jr.","Chong ching chong"),
	
	wake_up("Réveiller","Wake Up","Chong ching chong"),
	is_sleeping("$n dort...","$n is sleeping...","Chong ching chong"),
	show_stats("Voir les stats","Show stats","Chong ching chong"),
	hide_stats("Cacher les stats","Hide stats","Chong ching chong"),
	
	living_room("Salon","Living-room","Chong ching chong"),
	garden("Jardin","Garden","Chong ching chong"),
	bathroom("Salle de Bain","Bathroom","Chong ching chong"),
	kitchen("Cuisine","Kitchen","Chong ching chong"),

	start_cooking("Cuisiner","Cook","Chong ching chong"),
	fridge_slot("Utiliser un élément du frigo","Use fridge item","Chong ching chong"),
	ingredient("Retirer un ingrédient","Remove ingredient","Chong ching chong"),
	cook_pizza("Cuisiner une Pizza","Make a Pizza","Chong ching chong"),
	cook_burger("Cuisiner un Burger","Make a Burger","Chong ching chong"),
	cook_plate("Cuisiner un Plat","Make a Plate","Chong ching chong"),
	cook_soup("Cuisiner une Soupe","Make a Soup","Chong ching chong"),
	eat("Déguster","Eat","Chong ching chong"),

	
	;
	
	
	private String[] translations;
		
	private Text(String...s) {
		
		translations=new String[Language.values().length];
		for(int i=0;i<Language.values().length;i++) translations[i]=s.length<=i?"":s[i];

	}
	
	
	public String call() {
		
		String text = translations[Language.getCurrentLanguage().ordinal()];
		Pet pet = Game.getSelectedPet();
		
		if(pet!=null) text.replace("$n",pet.getName());
		
		return text;
	}
	
	
	public static String call(Action action) {
		if(action==null) return missing_text.call();

		Pet pet = Game.getSelectedPet();
		
		switch(action) {
			case play:return play.call();
			case quit:return quit.call();
			case endCreation:return end_creation.call();
		
			case toogleStats:return (pet.getStats()?hide_stats:show_stats).call();
			case wakeUp:return wake_up.call();
			case goToBathroom:return bathroom.call();
			case goToLivingRoom:return living_room.call();
			case goToGarden:return garden.call();
			case goToKitchen:return kitchen.call();

			case startCooking:return start_cooking.call();

			case fridgeSlot:return fridge_slot.call();
			case ingredient:return ingredient.call();
			
			case cookPizza:return cook_pizza.call();
			case cookBurger:return cook_burger.call();
			case cookPlate:return cook_plate.call();
			case cookSoup:return cook_soup.call();
			
			case eat:return eat.call();
		
			default:return action.name();
		}
		
	}
	

	public static String call(Ingredient ingredient) {
		if(ingredient==null) return missing_text.call();
		//Pet pet = Game.getSelectedPet();

		switch(ingredient) {
			default: return ingredient.name();
		}
		
	}
	

	

	public static String call(Recipe recipe) {
		if(recipe==null) return missing_text.call();

		//Pet pet = Game.getSelectedPet();
		
		switch(recipe) {
			default:return recipe.name();
		}
		
	}
	


	public static String call(Dish dish) {
		if(dish==null) return missing_text.call();

		//Pet pet = Game.getSelectedPet();
		
		switch(dish) {
			default:return dish.name();
		}
		
	}
	


	
	
}
