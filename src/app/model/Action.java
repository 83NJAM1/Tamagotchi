package app.model;

public enum Action {
	
	doNothing,
	menu,
	
	play,
	
	quit,
	
	selectPet,
	changePet,
	removePet,
	startCreation,
	
	changeName,
	changeColor,
	endCreation,
		
	fastForward20Minutes,
	fastForward2Hours,
	fastForward1Day,
	
	toogleStats,
	wakeUp,
	
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
	
	eat
	;
	private static Pet contextPet;
	private static int contextInt;
	private static String contextColor = "#ffffff";
	private static String contextString = "BALEK";
	
	public static void setContextPet(Pet pet) {contextPet = pet;}
		
	public void execute() {
		if(!isAllowed()) return;
		
		Pet pet=contextPet;
		

		if(pet!=null && Game.is(Stage.playing)) pet.setLastActionTime();
		
		switch(this) {
			case quit : System.exit( -1 );
			case menu : if(Game.is(Stage.creation)) Game.removePet(contextPet);
			case play : Game.setStage(Stage.menu);
				break;
			case selectPet : 
				Game.setStage(Stage.playing);
				break;
			case removePet :
				Game.removePet(pet);
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
				break;
			case changeColor:
				switch (contextInt) {
					case 0 : 
						pet.setFirstColor(contextColor);
						break;
					case 1 : 
						pet.setSecondColor(contextColor);
						break;
					case 2 : 
						pet.setThirdColor(contextColor);
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
	
	public boolean isAllowed() {
		
		switch(this) {
			case play:return Game.is(Stage.title);
			case startCreation:
			case quit:return Game.is(Stage.menu);
			case selectPet:
			case changePet:
			case removePet:return Game.is(Stage.menu) && Game.getSelectedPet()!=null;
			case endCreation:return Game.is(Stage.creation);
			case doNothing:return true;
			case menu:return !Game.is(Stage.menu) && !Game.is(Stage.title);
			case changeName:
			case changeColor:return Game.is(Stage.creation);
			default:
		}

		if(!Game.is(Stage.playing))return false;
		
		Pet pet=contextPet;
				
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
	
		case fridgeSlot:return pet.fridgeIsOpen() && (pet.getIngredientSlot()!=-1 || !pet.isCooking());
		
		case ingredient:return pet.isCooking();

		case cookPizza:return pet.isCooking() && pet.getRecipe(Dish.pizza)!=null;
		case cookBurger:return pet.isCooking() && pet.getRecipe(Dish.burger)!=null;
		case cookPlate:return pet.isCooking() && pet.getRecipe(Dish.plate)!=null;
		case cookSoup:return pet.isCooking() && pet.getRecipe(Dish.soup)!=null;
		
		case eat:return pet.isDining();
	
		default:return true;
			
		}
	}
	
	public void setContext(String s) {
		if(s.charAt(0)=='#' && s.length()==7) contextColor = s;
		else contextString = s;
	}
	
	public void setContext(int n) {
		contextInt=n;
	}
	
	public static Action get(String name) {
		for(Action action : Action.values())
			if(action.name().equals(name))
				return action;
		return doNothing;
	}

	
	
	
}
