package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;



import app.model.*;

class TestStatOnTime {

	Animal animal;
	
	@BeforeEach
	void setUp() {
		animal=new Animal();
		Stat stat = new Stat("hunger");
		animal.setHunger(stat);
	}

	@Test
	@DisplayName("Changement stat")
	void testChangementStat() {
		double x = animal.getHunger().getValue();
		animal.getHunger().dec();
		assertNotEquals(x, animal.getHunger().getValue());
	}

}
