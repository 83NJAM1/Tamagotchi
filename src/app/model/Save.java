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
	private int numberStats;
	private Hashtable<String, Double> stats;

	public Save(String pathname) {

		stats = new Hashtable<String, Double>();
		
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
	
	public Date getDate() {
		return dateLoaded;
	}
	public String getRoomId() {
		return idRoom;
	}
	public String getPetType() {
		return petType;
	}
	public Double getStat(String key) {
		return stats.get(key);
	}
	
	/**
	 * PAS DEFINITIF
	 */
	public void save() {
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
	 * PAS DEFINITIF
	 * @param pathname
	 */
	public void load(String pathname) {
		
		try {
			FileReader in = new FileReader(pathname);
			
			Scanner scanner = new Scanner(in);
			scanner.useLocale(Locale.ENGLISH);
			
			dateLoaded = new Date(scanner.nextLong());
			idRoom = scanner.next();
			petType = scanner.next();
			numberStats = scanner.nextInt();
			
			String key;
			for ( int i=0; i<numberStats; i++) {
				key = scanner.next();
					  scanner.next();
				stats.put(key, scanner.nextDouble());
			}
			
			in.close();
			scanner.close();
				
		} catch ( IOException e) {
			System.err.println("unknown error : " + e);
		} catch (IndexOutOfBoundsException e ) {
			System.err.println("end of file : " + e);
		}
	}
	
	public String toString() {
		return dateLoaded + System.lineSeparator()
			 + idRoom + System.lineSeparator()
			 + petType + System.lineSeparator()
			 + "    " + stats.get("hunger") + System.lineSeparator()
			 + "    " + stats.get("thirst") + System.lineSeparator()
			 + "    " + stats.get("weight") + System.lineSeparator()
			 + "    " + stats.get("hygiene") + System.lineSeparator()
			 + "    " + stats.get("moral") + System.lineSeparator();
	}
}
