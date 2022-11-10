package app.model;

import app.model.states.*;

/*
 * Moteur textuel du modèle
 * 
 * La vue n'a pas besoin de moteur de texte.
 * Elle doit uniquement afficher le texte que lui a envoyé le contrôleur (à partir du modèle, des ressources ou du contrôleur lui-même)
 * 
 * Cette forme en Enum est aussi primitive qu'une forme en Interface + méthodes ou même dictionnaire interne.
 * Ces solutions sont simples, mais elles ne sont ni professionnelles ni résistantes.
 * 
 * La vraie solution pour l'internationalisation d'une app, c'est de faire appel à des modules extérieurs spécialisés.
 * Ceux-ci ont un set de classes équivalent à mon enum "Language" plus haut, ou à une Interface et des classes l'implémentant.
 * 
 * La différence sera que les traductions seront stockées dans la partie Ressources, dans un format permettant aux modules d'y accéder.
 * 
 * Cela permet de ne pas compiler des choses inutiles (les chaines) et aussi d'éditer facilement les phrases.
 * 
 * 
 * Deux problèmes majeurs :
 *     - Pas mal de boulot comparé aux méthodes cheap.
 *     - Encore plus de boulot pour pouvoir faire des phrases avec contexte ("Donner un os à %petname" => "Give %petname a bone")
 * 
 * Si certains estiment que ça vaut la peine de passer du temps dessus pourquoi pas.
 * 
 */


public enum Text {
	missing_text,
	
	doNothing,
	menu("Menu","Menu","Chong ching chong"),
	title("Ecran de Démarrage","Title Screen","Chong ching chong"),
	creation("Création du Familier","Pet Creation","Chong ching chong"),
	
	play("Jouer","Play","Chong ching chong"),
	quit("Quitter","Leave","Chong ching chong"),
	select_pet,
	remove_pet("Abandonner","Abandon","Chong ching chong"),
	start_creation("Nouveau compagnon","New pet","Chong ching chong"),
	end_creation("Ok","Start","Chong ching chong"),
	
	cat("Chat","Cat","Chong ching chong"),
	dog("Chien","Dog","Chong ching chong"),
	rabbit("Lapin","Rabbit","Chong ching chong"),
	robot("Robot","Robot","Chong ching chong"),
	
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

	sunny("Beau Temps","Sunny","Chong ching chong"),
	cloudy("Temps Couvert","Cloudy","Chong ching chong"),
	raining("Pluie","Raining","Chong ching chong"),
	storm("Tempête","Storm","Chong ching chong"),

	ananas("Ananas","pineapple","Chong ching chong"),
	fraise("Fraise","Strawberry","Chong ching chong"),
	peche("Pêche","Peach","Chong ching chong"),
	beaf("Viande","Meat","Chong ching chong"),
	fish("Poisson","Fish","Chong ching chong"),
	tomate("Tomate","Tomato","Chong ching chong"),
	carrot("Carotte","Carrot","Chong ching chong"),
	brocoli("Brocoli","Broccoli","Chong ching chong"),
	cheese("Fromage","Cheese","Chong ching chong"),
	chocolat("Chocolat","Chocolate","Chong ching chong"),
	mouse("Souris","Mouse","Chong ching chong"),

	start_cooking("Cuisiner","Cook","Chong ching chong"),
	stop_cooking("Arrêter de Cuisiner","Stop Cooking","Chong ching chong"),
	open_fridge("Ouvrir le Frigo","Open Fridge","Chong ching chong"),
	close_fridge("Fermer le Frigo","Close Fridge","Chong ching chong"),
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
			case menu:return menu.call();
			case startCreation:return start_creation.call();
			case endCreation:return end_creation.call();
		    
			case removePet:return remove_pet.call();
			
			case toogleStats:return (pet.getStats()?hide_stats:show_stats).call();
			case wakeUp:return wake_up.call();
			case goToBathroom:return bathroom.call();
			case goToLivingRoom:return living_room.call();
			case goToGarden:return garden.call();
			case goToKitchen:return kitchen.call();

			case startCooking:return start_cooking.call();
			case stopCooking:return stop_cooking.call();
			case openFridge:return open_fridge.call();
			case closeFridge:return close_fridge.call();
			
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
			case ananas:return ananas.call();
			case fraise:return fraise.call();
			case peche:return peche.call();
			case beaf:return beaf.call();
			case fish:return fish.call();
			case tomate:return tomate.call();
			case carrot:return carrot.call();
			case brocoli:return brocoli.call();
			case cheese:return cheese.call();
			case chocolat:return chocolat.call();
			case mouse:return mouse.call();
			case rabbit:return rabbit.call();
			default: return ingredient.name();
		}
		
	}
	

	

	public static String call(Room room) {
		if(room==null) return missing_text.call();
		
		switch(room) {
			case bathroom:return bathroom.call();
			case livingRoom:return living_room.call();
			case garden:return garden.call();
			case kitchen:return kitchen.call();
			default:return room.name();
		}
		
	}
	


	public static String call(Recipe recipe) {
		if(recipe==null) return missing_text.call();
		
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
	



	public static String call(Specie specie) {
		if(specie==null) return missing_text.call();
		
		switch(specie) {
			case cat:return cat.call();
			case dog:return dog.call();
			case robot:return robot.call();
			case rabbit:return rabbit.call();
			default:return specie.name();
		}
		
	}
	




	public static String call(Weather weather) {
		if(weather==null) return missing_text.call();
		
		switch(weather) {
			case sunny:return sunny.call();
			case cloudy:return cloudy.call();
			case raining:return raining.call();
			case storm:return storm.call();
			default:return weather.name();
		}
		
	}
	
	public static String call(Stage stage) {
		if(stage==null) return missing_text.call();
		
		switch(stage) {
			case title:return title.call();
			case menu:return menu.call();
			case creation:return creation.call();
			default:return stage.name();
		}
		
	}
	


	
	public static String call(State state) {
		if(state instanceof Clean) return clean.call();
		if(state instanceof Energy) return energy.call();
		if(state instanceof Health) return health.call();
		if(state instanceof Hunger) return hunger.call();
		if(state instanceof Moral) return moral.call();
		if(state instanceof Sick) return sick.call();
		if(state instanceof Thirst) return thirst.call();
		if(state instanceof Weight) return weight.call();
		return wet.call();
		}


	
	
}
