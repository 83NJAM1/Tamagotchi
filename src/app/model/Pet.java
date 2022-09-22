package app.model;

import app.useless.*;

public class Pet {

	private String name;
	private Characteristic bladder;
	private Characteristic energy;
	private Characteristic hydrated;
	private Characteristic hygiene;
	private Characteristic nourished;
	private Characteristic spirit;
	private Characteristic weight;
	private Characteristic[] list;
	public Pet(String name) {
		this.name = name;
		
		bladder = new Bladder(0.5);
		energy = new Energy(0.5);
		hydrated = new Hydrated(0.5);
		hygiene = new Hygiene(0.5);
		nourished = new Nourished(0.5);
		spirit = new Spirit(0.5);
		weight = new Weight(0.5);
		list = new Characteristic[]{bladder, energy, hydrated, hygiene, nourished, spirit, weight};
	}
	
	public Characteristic[] getCharacteristic() {
		return list;
	}
	
	public String getName() {
		return name;
	}
	
	public void autoDecrement() {
		for (Characteristic c : list) {
			c.autoDecrement();
		}
	}
}
