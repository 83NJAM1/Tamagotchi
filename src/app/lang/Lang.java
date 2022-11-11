package app.lang;

/**
 * 
 * @author ben
 * Interface qui permettra de traduire les phrases du jeu via des methodes.
 * Sera remplacer dans les version ultérieur par Java i18n mais pour simplifier
 * on reste avec ça
 */
public abstract class Lang {
	
	private static Lang instance;
	
	public static Lang getLang() {
		return instance;
	}
	public static void setLang(Lang newInstance) {
		instance = newInstance;
	}

}
