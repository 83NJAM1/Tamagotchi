package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import app.model.*;

class TestStat {
	
	Stat stat;
	
	@BeforeEach
	void setUp() {
		stat = new Stat("Test");
	}

	@Test
	@DisplayName("decrement stat")
	void testDecrementStat() {
		double oldValue = stat.getValue();
		stat.dec();
		assertTrue(oldValue > stat.getValue());
	}
	
	@Test
	@DisplayName("increment stat")
	void testIncrementStat() {
		double oldValue = stat.getValue();
		stat.inc();
		assertTrue(oldValue < stat.getValue());
	}

}
