package app.view;
 
import javafx.scene.image.Image;

/**
 * 
 * @author ben
 * view.Pet permet l'affichage du pet dans un etat donné par model.Pet
 * TODO une classe Sprite
 *      Ca me semble mieux de faire une classe Sprite et que view.Pet
 *      hérite de Sprite
 */
public class Pet extends Image {

	//########################### ATTRIBUTS #####################################
	 
	private Stat hunger;
	private Stat thirst;
	private Stat weight;
	private Stat hygiene;
	private Stat moral;
	
	private double x;
	private double y;
	private double w;
	private double h;
	
	//############################ METHODES #####################################
	
	// Constructeur obligatoire car de type Image
	// REMARQUE: nescesaire uniquement pour la vue
	//			 ?? chemin de l'image dans le model ??
	public Pet(String spritesheet) {
		super(spritesheet);
		x = 0;
		y = 92;
		w = 128;
		h = 128;
	}
	
	public void setHunger(Stat hunger) {
		this.hunger = hunger;
	}
	public void setThirst(Stat thirst) {
		this.thirst = thirst;
	}
	public void setWeight(Stat weight) {
		this.weight = weight;
	}
	public void setHygiene(Stat hygiene) {
		this.hygiene = hygiene;
	}
	public void setMoral(Stat moral) {
		this.moral = moral;
	}
	
	public Stat getHunger() {
		return hunger;
	}
	public Stat getThirst() {
		return thirst;
	}
	public Stat getWeight() {
		return weight;
	}
	public Stat getHygiene() {
		return hygiene;
	}
	public Stat getMoral() {
		return moral;
	}
	
	public void setX(double v) {
		x=v;
	}
	public void setY(double v) {
		y=v;
	}
	public void setW(double v) {
		w=v;
	}
	public void setH(double v) {
		h=v;
	}
	public double getX() {
		return x;
	}
	public double getY() {
		return y;
	}
	public double getW() {
		return w;
	}
	public double getH() {
		return h;
	}

}
