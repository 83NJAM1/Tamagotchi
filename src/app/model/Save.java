package app.model;
 
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Hashtable;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * @author ben
 * Permet de sauvegarder un état données du jeu
 */
public class Save {

	private File file;
	 
	private Game gameRecord;
	private Date dateLoaded;
	private String idRoom;
	private String petType;
	private int numberStates;
	private Hashtable<String, Double> states;
	
	/**
	 * constructeur
	 * @param pathname
	 */
	public Save(String pathname) {

		System.out.println("MODEL-SAVE - CONS: " + pathname);
		
		states = new Hashtable<String, Double>();
		
		try {
			file = new File(pathname);
		} 
		catch ( NullPointerException e) {
			System.err.println(e.getMessage());
		}
		
		
	}
	
	public void setGameInstance(Game game){
		gameRecord = game;
	}
	
	/**
	 * obtient la date de la dernière sauvegarde
	 * @return
	 */
	public Date getDate() {
		return dateLoaded;
	}
	
	/**
	 * obtient la room enregistré
	 * @return
	 */
	public String getRoomId() {
		return idRoom;
	}
	
	/**
	 * obtient le type de pet enregistré
	 * @return
	 */
	public String getPetType() {
		return petType;
	}
	
	/**
	 * obtient la state enregistré
	 * @return
	 */
	public Double getState(String key) {
		return states.get(key);
	}
	
	/**
	 * NOTE PAS DEFINITIF
	 */
	public void save() {
		
		System.out.println("MODEL-SAVE - SAVE: " + file.getAbsolutePath());
		
		try {
			FileWriter out = new FileWriter(file);
			out.write( (new Date()).getTime() + System.lineSeparator() + gameRecord.toString() );
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * NOTE PAS DEFINITIF
	 * @param pathname
	 */
	public void load(String pathname) {
		
		System.out.println("MODEL-SAVE - LOAD: " + pathname);
		
		try {
			FileReader in = new FileReader(pathname);
			
			Scanner scanner = new Scanner(in);
			scanner.useLocale(Locale.ENGLISH);
			
			dateLoaded = new Date(scanner.nextLong());
			idRoom = scanner.next();
			petType = scanner.next();
			numberStates = scanner.nextInt();
			
			String key;
			for ( int i=0; i<numberStates; i++) {
				key = scanner.next();
					  scanner.next();
				states.put(key, scanner.nextDouble());
			}
			
			in.close();
			scanner.close();
				
		} catch ( IOException e) {
			System.err.println("unknown error : " + e);
		} catch (IndexOutOfBoundsException e ) {
			System.err.println("end of file : " + e);
		}
	}
	
	@Override
	public String toString() {
		return dateLoaded + System.lineSeparator()
			 + idRoom + System.lineSeparator()
			 + petType + System.lineSeparator()
			 + "    " + states.get("hunger") + System.lineSeparator()
			 + "    " + states.get("thirst") + System.lineSeparator()
			 + "    " + states.get("weight") + System.lineSeparator()
			 + "    " + states.get("hygiene") + System.lineSeparator()
			 + "    " + states.get("moral") + System.lineSeparator()
			 + "    " + states.get("health") + System.lineSeparator()
			 + "    " + states.get("energy") + System.lineSeparator();
	}
}
