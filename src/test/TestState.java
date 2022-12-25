package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import app.model.*;

class TestState {
	
	State state;
	
	@BeforeEach
	void setUp() {
		state = new State("Test");
	}

	@Test
	@DisplayName("decrement stat")
	void testDecrementStat() {
		double oldValue = state.getValue();
		state.applyBonus("c");
		assertTrue(oldValue > state.getValue());
	}
	
	@Test
	@DisplayName("increment stat")
	void testIncrementStat() {
		double oldValue = state.getValue();
		state.applyMalus("c");
		assertTrue(oldValue < state.getValue());
	}

}
