package app.view;

public interface WeatherEffect {
	
	/**
	 * dessine l'effet dans tous ses passes
	 * @return vrai si non stoper
	 */
	public boolean drawEffect();
	
	/**
	 * dessine l'effet à la passes spécifier
	 * @param numPass le numéro de la passe à partire de 1
	 * @return vrai si non stoper
	 */
	public boolean drawEffect(int numPass);
	
	/**
	 * Met l'effet dans un état d'arret
	 */
	public void stopEffect();
	
	/**
	 * redimensionne l'effet
	 * @param width
	 * @param height
	 */
	public void resize(double width, double height);
	
	/**
	 * stop les threads s'il y en a 
	 */
	public void clear();
}
