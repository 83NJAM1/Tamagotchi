package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.model.Save;

class TestSave {
	
	Save save;
	
	@BeforeEach
	void setUp() {
		save = new Save(null);
	}
	
	@Test
	@DisplayName("loading save")
	void testLoadingSave() {
		save.load("./res/testsave.tmg.test");
		assertEquals(0.3999999999999999, save.getState("hunger"));
		assertEquals(0.3999999999999999, save.getState("thirst"));
		assertEquals(0.3999999999999999, save.getState("weight"));
		assertEquals(0.6400000000000001, save.getState("hygiene"));
		assertEquals(0.3999999999999999, save.getState("moral"));
	}


}
