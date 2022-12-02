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
	
	//############################ METHODES #####################################
	
	// Constructeur obligatoire car de type Image
	// REMARQUE: nescesaire uniquement pour la vue
	//			 ?? chemin de l'image dans le model ??
	public Pet(String spritesheet) {
		super(spritesheet);
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
	
	public double getX() {
		return 0;
	}
	public double getY() {
		return 92;
	}
	public double getW() {
		return 128;
	}
	public double getH() {
		return 128;
	}

}
