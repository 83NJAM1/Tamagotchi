package app.model;

/*
 * Une action est la seule manière de modifier le modèle à partir de l'extérieur de celui-ci.
 * Ce système permet de simplifier le travail du contrôlleur.
 * Il permet aussi d'encapsuler le modèle et donc d'éviter bugs et disfonctionnements.
 * 
 * Dans un projet professionnel, il aurait fallu créer une interface Action et plein d'actions l'implémentant.
 * Cela aurait fait beaucoup de classes. Nous n'avons pas d'IDE permettant d'automatiser leur création à partir d'un tableau de valeurs,
 * 		comme ont les entreprises normalement.
 * 
 * Les actions sont donc ici une enum. Elles s'y pretent bien puisqu'elles n'ont pas besoin d'être instanciées.
 */

public enum Action {
	
	doNothing,
	menu,
	
	play,
	
	changeLanguage,
	quit,
	
	selectPet,
	changePet,
	removePet,
	startCreation,
	
	changeName,
	changeColor,
	changeSpecie,
	endCreation,
		
	fastForward20Minutes,
	fastForward2Hours,
	fastForward1Day,
	
	toogleStats,
	
	goToBathroom,
	goToLivingRoom,
	goToGarden,
	goToKitchen,
	
	
	startCooking,
	stopCooking,
	openFridge,
	closeFridge,
	
	fridgeSlot,
	
	ingredient,
	
	cookPizza,
	cookBurger,
	cookPlate,
	cookSoup,
	
	eat,
	
	wakeUp
	;
	
	/*
	 * Variables de contexte communiquée par l'extérieur avant d'effectuer une action.
	 * Dans un vrai jeu, elles peuvent être plus ou moins complexes.
	 * Dans une app ou un petit jeu comme un tamagoshi, elles se résumeront à un entier et une chaîne.
	 */
	
	private static int contextInt;
	private static String contextString = "BALEK";
	
	
	//Setters de contexte
	
	public static void setContext(String s) {
		contextString = s;
	}
	
	public static void setContext(int n) {
		contextInt=n;
	}
	


	/*
	 * Fonction déterminant si une action a le droit d'être exécutée.
	 * Cela garanti le fonctionnement logique et sans bug du modèle.
	 */
	
	public boolean isAllowed() {
		
		switch(this) {
			case play:return Game.is(Stage.title);
			case startCreation:
			case changeLanguage:
			case quit:return Game.is(Stage.menu);
			case selectPet:
			case changePet:
			case removePet:return Game.is(Stage.menu) && Game.getSelectedPet()!=null;
			case endCreation:return Game.is(Stage.creation);
			case doNothing:return true;
			case menu:return !Game.is(Stage.menu) && !Game.is(Stage.title);
			case changeName:
			case changeSpecie:
			case changeColor:return Game.is(Stage.creation);
			default:
		}

		if(!Game.is(Stage.playing))return false;
		
		Pet pet=Game.getSelectedPet();
				
		switch(this) {
		
		case fastForward20Minutes:
		case fastForward2Hours:
		case fastForward1Day:return Game.isDevMode();
		
		case wakeUp:return pet.isSleeping();
				
		case goToBathroom:return pet.isClose(Room.bathroom);
		case goToLivingRoom:return pet.isClose(Room.livingRoom);
		case goToGarden:return pet.isClose(Room.garden);
		case goToKitchen:return pet.isClose(Room.kitchen);
		
		
		case startCooking:return pet.isIn(Room.kitchen) && !pet.isCooking();
		case stopCooking:return pet.isCooking();
		case openFridge:return pet.isIn(Room.kitchen) && !pet.fridgeIsOpen();
		case closeFridge:return pet.isIn(Room.kitchen) && pet.fridgeIsOpen();
	
		case fridgeSlot:return pet.fridgeIsOpen() && pet.isIn(Room.kitchen) ;
		
		case ingredient:return pet.isCooking();

		case cookPizza:return pet.isCooking() && pet.getRecipe(Dish.pizza)!=null;
		case cookBurger:return pet.isCooking() && pet.getRecipe(Dish.burger)!=null;
		case cookPlate:return pet.isCooking() && pet.getRecipe(Dish.plate)!=null;
		case cookSoup:return pet.isCooking() && pet.getRecipe(Dish.soup)!=null;
		
		case eat:return pet.isDining();
	
		default:return true;
			
		}
	}
	
	
	/*
	 * Fonction d'exécution de l'action.
	 */
	
