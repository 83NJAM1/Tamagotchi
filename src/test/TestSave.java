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
		assertEquals(0.3999999999999999, save.getStat("hunger"));
		assertEquals(0.3999999999999999, save.getStat("thirst"));
		assertEquals(0.3999999999999999, save.getStat("weight"));
		assertEquals(0.6400000000000001, save.getStat("hygiene"));
		assertEquals(0.3999999999999999, save.getStat("moral"));
	}


}
