package app.model;
import app.states.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Pet {
	
	protected Specie specie;
	
	protected String name;
	
	private Pattern pattern;
	private String firstColor;
	private String secondColor;
	private String thirdColor;
	
	private String sprayColor;
	private boolean sprayed;
	
	protected long time;
	protected long lastActionTime;
	protected int cycle;
	
	protected Room room;
	
	protected Weather weather;
		
	protected String deathCause;
	protected String deathNote;
	
	protected List<State> states = new ArrayList<State>();
		
	protected Mood mood;
	protected List<Mood> passiveMoods = new ArrayList<Mood>();
	protected List<Mood> activeMoods = new ArrayList<Mood>();
		
	protected final List<Ingredient> ingredients = Arrays.asList(null,null,null);
	protected List<Ingredient> fridge;
	protected boolean fridgeDoor;
	protected Recipe recipe;
	
	protected boolean stats;
	
	public Pet() {
		time=System.currentTimeMillis();
		states = Arrays.asList(
				new Health(this),
				new Energy(this),
				new Moral(this),
				new Hunger(this),
				new Thirst(this),
				new Clean(this),
				new Weight(this),
				new Wet(this),
				new Sick(this)
				);
		
		setSpecie(Game.getSpecie());
		
		setName(specie.getName());
		
		setPattern(specie.getPattern());
		setFirstColor(specie.getFirstColor());
		setSecondColor(specie.getSecondColor());
		setThirdColor(specie.getThirdColor());
		
		setRoom(Room.livingRoom);
		
		
		newCycle();
	}
	
	public void refresh() {
		
		long time = System.currentTimeMillis();
		int timePassed = (int)(time-this.time);
		int count = Math.min(Math.max(1, timePassed/100),10);
		int interval = timePassed/count;
				
		for(int i=0;i<count;i++) pass(interval);
		

	}
	
	public void newCycle() {
		cycle++;
		lastActionTime = time;
		weather = Random.getWeather();
		sprayColor = Random.getSpray();
		fridge = Random.getFridge();
		
	}
	
	public void pass(int interval) {
		
		time+=interval;
		
		if(getAloneTime()>2*3600*1000) newCycle();
	
		passiveMoods = new ArrayList<Mood>();
		
		for(State state : states) {
			state.pass(interval);
		}
		
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
	
	public void fastForward(int n) {
		if(n<0)return;
		time-=n;
		lastActionTime-=n;
	}
	
	public Specie getSpecie() {
		return specie;
	}
	
	public void setSpecie(Specie specie) {
		this.specie=specie;
	}
	
	public boolean is(Specie specie) {
		return specie==this.specie;
	}
	
	public void setName(String name) {
		this.name=name;
		specie.setName(name);
	}
	
	public String getName() {
		return name;
	}
	
	public void setPattern(Pattern pattern) {
		specie.setPattern(pattern);
		this.pattern=pattern;
	}
	public Pattern getPattern() {
		return pattern;
	}
	
	public void setFirstColor(String color) {
		specie.setFirstColor(color);
		firstColor=color;
	}
	public String getFirstColor() {
		return firstColor;
	}
	
	public void setSecondColor(String color) {
		specie.setSecondColor(color);
		secondColor=color;
	}
	public String getSecondColor() {
		return secondColor;
	}
	
	public void setThirdColor(String color) {
		specie.setThirdColor(color);
		thirdColor=color;
	}
	public String getThirdColor() {
		return thirdColor;
	}
	
	public void setLastActionTime() {
		lastActionTime=time;
	}
	
	public int getAloneTime() {
		return (int)(time-lastActionTime);
	}
	
	public void setSprayColor(String color) {
		sprayColor=color;
	}
	public String getSprayColor() {
		return sprayColor;
	}
	
	public void setSpray(boolean b) {
		sprayed=b;
	}
	public boolean isSprayed() {
		return sprayed;
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
	

	public void useIngredient(int n) {
		if(!isCooking()) {
			return;
		}
		else {
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
	
	public int getIngredientSlot() {
		int n=0;
		for(Ingredient ingredient : ingredients) {
			if(ingredient==null)
				return n;
			n++;
		}
		return -1;
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
	
	public Recipe getRecipe(Dish dish) {
		return Recipe.getRecipe(ingredients.get(0),ingredients.get(1),ingredients.get(2),dish);
	}
	
	public boolean fridgeIsOpen() {
		return fridgeDoor;
	}
	
	public void setFridge(boolean open) {
		fridgeDoor=open;
	}
	
	public Recipe getRecipe() {
		return this.recipe;
	}
	
	public void setRecipe(Recipe recipe) {
		this.recipe=recipe;
	}
	
	public Weather getWeather() {
		return weather;
	}
	
	public void setRoom(Room r) {
		room=r;
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
	
	public void setStats(boolean b) {
		stats=b;
	}
	
	public void getTextinfo() {
		System.out.println(name+"\t\t"+specie+"\t\t"+room+"\t\t"+weather+"\t\t"+Integer.toString(cycle)+"\t\t");
		
		if(stats) {
			for(State state : states) {
				String s=state.getText().call()+" : [";
				
				double n=state.getValue();
				
				String percent=Double.toString(n*100);
				percent=(percent.contains("E")?"0":percent.substring(0,percent.length()>4?5:percent.length()))+" %";
				
				for(int i=0;i<50;i++) {s+=n>0?"#":" ";n-=0.02;}
				
				s+="] "+percent;
				
				System.out.println(s);
			}
		}
		
		if(fridgeIsOpen()) {
			System.out.println("FRIGO-----");
			for(int i=0;i<8;i++)
				System.out.println(Integer.toString(i+1)+" "+Text.call(fridge.get(i)));
			System.out.println("----------");
			}
		
		if(isCooking()) {
			System.out.println("INGREDIENTS-----");
			for(int i=0;i<3;i++)
				System.out.println(Integer.toString(i+1)+" "+Text.call(ingredients.get(i)));
			System.out.println("----------------");
			}
		
		if(isDining()) {
			System.out.println("DISH-----");
			System.out.println(Text.call(recipe));
			System.out.println("----------------");
			}
		
		
	}
	
	
	
}