	public void execute() {
		if(!isAllowed()) return;
				
		Pet pet = Game.getSelectedPet();
		
		if(pet!=null && Game.is(Stage.playing)) pet.setLastActionTime();
		
		switch(this) {
			case quit : System.exit( -1 );
			case menu : if(Game.is(Stage.creation)) Game.removePet(Game.getSelectedPet());
			case play : Game.setStage(Stage.menu);
				break;
			case changeLanguage:
				Language.setCurrentLanguage(Language.values()[contextInt]);
				break;
			case selectPet : 
				Game.setSelectedPet(Game.getPetList().get(contextInt));
				Game.setStage(Stage.playing);
				break;
			case removePet :
				Game.removePet(Game.getPetList().get(contextInt));
				break;
			case changePet :
				Game.setSelectedPet(pet);
				break;
			case startCreation :
				Game.setStage(Stage.creation);
				Game.addPet(new Pet());
				break;
			case changeName:
				pet.setName(contextString);
				pet.getSpecie().setName(contextString);
				break;
			case changeSpecie:
				Specie specie =Specie.values()[contextInt];
				if(pet.getName().equals(pet.getSpecie().getDefaultName()))pet.setName(specie.getName());
				pet.setSpecie(specie);
				Specie.setDefault(specie);
				break;
			case changeColor:
				switch (contextInt) {
					case 0 : 
						pet.setFirstColor(contextString);
						break;
					case 1 : 
						pet.setSecondColor(contextString);
						break;
					case 2 : 
						pet.setThirdColor(contextString);
						break;
				}
				break;
			case endCreation :
				Game.setStage(Stage.playing);
				break;
			case fastForward20Minutes:
				pet.fastForward(20*60*1000);
				break;
		
			case fastForward2Hours:
				pet.fastForward(2*3600*1000);
				break;
			
			case fastForward1Day:
				pet.fastForward(24*3600*1000);
				break;
		
			case wakeUp:
				break;
			
			case toogleStats:
				pet.setStats(!pet.getStats());
				break;
			
			case goToBathroom:
				pet.setRoom(Room.bathroom);
				break;
			
			case goToKitchen:
				pet.setRoom(Room.kitchen);
				break;
			
			case goToLivingRoom:
				pet.setRoom(Room.livingRoom);
				break;
				
			case goToGarden:
				pet.setRoom(Room.garden);
				break;
				
				
				
			case startCooking:
				pet.setMood(Mood.cooking);
				pet.setFridge(true);
				break;
				
			case stopCooking:
				pet.resetMood();
				break;
				
			case openFridge:
				pet.setFridge(true);
				break;
				
			case closeFridge:
				pet.setFridge(false);
				break;
			case fridgeSlot:
				pet.useIngredient(contextInt);
				break;
			case ingredient:
				pet.removeIngredient(contextInt);
				break;
			case cookPizza:
				pet.cook(Dish.pizza);
				break;
			case cookBurger:
				pet.cook(Dish.burger);
				break;
			case cookPlate:
				pet.cook(Dish.plate);
				break;
			case cookSoup:
				pet.cook(Dish.soup);
				break;
	
			case eat:
				pet.setRecipe(null);
				pet.resetMood();
				break;
				
				
			default:
			}
	}
	
	
	//Fonction permattant de récupérer l'action à partir d'une chaîne.
	public static Action get(String name) {
		for(Action action : Action.values())
			if(action.name().equals(name))
				return action;
		return doNothing;
	}
	
	
}
