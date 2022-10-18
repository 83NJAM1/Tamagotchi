package app.model;

public enum Weather {
	raining(1),
	sunny(-1),
	cloudy(0),
	storm(3);
	
	private int wetCoefficient;
	
	private Weather(int n) {
		wetCoefficient=n;
	}
	
	public int getWet() {
		return wetCoefficient;
	}
	
}
