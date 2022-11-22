package app.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author ben
 * Permet de sauvegarder un état données du jeu
 */
public class Save {

	private Date date;
	private File file;
	private FileWriter out;//FileOutputStream out;
	private FileReader in;//FileInputStream in;
	private String info_game;

	public Save(String pathname) {
		date = new Date();
		file = new File(pathname);
		info_game = new String();
		try {
			out = new FileWriter(file);
			out.write(date.toString()+System.lineSeparator());
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
		
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 * @param info
	 */
	public void addInfoGame(String info) {
		info_game+=info;
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 */
	public void saveToDisk() {
		try {
			out = new FileWriter(file, true);
			out.write(info_game);
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 * @param info
	 */
	public void addData(String info) {
		try {
			out = new FileWriter(file, true);
			out.write(info);
			out.flush();
			out.close();
		} catch ( IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * JUSTE UN TEST PAS DEFINITIF
	 * @param info
	 */
	public String getData(String info) {
		
		char[] buff = new char[4096];
		String result="";
		int offset = 0;
		
		try {
			in = new FileReader(file);
			
			while( (offset = in.read(buff, offset, buff.length)) != -1 ) {

				result += new String(buff);
			}
			
			in.close();
			
		} catch ( IOException e) {
			System.err.println("unknown error : " + e);
		} catch (IndexOutOfBoundsException e ) {
			System.err.println("end of file : " + e);
		}
		
		return result;
	}
}
