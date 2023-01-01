package app.view;

public interface WeatherEffect {

	public boolean drawEffect();
	public boolean drawEffect(int numPass);
	public void stopEffect();
	public void resize(double width, double height);
	public void clear();
}
