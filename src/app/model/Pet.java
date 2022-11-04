package app.model;
import app.model.states.Clean;
import app.model.states.Energy;
import app.model.states.Health;
import app.model.states.Hunger;
import app.model.states.Moral;
import app.model.states.Sick;
import app.model.states.State;
import app.model.states.Thirst;
import app.model.states.Weight;
import app.model.states.Wet;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Pet {
	
	//Espèce
	protected Specie specie;
	
	//nom
	protected String name;
	
	//motif
	private Pattern pattern;
	
	//Couleurs
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
	//Coloration artificielle
	private String sprayColor;
	private boolean sprayed;
	
	//Date de création
	private long creationTime;
	
	//Dernier timestamp
	protected long time;
	
	//temps depuis la dernière action extérieure
	protected long lastActionTime;
	
	//cycle actuel, purement informatif
	protected int cycle;
	//Un nouveau cycle arrive lorsqu'il n'y a pas eu d'action utilisateur depuis cycleTime, cela change la météo et le contenu du frigo
	protected final static double cycleTime = 2*3600*1000;//2 heures
	
	//Salle actuelle
	protected Room room;
	
	//Temps actuel
	protected Weather weather;
	
	//Etats
	protected List<State> states = new ArrayList<State>();
	
	//Etat d'esprit primordial
	protected Mood mood;
	//Etats d'esprit passifs
	protected List<Mood> passiveMoods = new ArrayList<Mood>();
	//Etats d'esprit causés par une interaction utilisateur
	protected List<Mood> activeMoods = new ArrayList<Mood>();
	
	//Ingrédients sur le plan de travail
	protected final List<Ingredient> ingredients = Arrays.asList(null,null,null);
	//Ingrédients dans le frigo
	protected List<Ingredient> fridge;
	//Booleen : le frigo est ouvert/fermé
	protected boolean fridgeDoor;
	
	//Plat cuisiné récement
	protected Recipe recipe;
	
	//Booleen : les stats sont visibles/cachées
	protected boolean stats;
	
	
	
	//Création du pet
	public Pet() {
		creationTime=System.currentTimeMillis();
		time=creationTime;
		states = Arrays.asList(
				new Health(),
				new Energy(),
				new Moral(),
				new Hunger(),
				new Thirst(),
				new Clean(),
				new Weight(),
				new Wet(),
				new Sick()
				);
		
		//Espèce par défaut
		setSpecie(Specie.getDefault());
		
		//Nom par défaut
		setName(specie.getName());
		
		//Motif par défaut
		setPattern(specie.getPattern());
		
		//Couleurs par défaut
		setFirstColor(specie.getFirstColor());
		setSecondColor(specie.getSecondColor());
		setThirdColor(specie.getThirdColor());
		
		//Salle par défaut
		setRoom(Room.livingRoom);
		
		
		newCycle();
	}
	
	//Refresh
	public void refresh() {
		
		long time = System.currentTimeMillis();
		int timePassed = (int)(time-this.time);
		int count = Math.min(Math.max(1, timePassed/100),10);
		int interval = timePassed/count;
		
		//Fragmentation du temps écoulé en petites parties pour éviter les comportements incohérents causés par beaucoup de temps qui passe d'un coup
		for(int i=0;i<count;i++) pass(interval);
		

	}
	
	public void pass(int interval) {
			
			time+=interval;
			
			//Test pour savoir si un nouveau cycle doit être généré
			if(getAloneTime()>cycleTime) newCycle();
			
			//Réinitialisation des états d'esprit passifs
			passiveMoods = new ArrayList<Mood>();
			
			//Calcul des effets du temps sur les états
			for(State state : states) {
				state.pass(this, interval);
			}
		
	}
	
	//Nouveau cycle
	public void newCycle() {
		cycle++;
		lastActionTime = time;
		//Changement du temps
		weather = Random.getWeather();
		//Changement de la couleur de la teinture
		sprayColor = Random.getSpray();
		//Changement du contenu du frigo
		fridge = Random.getFridge();
		
	}
	
	//Fast forward pour devMode
	public void fastForward(int n) {
		if(n<0)return;
		time-=n;
		lastActionTime-=n;
		creationTime-=n;
	}	
	
	
	//GETTERS---------------------------------------------------------------------------------------------

	
	public long getAge() {
		return time - creationTime;
	}
	
	public List<State> getStateList() {
		return states;
	}

	public State health() {
		return states.get(0);
	}
	
	public State energy() {
		return states.get(1);
	}
	
	public State moral() {
		return states.get(2);
	}
	
	public State hunger() {
		return states.get(3);
	}
	
	public State thirst() {
		return states.get(4);
	}
	
	public State clean() {
		return states.get(5);
	}
	
	public State weight() {
		return states.get(6);
	}
	
	public State wet() {
		return states.get(7);
	}
	
	public State sick() {
		return states.get(8);
	}
	
	public int getCycle() {
		return cycle;
	}
	
	public Specie getSpecie() {
		return specie;
	}
	
	public boolean is(Specie specie) {
		return specie==this.specie;
	}
	
	public String getName() {
		return name;
	}
	
	public Pattern getPattern() {
		return pattern;
	}
	
	public String getFirstColor() {
		return firstColor;
	}
	
	public String getSecondColor() {
		return secondColor;
	}
	
	public String getThirdColor() {
		return thirdColor;
	}
	
	public int getAloneTime() {
		return (int)(time-lastActionTime);
	}
	
	public String getSprayColor() {
		return sprayColor;
	}
	
	public List<Ingredient> getFridge(){
		return fridge;
	}
	
	public Ingredient getFridgeSlot(int slot){
		return fridge.get(slot);
	}
	
	public List<Ingredient> getIngredients(){
		return ingredients;
	}
	
	public Ingredient getIngredient(int n){
		return ingredients.get(n);
	}
	
	public boolean isSprayed() {
		return sprayed;
	}
	
	public int getIngredientSlot() {
		int n=0;
		for(Ingredient ingredient : ingredients) {
			if(ingredient==null)
				return n;
			n++;
		}
		return -1;
	}
	
	public Recipe getRecipe(Dish dish) {
		return Recipe.getRecipe(ingredients.get(0),ingredients.get(1),ingredients.get(2),dish);
	}
	
	public boolean fridgeIsOpen() {
		return fridgeDoor;
	}
	
	public Recipe getRecipe() {
		return this.recipe;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	public Room getRoom() {
		return room;
	}
	
	public boolean isIn(Room r) {
		return room==r;
	}
	
	public boolean isClose(Room r) {
		return room==null?false:room.isClose(r);
	}
	
	public Mood getMood() {
		return this.mood;
	}
	
	public boolean isOutside() {
		return room.isOutside();
	}
	
	public boolean isDoingNothing() {
		return mood==Mood.idle;
	}
	
	public boolean isDead() {
		return mood==Mood.dead;
	}
	
	public boolean isSleeping() {
		return mood==Mood.sleeping;
	}
	
	public boolean isDining() {
		return mood==Mood.dining;
	}
	
	public boolean isCooking() {
		return mood==Mood.cooking;
	}
	
	public boolean getStats() {
		return stats;
	}
	
	
	
	
	
	
	//SETTERS---------------------------------------------------------------------------------------------



	
	public void setSpecie(Specie specie) {
		this.specie = specie;
		Specie.setDefault(specie);
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public void setPattern(Pattern pattern) {
		specie.setPattern(pattern);
		this.pattern=pattern;
	}
	public void setFirstColor(String color) {
		specie.setFirstColor(color);
		firstColor=color;
	}
	public void setSecondColor(String color) {
		specie.setSecondColor(color);
		secondColor=color;
	}
	public void setThirdColor(String color) {
		specie.setThirdColor(color);
		thirdColor=color;
	}
	public void setLastActionTime() {
		lastActionTime=time;
	}
	
	public void setSprayColor(String color) {
		sprayColor=color;
	}
	public void setSpray(boolean b) {
		sprayed=b;
	}
	
	public void useIngredient(int n) {
		if(!isCooking()) {
			return;
		}
		else if(getIngredientSlot()!=-1){
			n=Math.min(fridge.size()-1, Math.max(0,n));
			addIngredient(fridge.get(n));
			fridge.set(n, null);
		}
	}
	
	public void addIngredient(Ingredient ingredient) {
		if(getIngredientSlot()!=-1 && ingredient!=null)
			ingredients.set(getIngredientSlot(), ingredient);
	}
	
	public void removeIngredient(int n) {
		Ingredient ingredient = ingredients.get(n);
		for(int i=0;i<fridge.size();i++) 
			if(fridge.get(i)==null) {
				fridge.set(i, ingredient);
				break;
			}
		ingredients.set(n, null);
	}
	
	
	
	public void cook(Dish dish) {
		recipe=Recipe.getRecipe(ingredients.get(0),ingredients.get(1),ingredients.get(2),dish);
		setMood(Mood.dining);
		resetIngredients();
	}
	
	public void resetIngredients() {
		ingredients.set(0, null);
		ingredients.set(1, null);
		ingredients.set(2, null);
		}
	
	public void setFridge(boolean open) {
		fridgeDoor=open;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe=recipe;
	}
	
	public void setRoom(Room r) {
		room=r;
	}
	public void setMood(Mood mood) {
		this.mood=mood;
	}
	
	public void resetMood() {
		setMood(Mood.idle);
	}
	
	public void addActiveMood(Mood mood) {
		activeMoods.add(mood);
		if(!mood.sameFamily(getMood())) resetMood();
	}
	
	public void resetActiveMood() {
		activeMoods = new ArrayList<Mood>();
	}
	
	public void addPassiveMood(Mood mood) {
		passiveMoods.add(mood);
	}
	
	public void setStats(boolean b) {
		stats=b;
	}
	
	
	
	
	
	//Fonction uniquement pour jouer sur console
	
	public String getTextinfo() {
		String s=name+"\t\t"+specie+"\t\t"+room+"\t\t"+weather+"\t\t"+Integer.toString(cycle)+"\t\t\n";
		
		if(stats) {
			for(State state : states) {
				s+=Text.call(state)+" : [";
				
				double n=state.getValue();
				
				String percent=Double.toString(n*100);
				percent=(percent.contains("E")?"0":percent.substring(0,percent.length()>4?5:percent.length()))+" %";
				
				for(int i=0;i<50;i++) {s+=n>0?"#":" ";n-=0.02;}
				
				s+="] \n"+percent;
				
			}
		}
		
		if(fridgeIsOpen()) {
			s+="FRIGO-----\n";
			for(int i=0;i<8;i++)
				s+=Integer.toString(i+1)+" "+Text.call(fridge.get(i))+"\n";
			s+="----------\n";
			}
		
		if(isCooking()) {
			s+="INGREDIENTS-----\n";
			for(int i=0;i<3;i++)
				s+=Integer.toString(i+1)+" "+Text.call(ingredients.get(i))+"\n";
			s+="----------------\n";
			}
		
		if(isDining()) {
			s+="DISH-----\n";
			s+=Text.call(recipe)+"\n";
			s+="----------------\n";
			}
		
		return s;
	}
	
	
	
}
