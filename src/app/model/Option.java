package app.model;
 
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * 
 * @author ben
 * Les options de l'application
 */
public class Option {
	
	private String lastSave;
	private String language;
	private double volume;
	private int w_window;
	private int h_window;
	
	private File file;
	
	public Option(String pathname) {
		lastSave=null;
		language=Locale.FRENCH.toString();
		volume=0.25;
		w_window=640;
		h_window=360;
		
		try {
			file = new File(pathname);
		} 
		catch ( NullPointerException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * PAS DEFINITIF
	 */
	public void save() {
		try {
			FileWriter out = new FileWriter(file);
			out.write(this.toString());
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * PAS DEFINITIF
	 */
	public void load() {
		
		try {
			FileReader in = new FileReader(file);
			
			Scanner scanner = new Scanner(in);
			scanner.useLocale(Locale.ENGLISH);
			
			while ( scanner.hasNext() ) {
			
				switch (scanner.next()) {
					case "last_save" :
						scanner.next();
						String v = scanner.next();
						if ( v.equals("null") )
							lastSave = null;
						else
							lastSave = v;
						break;
					
					case "language" :
						scanner.next();
						language = scanner.next();
						break;
					
					case "volume" :
						scanner.next();
						volume = scanner.nextDouble();
						break;
					
					case "width_window" :
						scanner.next();
						w_window = scanner.nextInt();
						break;
					
					case "height_window" :
						scanner.next();
						h_window = scanner.nextInt();
						break;
					
					default:
						break;
				}
			}
			
			in.close();
			scanner.close();
				
		} catch ( IOException e) {
			System.err.println(e);
		} catch (IndexOutOfBoundsException e ) {
			System.err.println(e);
		}
	}
	
	public String toString() {
		return "last_save : " + lastSave + System.lineSeparator() +
			   "language : " + language + System.lineSeparator() +
			   "volume : " + volume + System.lineSeparator() +
			   "width_window : " + w_window + System.lineSeparator() + 
			   "height_window : " + h_window + System.lineSeparator();
	}
	
	public void setLastSave(String value) {
		lastSave=value;
	}
	public String getLastSave() {
		return lastSave;
	}
	
	public void setLanguage(String value) {
		language=value;
	}
	public String getLanguage() {
		return language;
	}
	
	public void setVolume(Double value) {
		volume=value;
	}
	public Double getVolume() {
		return volume;
	}
	
	public void setWindowWidth(int value) {
		w_window=value;
	}
	public int getWindowWidth() {
		return w_window;
	}
	
	public void setWindowHeight(int value) {
		h_window=value;
	}
	public int getWindowHeight() {
		return h_window;
	}
}
