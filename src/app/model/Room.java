package app.model;
 
/**
 * 
 * @author ben
 * Pièce dans laquelle le compagnon peut s'y rendre
 * Une pièce permet certaine action alors que d'autre non
 * 
 * Pour le moment une pièce se compose
 * uniquement d'un nom
 * 
 * Dans le cas de zone cliquable la pièce se composera
 * d'un nom et de zones
 */
public class Room {
	String id_name;
	
	public Room(String id_name) {
		this.id_name=id_name;
	}
	
	public String toString() {
		return id_name;
	}
}
